package org.example.network_models.response;

import org.example.utility.Commands;

public class PrintAscendingResponse extends Response {
    public PrintAscendingResponse(String error, String result) {
        super(Commands.PRINT_ASCENDING, error, result);
    }
}
