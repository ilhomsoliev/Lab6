package org.example.commands;


import org.example.blanks.TicketForm;
import org.example.command_line.console.Console;
import org.example.exceptions.APIException;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.InvalidFormException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.modules.TCPclient;
import org.example.network.NetworkClient;
import org.example.network_models.request.UpdateRequest;
import org.example.network_models.response.UpdateResponse;

import java.io.IOException;

/**
 * Команда 'update'. Обновляет элемент коллекции.
 * @author ilhom
 */
public class Update extends Command {
    private final Console console;
    private final TCPclient client;

    public Update(Console console, TCPclient client) {
        super("update ID {element}");
        this.console = console;
        this.client = client;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            var id = Integer.parseInt(arguments[1]);
            console.println("* Введите данные обновленного продукта:");
            var updatedProduct = (new TicketForm(console)).build();
            var response = (UpdateResponse) TCPclient.sendCommandAndReceiveResponse(new UpdateRequest(id, updatedProduct));
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println("Продукт успешно обновлен.");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (InvalidFormException exception) {
            console.printError("Поля продукта не валидны! Продукт не создан!");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть представлен числом!");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (IncorrectInputInScriptException ignored) {
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException " + e.getMessage());
        }
        return false;
    }
}
