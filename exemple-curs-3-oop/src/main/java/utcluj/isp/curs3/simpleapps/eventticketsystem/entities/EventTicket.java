package utcluj.isp.curs3.simpleapps.eventticketsystem.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventTicket {
    private String name;
    private LocalDateTime eventDate;
    private String ticketType;
    private double ticketPrice;

    public EventTicket(String name, LocalDateTime eventDate, String ticketType, double ticketPrice) {
        this.name = name;
        this.eventDate = eventDate;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
    }

    public String toString() {
        return "Event: " + name + " | Date: " + eventDate + " | Ticket Type: " + ticketType + " | Price: $" + ticketPrice;
    }
}
