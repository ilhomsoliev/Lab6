package org.example.commands;


import org.example.blanks.TicketForm;
import org.example.command_line.console.Console;
import org.example.exceptions.APIException;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.InvalidFormException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.modules.TCPclient;
import org.example.network.NetworkClient;
import org.example.network_models.request.AddRequest;
import org.example.network_models.response.AddResponse;

import java.io.IOException;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 *
 * @author ilhom_soliev
 */
public class Add extends Command {
    private final Console console;

    public Add(Console console) {
        super("add {element}");
        this.console = console;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {

        try {
            if (!arguments[1].isEmpty())
                throw new WrongAmountOfElementsException();
            console.println("* Создание нового 'ticket':");
            var newTicket = (new TicketForm(console)).build();
            var response = (AddResponse) TCPclient.sendCommandAndReceiveResponse(new AddRequest(newTicket));
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println("Новый ticket с id=" + response.newId + " успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (InvalidFormException exception) {
            console.printError("Поля продукта не валидны! Продукт не создан!");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (IncorrectInputInScriptException ignored) {
        } catch (ClassNotFoundException ignored) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }
}
