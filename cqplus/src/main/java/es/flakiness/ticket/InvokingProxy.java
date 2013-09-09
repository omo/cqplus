package es.flakiness.ticket;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class InvokingProxy implements Proxy {

    private ServiceInvoker mInvoker;
    private Service mService;

    public InvokingProxy(ServiceInvoker invoker, Service service) {
        mInvoker = invoker;
        mService = service;
    }

    @Override
    public Ticket call(Object request) {
        return mInvoker.ask(mService, request);
    }
}
