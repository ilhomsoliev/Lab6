package org.example.commands;


import org.example.command_line.console.Console;
import org.example.exceptions.APIException;
import org.example.exceptions.CollectionIsEmptyException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.modules.TCPclient;
import org.example.network.NetworkClient;
import org.example.network_models.request.RemoveFirstRequest;
import org.example.network_models.response.RemoveFirstResponse;

import java.io.IOException;

/**
 * Команда 'remove_first'. Удаляет первый элемент из коллекции.
 *
 * @author ilhom_soliev
 */
public class RemoveFirst extends Command {

    private final Console console;

    public RemoveFirst(Console console) {
        super("remove_first");
        this.console = console;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            var response = (RemoveFirstResponse) TCPclient.sendCommandAndReceiveResponse(new RemoveFirstRequest());

            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println("First Ticket успешно удален.");
            return true;
        } catch (WrongAmountOfElementsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException");

        }
        return false;
    }
}
