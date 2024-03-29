package org.example.commands;


import org.example.blanks.TicketForm;
import org.example.command_line.console.Console;
import org.example.exceptions.*;
import org.example.modules.TCPclient;
import org.example.network.NetworkClient;
import org.example.network_models.request.RemoveByIdRequest;
import org.example.network_models.request.RemoveLowerRequest;
import org.example.network_models.response.RemoveByIdResponse;

import java.io.IOException;

/**
 * Команда 'remove_lower {element}'. Удаляет все элементы, меньшие, чем заданный из коллекции.
 *
 * @author ilhom_soliev
 */
public class RemoveLower extends Command {
    private final Console console;

    public RemoveLower(Console console) {
        super("remove_lower {element}");
        this.console = console;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            console.println("* Создание нового ticket (remove_lower):");
            var ticket = (new TicketForm(console)).build();
            var response = (RemoveByIdResponse) TCPclient.sendCommandAndReceiveResponse(new RemoveLowerRequest(ticket));
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println("Action finished");
            return true;
        } catch (WrongAmountOfElementsException e) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException e) {
            console.printError("IncorrectInputInScriptException");
        } catch (InvalidFormException e) {
            console.printError("Поля ticket не валидны! Продукт не создан!");
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
