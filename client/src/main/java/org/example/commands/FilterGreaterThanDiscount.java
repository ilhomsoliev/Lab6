package org.example.commands;


import org.example.command_line.console.Console;
import org.example.exceptions.APIException;
import org.example.exceptions.IncorrectInputInScriptException;
import org.example.exceptions.WrongAmountOfElementsException;
import org.example.modules.TCPclient;
import org.example.network_models.request.CountByDiscountRequest;
import org.example.network_models.request.FilterGreaterThanDiscountRequest;
import org.example.network_models.response.FilterGreaterThanDiscountResponse;
import org.example.utility.Interrogator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Команда 'filter_greater_than_discount discount'. Выводит элементы, значение поля discount которых больше заданного.
 *
 * @author ilhom_soliev
 */
public class FilterGreaterThanDiscount extends Command {
    private final Console console;

    public FilterGreaterThanDiscount(Console console) {
        super("filter_greater_than_discount discount");
        this.console = console;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            var discount = (askDiscount());
            var response = (FilterGreaterThanDiscountResponse) TCPclient.sendCommandAndReceiveResponse(new FilterGreaterThanDiscountRequest(discount));
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println(response.getResult());
            return true;
        } catch (WrongAmountOfElementsException e) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (IncorrectInputInScriptException e) {
            console.printError("IncorrectInputInScriptException");
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }

    private int askDiscount() throws IncorrectInputInScriptException {
        int discount;
        var fileMode = Interrogator.fileMode();
        while (true) {
            try {
                console.println("Введите discount of ticket:");
                console.ps2();

                String partNumberStr = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(partNumberStr);
                if (partNumberStr.isEmpty()) {
                    throw new IllegalStateException("");
                }
                discount = Integer.parseInt(partNumberStr);
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Номер части не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return discount;
    }
}
