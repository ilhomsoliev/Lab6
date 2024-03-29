package org.example.utility;


import org.example.model.Ticket;

import java.util.Comparator;

public class TicketComparator implements Comparator<Ticket> {
    public int compare(Ticket a, Ticket b) {
        return (int) (a.getPrice() - b.getPrice());
    }
}
