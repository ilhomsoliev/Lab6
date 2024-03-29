package org.example.commands;


import org.example.command_line.console.Console;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.modules.TCPclient;
import org.example.network.NetworkClient;
import org.example.network_models.request.HelpRequest;
import org.example.network_models.request.PrintAscendingRequest;
import org.example.network_models.response.HelpResponse;
import org.example.network_models.response.PrintAscendingResponse;

import java.io.IOException;

/**
 * Команда 'print_ascending'. Выводит элементы коллекции в порядке возрастания.
 *
 * @author ilhom_soliev
 */
public class PrintAscending extends Command {
    private final Console console;

    public PrintAscending(Console console) {
        super("print_ascending");
        this.console = console;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            var response = (PrintAscendingResponse) TCPclient.sendCommandAndReceiveResponse(new PrintAscendingRequest());
            console.print(response.getResult());
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }
}
