package org.example.commands;


import org.example.command_line.console.Console;
import org.example.exceptions.APIException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.network.NetworkClient;
import org.example.network_models.request.ClearRequest;
import org.example.network_models.response.ClearResponse;

import java.io.IOException;

/**
 * Команда 'clear'. Очищает коллекцию.
 * @author ilhom
 */
public class Clear extends Command {
    private final Console console;
    private final NetworkClient client;

    public Clear(Console console, NetworkClient client) {
        super("clear");
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
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            var response = (ClearResponse) client.sendAndReceiveCommand(new ClearRequest());
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }

            console.println("Коллекция очищена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        }
        return false;
    }
}
