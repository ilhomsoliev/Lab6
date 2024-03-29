package org.example.commands;


import org.example.command_line.console.Console;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.network.NetworkClient;
import org.example.network_models.request.HelpRequest;
import org.example.network_models.response.HelpResponse;

import java.io.IOException;

/**
 * Команда 'print_ascending'. Выводит элементы коллекции в порядке возрастания.
 *
 * @author ilhom_soliev
 */
public class PrintAscending extends Command {
    private final Console console;
    private final NetworkClient client;

    public PrintAscending(Console console, NetworkClient client) {
        super("print_ascending");
        this.console = console;
        this.client = client;
    }


    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        try {
            var response = (HelpResponse) client.sendAndReceiveCommand(new HelpRequest());
            console.print(response.helpMessage);
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        }
        return false;
    }
}
