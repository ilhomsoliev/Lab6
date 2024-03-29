package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.AddRequest;
import org.example.network_models.request.Request;
import org.example.network_models.response.AddResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 * @author ilhom
 */
public class Add extends Command {
    private final TicketRepository ticketRepository;

    public Add(TicketRepository ticketRepository) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.ticketRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (AddRequest) request;
        try {
            if (!req.ticket.validate()) {
                return new AddResponse(-1, "Поля продукта не валидны! Продукт не добавлен!");
            }
            var newId = ticketRepository.add(req.ticket);
            return new AddResponse(newId, null);
        } catch (Exception e) {
            return new AddResponse(-1, e.toString());
        }
    }
}