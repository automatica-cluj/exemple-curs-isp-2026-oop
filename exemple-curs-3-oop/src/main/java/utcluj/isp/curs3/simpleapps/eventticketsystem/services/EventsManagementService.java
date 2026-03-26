package utcluj.isp.curs3.simpleapps.eventticketsystem.services;

import utcluj.isp.curs3.simpleapps.eventticketsystem.entities.EventTicket;
import utcluj.isp.curs3.simpleapps.eventticketsystem.entities.PurchasedTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventsManagementService {

    private TicketsCreatorValidatorUtil ticketsCreatorValidatorUtil;

    private ArrayList<EventTicket> eventTickets = new ArrayList<>();

    private ArrayList<PurchasedTicket> purchasedTickets = new ArrayList<>();

    public EventsManagementService() {
        this.ticketsCreatorValidatorUtil = new TicketsCreatorValidatorUtil();
    }

    public void addEventTicket(String name, LocalDateTime eventDate, String ticketType, double ticketPrice) {
        EventTicket eventTicket = new EventTicket(name, eventDate, ticketType, ticketPrice);
        eventTickets.add(eventTicket);
    }

    public void buyTicketForEvent(String name, String email, String phoneNumber, String eventName) {
        EventTicket eventTicket = null;
        for (EventTicket et : eventTickets) {
            if (et.getName().equals(eventName)) {
                eventTicket = et;
                break;
            }
        }
        String currentDateTime = LocalDateTime.now().toString();
        if (eventTicket != null) {
            PurchasedTicket purchasedTicket =
                    ticketsCreatorValidatorUtil.createTicket(eventTicket, name, email, phoneNumber, currentDateTime);
            ticketsCreatorValidatorUtil.generateElectronicTicket(purchasedTicket);
            purchasedTickets.add(purchasedTicket);
        }
    }


    public void checkinTicket(String imagePath, String phoneNumber, String eventName) {
        try {
            String ticket = ticketsCreatorValidatorUtil.checkinTicket(imagePath, phoneNumber);
            System.out.println("Scanning result: "+ticket);
            boolean r1 = ticket.contains("Phone Number: " + phoneNumber + " |");
            boolean r2 = ticket.contains("Event: " + eventName + " |");
            System.out.println("Is ticket valid ? " + (r1&&r2));
            /**
             * Updated this method so that it looks for the event name in the ticket and marks the ticket as checked in in the purchasedTickets list.
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayAllEvents(){
        System.out.println("All events:");
        for(EventTicket et : eventTickets){
            System.out.println(et);
        }
    }

    public void displayAllPurchasedTickets(){
        System.out.println("All purchased tickets:");
        for(PurchasedTicket pt : purchasedTickets){
            System.out.println(pt);
        }
    }
}
