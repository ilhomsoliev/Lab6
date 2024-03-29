package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.network_models.request.CountByDiscountRequest;
import org.example.network_models.request.Request;
import org.example.network_models.response.CountByDiscountResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 * @author ilhom
 */
public class CountByDiscount extends Command {
    private final TicketRepository productRepository;

    public CountByDiscount(TicketRepository productRepository) {
        super("count_by_discount discount", "вывести количество элементов, значение поля discount которых равно заданному");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            var req = (CountByDiscountRequest) request;
            var ans = productRepository.getAmountOfEqualsElementsByDiscount(req.discount);
            return new CountByDiscountResponse(null, Integer.toString(ans));
        } catch (Exception e) {
            return new CountByDiscountResponse(e.toString(), "Error");
        }
    }
}
