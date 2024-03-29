package org.example.commands;


import org.example.managers.TicketRepository;
import org.example.model.Ticket;
import org.example.network_models.request.Request;
import org.example.network_models.response.InfoResponse;
import org.example.network_models.response.Response;

/**
 * Команда 'info'. Выводит информацию о коллекции.
 * @author ilhom
 */
public class Info extends Command {
    private final TicketRepository productRepository;

    public Info(TicketRepository ticketRepository) {
        super("info", "вывести информацию о коллекции");
        this.productRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var lastInitTime = productRepository.getLastInitTime();
        var lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        var lastSaveTime = productRepository.getLastSaveTime();
        var lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        var message = "Сведения о коллекции:\n" +
                " Тип: " + productRepository.type() + "\n" +
                " Количество элементов: " + productRepository.size() + "\n" +
                " Дата последнего сохранения: " + lastSaveTimeString + "\n" +
                " Дата последней инициализации: " + lastInitTimeString;
        return new InfoResponse(message, null);
    }
}
