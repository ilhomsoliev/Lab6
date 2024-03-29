package org.example.commands;


import org.example.command_line.console.Console;
import org.example.modules.TCPclient;
import org.example.network_models.request.InfoRequest;
import org.example.network_models.response.InfoResponse;

import java.io.IOException;

/**
 * Команда 'info'. Выводит информацию о коллекции.
 * @author ilhom
 */
public class Info extends Command {
    private final Console console;
    private final TCPclient client;

    public Info(Console console, TCPclient client) {
        super("info");
        this.console = console;
        this.client = client;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        try {
            var response = (InfoResponse) client.sendCommandAndReceiveResponse(new InfoRequest());
            console.print(response.infoMessage);
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (ClassNotFoundException e) {
            console.printError("Class Not Found!");
        }
        return false;
    }
}