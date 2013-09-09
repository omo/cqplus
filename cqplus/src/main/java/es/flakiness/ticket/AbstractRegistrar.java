package es.flakiness.ticket;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class AbstractRegistrar implements ServiceInvoker {

    public Proxy register(Service service) {
        return new RegisteredProxy(this, service);
    }

    public Ticket ask(Service service, Object request) {
        return new ServiceInvocation(service, request);
    }
}
