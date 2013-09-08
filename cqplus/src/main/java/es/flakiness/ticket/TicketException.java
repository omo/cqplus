package es.flakiness.ticket;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class TicketException extends RuntimeException {
    public TicketException() {
    }

    public TicketException(String detailMessage) {
        super(detailMessage);
    }

    public TicketException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TicketException(Throwable throwable) {
        super(throwable);
    }
}
