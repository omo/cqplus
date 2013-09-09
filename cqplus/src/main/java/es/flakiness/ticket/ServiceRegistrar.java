package es.flakiness.ticket;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class ServiceRegistrar implements ServiceInvoker {

    public Proxy register(Service service) {
        return createProxy(service);
    }

    public Ticket ask(Service service, Object request) {
        return createTicket(service, request);
    }

    protected ServiceInvocation createTicket(Service service, Object request) {
        return new ServiceInvocation(service, request);
    }

    protected RegisteredProxy createProxy(Service service) {
        return new RegisteredProxy(this, service);
    }
}
