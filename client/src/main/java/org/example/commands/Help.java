package org.example.commands;


import org.example.command_line.console.Console;
import org.example.modules.TCPclient;
import org.example.network_models.request.HelpRequest;
import org.example.network_models.response.HelpResponse;

import java.io.IOException;

/**
 * Команда 'help'. Выводит справку по доступным командам
 * @author ilhom
 */
public class Help extends Command {
    private final Console console;
    private final TCPclient client;

    public Help(Console console, TCPclient client) {
        super("help");
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
            var response = (HelpResponse) client.sendCommandAndReceiveResponse(new HelpRequest());
            console.print(response.helpMessage);
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (ClassNotFoundException e) {
            console.printError("Class Not Found!");
        }
        return false;
    }
}
