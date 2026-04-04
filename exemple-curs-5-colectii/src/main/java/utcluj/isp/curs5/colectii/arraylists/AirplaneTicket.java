package utcluj.isp.curs5.colectii.arraylists;

import java.util.Objects;

public class AirplaneTicket {

    // Instance variables
    private String passengerName;
    private String departureCity;
    private String arrivalCity;
    private String flightNumber;
    private double ticketPrice;

    // Constructor
    public AirplaneTicket(String passengerName, String departureCity, String arrivalCity, String flightNumber, double ticketPrice) {
        this.passengerName = passengerName;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.flightNumber = flightNumber;
        this.ticketPrice = ticketPrice;
    }

    // Getters and setters for instance variables
    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneTicket that = (AirplaneTicket) o;
        return Objects.equals(passengerName, that.passengerName) && Objects.equals(flightNumber, that.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerName, flightNumber);
    }

    // toString method to display object as string
    public String toString() {
        return "Passenger Name: " + passengerName + "\nDeparture City: " + departureCity +
                "\nArrival City: " + arrivalCity + "\nFlight Number: " + flightNumber +
                "\nTicket Price: $" + ticketPrice;
    }
}
