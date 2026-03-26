package utcluj.isp.curs3.simpleapps.eventticketsystem;

import utcluj.isp.curs3.simpleapps.eventticketsystem.services.EventsManagementService;

import java.time.LocalDateTime;

public class AppEventsManager {
    public static void main(String[] args) {
        EventsManagementService eventsManagementService = new EventsManagementService();
        eventsManagementService.addEventTicket("Concert A", LocalDateTime.parse("2021-10-10T19:00:00"), "VIP", 100.0);
        eventsManagementService.addEventTicket("Concert A", LocalDateTime.parse("2021-10-10T19:00:00"), "REG", 50.0);

        eventsManagementService.buyTicketForEvent("Mihai", "m@m.m", "123", "Concert A");
        eventsManagementService.buyTicketForEvent("Alex", "a@a.a", "456", "Concert A");
        eventsManagementService.buyTicketForEvent("Dan", "a@a.a", "456", "Concert A");

        eventsManagementService.displayAllEvents();

//        eventsManagementService.displayAllPurchasedTickets();
//
//        eventsManagementService.checkinTicket("C:\\Tickets\\ticket_Alex.png", "456", "Concert A");
    }
}
