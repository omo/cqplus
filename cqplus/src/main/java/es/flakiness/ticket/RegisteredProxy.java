package es.flakiness.ticket;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class RegisteredProxy implements Proxy {

    private AbstractRegistrar mRegistrar;
    private Service mService;

    public RegisteredProxy(AbstractRegistrar registrar, Service service) {
        mRegistrar = registrar;
        mService = service;
    }

    @Override
    public Ticket call(Object request) {
        return mRegistrar.ask(mService, request);
    }
}
