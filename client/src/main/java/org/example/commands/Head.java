package org.example.commands;


import org.example.command_line.console.Console;
import org.example.exceptions.APIException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.network.NetworkClient;
import org.example.network_models.request.HeadRequest;
import org.example.network_models.response.HeadResponse;

import java.io.IOException;

/**
 * Команда 'head'. Выводит первый элемент коллекции.
 * @author ilhom
 */
public class Head extends Command {
    private final Console console;
    private final NetworkClient client;

    public Head(Console console, NetworkClient client) {
        super("head");
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

            var response = (HeadResponse) client.sendAndReceiveCommand(new HeadRequest());
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }

            if (response.ticket == null) {
                console.println("Коллекция пуста!");
                return true;
            }

            console.println(response.ticket);
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
