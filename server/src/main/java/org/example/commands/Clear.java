package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.Request;
import org.example.network_models.response.ClearResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'clear'. Очищает коллекцию.
 * @author ilhom
 */
public class Clear extends Command {
    private final TicketRepository productRepository;

    public Clear(TicketRepository productRepository) {
        super("clear", "очистить коллекцию");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            productRepository.clear();
            return new ClearResponse(null);
        } catch (Exception e) {
            return new ClearResponse(e.toString());
        }
    }
}
