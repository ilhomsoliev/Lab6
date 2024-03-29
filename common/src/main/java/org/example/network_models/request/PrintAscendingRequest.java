package org.example.network_models.request;


import org.example.utility.Commands;

public class PrintAscendingRequest extends Request {
    public PrintAscendingRequest() {
        super(Commands.PRINT_ASCENDING);
    }
}
