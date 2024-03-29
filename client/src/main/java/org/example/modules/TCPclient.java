package org.example.modules;

import org.apache.commons.lang3.SerializationUtils;
import org.example.network_models.request.Request;
import org.example.network_models.response.Response;

import java.io.*;
import java.net.*;
import java.nio.channels.*;

public class TCPclient {
    private static final int BUFFER_SIZE = 1024;
    private InetSocketAddress addr;

    private static SocketChannel socketChannel;
    private static ClientReceivingManager receivingManager = null;
    private static ClientSendingManager sendingManager = null;

    public TCPclient(String addr, int port) {
        this.addr = new InetSocketAddress(addr, port);
    }

    public SocketChannel start() {
        while (true) {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
                this.socketChannel = SocketChannel.open();
                socketChannel.bind(new InetSocketAddress("127.0.0.1", 20000 + (int) (Math.random() * 10000)));
                socketChannel.configureBlocking(false);
                socketChannel.connect(this.addr);
                receivingManager = new ClientReceivingManager(this);
                sendingManager = new ClientSendingManager(this);
                return socketChannel;
            } catch (Exception e) {
                try {
                    System.out.println("TCP client: " + e.getMessage());
                    socketChannel.close();
                } catch (Exception e2) {
                    System.out.println("TCP client: " + e2.getMessage());
                }
            }
        }
    }

    public static Response sendCommandAndReceiveResponse(Request request) throws IOException, ClassNotFoundException {
        try (var baos = new ByteArrayOutputStream(); var a = new ObjectOutputStream(baos)) {
            a.writeObject(request);
            sendingManager.send(baos.toByteArray());
            try (var command = new ObjectInputStream(new ByteArrayInputStream(receivingManager. receive()))) {
                Response response = (Response) command.readObject();
                System.out.println(response.getResult());
                return response;
            }
        } catch (Exception e) {
            System.out.println("\nsendCommand - " + e);
        }
        throw new ClassNotFoundException("");
    }

    public SocketChannel getSocketChannel() {
        return this.socketChannel;
    }

    public boolean isConnected() {
        return socketChannel != null && socketChannel.socket().isBound() && !socketChannel.socket().isClosed() && socketChannel.isConnected()
                && !socketChannel.socket().isInputShutdown() && !socketChannel.socket().isOutputShutdown();
    }
}
