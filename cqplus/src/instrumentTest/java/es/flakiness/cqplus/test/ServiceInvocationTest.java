package es.flakiness.cqplus.test;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import es.flakiness.ticket.Proxy;
import es.flakiness.ticket.Service;
import es.flakiness.ticket.ServiceInvocation;
import es.flakiness.ticket.ServiceRegistrar;
import es.flakiness.ticket.Ticket;
import es.flakiness.ticket.Will;

/**
 * Created by morrita on 9/9/13.
 */
public class ServiceInvocationTest extends TestCase {

    static public class TestingRegistrar extends ServiceRegistrar {
        public List<ServiceInvocation> mInvocations = new ArrayList();

        @Override
        protected ServiceInvocation createTicket(Service service, Object request) {
            ServiceInvocation ticket = super.createTicket(service, request);
            mInvocations.add(ticket);
            return ticket;
        }

        public ServiceInvocation getFirstInvocation() {
            return mInvocations.get(0);
        }
    }

    static public class TestingService implements Service {
        static public class GreetingRequest {

            public GreetingRequest(String mMessage) {
                this.mMessage = mMessage;
            }

            public String getMessage() {
                return mMessage;
            }

            public void setMessage(String mMessage) {
                this.mMessage = mMessage;
            }

            private String mMessage;
        }

        public String greet(GreetingRequest request) {
            return "Hello, " + request.getMessage();
        }
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    private String mTestHelloResult;

    public void testHello() {
        TestingRegistrar registrar = new TestingRegistrar();
        TestingService service = new TestingService();
        Proxy proxy = registrar.register(service);

        proxy.call(new TestingService.GreetingRequest("How Are You?"))
                .then(new Will() {
                    public void fullfill(String response) {
                        mTestHelloResult = response;
                    }
                });


        ServiceInvocation invocation = registrar.getFirstInvocation();
        invocation.request();
        assertEquals(mTestHelloResult, null);
        invocation.respond();
        assertEquals(mTestHelloResult, "Hello, How Are You?");
    }
}
