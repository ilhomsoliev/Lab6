package org.example.managers;

import org.example.handlers.CommandHandler;
import org.example.network_models.request.Request;
import org.example.network_models.response.Response;
import org.example.utility.Configuration;
import org.example.utility.Console;
import org.example.utility.StandardConsole;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Класс серверного менеджера.
 * Обрабатывает подключения от клиента. Отправляет ответ на запрос клиенту.
 */
public class ServerManager {
    private static final Console console = new StandardConsole();

    private final Server server;
    private final CommandHandler commandHandler;

    public ServerManager(CommandHandler commandHandler) {
        server = new Server(Configuration.getHost(), Configuration.getPort());
        this.commandHandler = commandHandler;
    }

    public void start() throws IOException {
        server.start();
    }

    public void writeRes(SocketChannel socketChannel, Response response) {
        try {
            server.writeObject(socketChannel, response);  //отправляем клиенту
        } catch (IOException e) {
            console.write("Не получилось передать данные клиенту");
        }
    }

    public void handlerSocketChannel(SocketChannel socketChannel) throws IOException {
        Request request;
        try {
            request = (Request) server.getObject(socketChannel); //получаем запрос от клиента

            //на основе запроса формируем ответ
            Response response = commandHandler.handle(request);;

            //на UpdateCollectionHistoryRequest ответ не требуется
//            if (!(response instanceof UpdateCollectionHistoryResponse)) {
            writeRes(socketChannel, response);  //отправляем ответ
//            }
        } catch (IOException | ClassNotFoundException e) {
            console.write(e.toString());
            console.write("Принять данные не получилось");
            socketChannel.close();
        } catch (ClassCastException e) {
            console.write(e.toString());
            console.write("Передан неправильный тип данных");
        } finally {
            socketChannel.close();
        }
    }

    public void run() {
        SocketChannel socketChannel;
        while (true) {
            try {
                socketChannel = server.getSocketChannel();
                if (socketChannel == null) continue;
                handlerSocketChannel(socketChannel);
            } catch (IOException e) {
                console.write(e.toString());
            }
        }
    }
}