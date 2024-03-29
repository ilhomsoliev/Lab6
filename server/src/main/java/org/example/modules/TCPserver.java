package org.example.modules;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.example.handlers.CommandHandler;
import org.example.network_models.request.Request;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class TCPserver {
    public interface TCPExecute {
        Object Execute(String s, Object o);
    }

    private int port;
    private String host;
    private HashSet<SocketChannel> sessions;
    private ReceivingManager receivingManager = new ReceivingManager();
    private SendingManager sendingManager = new SendingManager();
    private CommandHandler commandHandler;
    private Selector selector;

    public TCPserver(String host, int port, CommandHandler commandHandler) {
        this.port = port;
        this.host = host;
        this.commandHandler = commandHandler;
        this.sessions = new HashSet<>();
    }

    public HashSet<SocketChannel> getSessions() {
        return sessions;
    }

    public void close() {
        for (var se : sessions) {
            try {
                se.close();
            } catch (Exception e) {
            }
        }
    }

    public void start() {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            var socketAddress = new InetSocketAddress(host, port);
            serverSocketChannel.bind(socketAddress, port);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (!key.isValid()) continue;
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        var result = receivingManager.read(key);
                        if (result == null)
                            continue;
                        var res = result;
                        int p = -1;
                        if (res == null)
                            continue;
                        for (int i = res.length - 1; i > -1; i--) {
                            if (res[i] != 0) {
                                p = i;
                                break;
                            }
                        }
                        var cutres = Arrays.copyOfRange(res, 0, p + 1);

                        try (var command = new ObjectInputStream(new ByteArrayInputStream(cutres))) {
                            Request request = (Request) command.readObject();
                            System.out.println(request.getName());
                            try (var baos = new ByteArrayOutputStream(); var a = new ObjectOutputStream(baos)) {
                                var response = commandHandler.handle(request);
                                a.writeObject(response);
                                sendingManager.send((SocketChannel) key.channel(), baos.toByteArray());
                            }
                        } catch (Exception e) {
                            sendingManager.send((SocketChannel) key.channel(), "503".getBytes());
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (e.getMessage().equals("Address already in use: bind")) {
                port = (port + 1) % 32767;
                start();
            }
        }
    }

    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
            sessions.add(channel);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

