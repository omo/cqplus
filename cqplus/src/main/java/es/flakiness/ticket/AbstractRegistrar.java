package es.flakiness.ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class AbstractRegistrar {

    public Proxy register(Service service) {
        return new RegisteredProxy(this, service);
    }

    public Ticket ask(Service service, Object request) {
        return null;
    }
}
