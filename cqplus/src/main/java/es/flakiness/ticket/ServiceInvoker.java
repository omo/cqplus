package es.flakiness.ticket;

/**
 * Created by morrita on 9/9/13.
 */
public interface ServiceInvoker {
    public Ticket ask(Service service, Object request);
}
