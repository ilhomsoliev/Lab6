package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.commands.*;
import org.example.handlers.CommandHandler;
import org.example.managers.CommandManager;
import org.example.managers.ServerManager;
import org.example.modules.FileManager;
import org.example.modules.TCPserver;
import org.example.utility.Configuration;
import org.example.managers.DumpManager;
import org.example.managers.TicketRepository;
import org.example.utility.Commands;
import org.example.utility.Console;
import org.example.utility.StandardConsole;
import org.example.utility.ValidateManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerApp {
    public static final int PORT = 9091;
    public static Logger logger = LogManager.getLogger("ServerLogger");

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }
        var dumpManager = new DumpManager(args[0]);
        var repository = new TicketRepository(dumpManager);

        var commandManager = new CommandManager() {{
            register(Commands.HELP, new Help(this));
            register(Commands.INFO, new Info(repository));
            register(Commands.SHOW, new Show(repository));
            register(Commands.ADD, new Add(repository));
            register(Commands.UPDATE, new Update(repository));
            register(Commands.REMOVE_BY_ID, new RemoveById(repository));
            register(Commands.CLEAR, new Clear(repository));
            register(Commands.HEAD, new Head(repository));
        }};
        TCPserver server = new TCPserver("localhost", PORT, new CommandHandler(commandManager));
        server.start();
    }
}