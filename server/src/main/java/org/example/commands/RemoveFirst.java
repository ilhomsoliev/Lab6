package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.RemoveByIdRequest;
import org.example.network_models.request.RemoveFirstRequest;
import org.example.network_models.request.Request;
import org.example.network_models.response.RemoveByIdResponse;
import org.example.network_models.response.RemoveFirstResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 * @author ilhom
 */
public class RemoveFirst extends Command {
    private final TicketRepository productRepository;

    public RemoveFirst(TicketRepository productRepository) {
        super("remove_first", "удалить элемент из коллекции по ID");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
//        var req = (RemoveFirstRequest) request;

        try {
            if (productRepository.size() == 0) {
                return new RemoveFirstResponse("No ticket to delete!");
            }
            productRepository.removeFirst();
            return new RemoveByIdResponse(null);
        } catch (Exception e) {
            return new RemoveByIdResponse(e.toString());
        }
    }
}
