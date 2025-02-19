import java.util.*;
class TicketBookingSystem {
    private final boolean[] seats;

    public TicketBookingSystem(int totalSeats) {
        this.seats = new boolean[totalSeats];
    }

    public synchronized boolean bookSeat(int seatNumber, String customer) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println(customer + " - Invalid seat number: " + seatNumber);
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customer + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customer + " - Seat " + seatNumber + " is already booked.");
            return false;
        }
    }
}
class CustomerThread extends Thread {
    private final TicketBookingSystem bookingSystem;
    private final int seatNumber;
    private final String customerName;
    public CustomerThread(TicketBookingSystem bookingSystem, int seatNumber, String customerName, int priority) {
        this.bookingSystem = bookingSystem;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        this.setPriority(priority);
    }
    public void run() {
        bookingSystem.bookSeat(seatNumber, customerName);
    }
}
public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(10);
        List<Thread> customers = new ArrayList<>();
        customers.add(new CustomerThread(bookingSystem, 2, "VIP_John", Thread.MAX_PRIORITY));
        customers.add(new CustomerThread(bookingSystem, 2, "Regular_Alice", Thread.NORM_PRIORITY));
        customers.add(new CustomerThread(bookingSystem, 5, "VIP_Mary", Thread.MAX_PRIORITY));
        customers.add(new CustomerThread(bookingSystem, 5, "Regular_Bob", Thread.NORM_PRIORITY));
        for (Thread customer : customers) {
            customer.start();
        }
        for (Thread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
