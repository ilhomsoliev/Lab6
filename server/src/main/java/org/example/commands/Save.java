package org.example.commands;

import org.example.managers.TicketRepository;
import org.example.model.Ticket;
import org.example.network_models.request.Request;
import org.example.network_models.response.Response;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save extends Command {
    private final TicketRepository productRepository;

    public Save(TicketRepository productRepository) {
        super("save", "save data locally");
        this.productRepository = productRepository;
    }

    @Override
    public Response apply(Request request) {
        StringBuilder str = new StringBuilder();
        for(Ticket t : productRepository.getCollection()){
            str.append(t.toString());
        }
        saveStringToFile(str.toString(), "/Users/ilhomsoliev/IdeaProjects/Lab6/server/data/out/out_server.txt");
        return null;
    }

    public static void saveStringToFile(String text, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
            System.out.println("[Server]: Изменения сохранены.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
