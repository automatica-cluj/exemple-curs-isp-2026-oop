package utcluj.isp.curs3.simpleapps.eventticketsystem;

import com.google.zxing.NotFoundException;
import utcluj.isp.curs3.simpleapps.eventticketsystem.entities.PurchasedTicket;
import utcluj.isp.curs3.simpleapps.eventticketsystem.services.TicketsCreatorValidatorUtil;

import java.io.IOException;
import java.time.LocalDateTime;

public class SomeTests {
    public static void main(String[] args) {
        //Create ticket

          TicketsCreatorValidatorUtil tm = new TicketsCreatorValidatorUtil();
          PurchasedTicket pt = tm.createTicket("CURS2", "mihai@mihai.com", "123", "Curs2 Online", LocalDateTime.now(), "REG", 100, "22-12-10");
          tm.generateElectronicTicket(pt);
        try {
            String str = tm.checkinTicket("C:\\Tickets\\ticket_CURS2.png", "1234567890");
            System.out.println("Ticket for validation: " + str);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }


    }
}
