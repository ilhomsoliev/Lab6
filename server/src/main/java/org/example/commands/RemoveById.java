package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.RemoveByIdRequest;
import org.example.network_models.request.Request;
import org.example.network_models.response.RemoveByIdResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 * @author ilhom
 */
public class RemoveById extends Command {
    private final TicketRepository productRepository;

    public RemoveById(TicketRepository productRepository) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (RemoveByIdRequest) request;

        try {
            if (!productRepository.checkExist(req.id)) {
                return new RemoveByIdResponse("Продукта с таким ID в коллекции нет!");
            }

            productRepository.remove(req.id);
            return new RemoveByIdResponse(null);
        } catch (Exception e) {
            return new RemoveByIdResponse(e.toString());
        }
    }
}