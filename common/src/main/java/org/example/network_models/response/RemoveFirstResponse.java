package org.example.network_models.response;

import org.example.utility.Commands;

public class RemoveFirstResponse extends Response {
    public RemoveFirstResponse(String error) {
        super(Commands.REMOVE_FIRST, error);
    }
}
