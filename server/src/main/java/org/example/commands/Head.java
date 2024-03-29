package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.Request;
import org.example.network_models.response.HeadResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'head'. Выводит первый элемент коллекции.
 * @author ilhom
 */
public class Head extends Command {
    private final TicketRepository productRepository;

    public Head(TicketRepository productRepository) {
        super("head", "вывести первый элемент коллекции");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            return new HeadResponse(productRepository.first(), null);
        } catch (Exception e) {
            return new HeadResponse(null, e.toString());
        }
    }
}
