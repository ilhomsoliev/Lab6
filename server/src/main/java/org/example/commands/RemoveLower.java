package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.RemoveLowerRequest;
import org.example.network_models.request.Request;
import org.example.network_models.response.RemoveByIdResponse;
import org.example.network_models.response.RemoveFirstResponse;
import org.example.network_models.response.RemoveLowerResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 * @author ilhom
 */
public class RemoveLower extends Command {
    private final TicketRepository productRepository;

    public RemoveLower(TicketRepository productRepository) {
        super("remove_lower ", "удалить элемент из коллекции по ID");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (RemoveLowerRequest) request;

        try {
            if (productRepository.size() == 0) {
                return new RemoveLowerResponse("No ticket to delete!");
            }
            productRepository.removeFirst();
            return new RemoveByIdResponse(null);
        } catch (Exception e) {
            return new RemoveByIdResponse(e.toString());
        }
    }
}
