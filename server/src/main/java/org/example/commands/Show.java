package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.Request;
import org.example.network_models.response.Response;
import org.example.network_models.response.ShowResponse;

import java.util.Collections;

/**
 * Команда 'show'. Выводит все элементы коллекции.
 * @author ilhom
 */
public class Show extends Command {
    private final TicketRepository ticketRepository;

    public Show(TicketRepository ticketRepository) {
        super("show", "вывести все элементы коллекции");
        this.ticketRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            return new ShowResponse(ticketRepository.sorted().toString(), null);
        } catch (Exception e) {
            return new ShowResponse(Collections.emptyList().toString(), e.toString());
        }
    }
}
