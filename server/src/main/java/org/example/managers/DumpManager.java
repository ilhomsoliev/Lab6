package org.example.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.example.ServerApp;
import org.example.model.Ticket;
import org.example.utility.LocalDateAdapter;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Использует файл для сохранения и загрузки коллекции.
 *
 * @author ilhom_soliev
 */
public class DumpManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    private final String fileName;
    private final String folderName;

    public DumpManager(String fileName) {
        this.fileName = fileName;
        Path path = Paths.get(fileName);
        Path parentDir = path.getParent();
        if (parentDir != null) {
            this.folderName = parentDir.toString();
        } else {
            this.folderName = "";
            System.out.println("No parent directory found.");
        }
    }

    /**
     * Записывает коллекцию в файл.
     *
     * @param collection коллекция
     */
    public void writeCollection(Collection<Ticket> collection) {
        System.out.println(fileName); // folderName + "/out.txt"
        try (PrintWriter collectionPrintWriter = new PrintWriter(fileName)) { // folderName + "/out.txt"
            collectionPrintWriter.println(gson.toJson(collection));
            ServerApp.logger.info("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            ServerApp.logger.error("Загрузочный файл не может быть открыт!");
        }
    }

    /**
     * Считывает коллекцию из файл.
     *
     * @return Считанная коллекция
     */
    public Collection<Ticket> readCollection() {
        System.out.println(fileName);
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileReader = new FileReader(fileName)) {
                var collectionType = new TypeToken<PriorityQueue<Ticket>>() {
                }.getType();
                var reader = new BufferedReader(fileReader);

                var jsonString = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.length() == 0) {
                    jsonString = new StringBuilder("[]");
                }

                PriorityQueue<Ticket> collection = gson.fromJson(jsonString.toString(), collectionType);
                PriorityQueue<Ticket> queue = new PriorityQueue<>();

                (new ArrayList<>(collection)).forEach(ticket -> {
//                    console.println(ticket.toString());
                    if (!ticket.validate()) {
                        ServerApp.logger.error("Ticket с id=" + ticket.getId() + " имеет невалидные поля.");
//                        console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
//                        throw new JsonParseException("");
//                        new PriorityQueue<>();
                    } else {
                        queue.add(ticket);
                    }
                });
                ServerApp.logger.info("Коллекция успешна загружена!");
                return queue;
            } catch (FileNotFoundException exception) {
                ServerApp.logger.error("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                ServerApp.logger.error("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                ServerApp.logger.error("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                ServerApp.logger.error("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            ServerApp.logger.error("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new PriorityQueue<>();
    }
}
