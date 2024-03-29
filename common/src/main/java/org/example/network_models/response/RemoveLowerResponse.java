package org.example.network_models.response;

import org.example.utility.Commands;

public class RemoveLowerResponse extends Response {
    public RemoveLowerResponse(String error) {
        super(Commands.REMOVE_LOWER, error);
    }
}
