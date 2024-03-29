package org.example;

import org.example.command_line.Runner;
import org.example.command_line.console.Console;
import org.example.command_line.console.StandardConsole;
import org.example.modules.AppModule;
import org.example.modules.TCPclient;

public class ClientApp {
    public static final int PORT = 9091;

    public static void main(String[] args) {
        var client = new TCPclient("localhost", PORT);
        client.start();
        Console console = new StandardConsole();
        AppModule.setClient(client);
        var cli = new Runner(client, console);
        cli.interactiveMode();

    }
}