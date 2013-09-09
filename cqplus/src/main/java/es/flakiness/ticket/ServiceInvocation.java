package es.flakiness.ticket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class ServiceInvocation implements Ticket {

    private Method mServiceMethod;
    private Service mService;
    private Object mRequest;
    private Object mResponse;
    private Method mWillMethod;
    private Will mWill; // This should be accessed only from the caller thread.

    public ServiceInvocation(Service service, Object request) {
        mServiceMethod = findRequestMethod(service, request.getClass());
        mService = service;
        mRequest = request;
    }

    @Override
    public void then(Will will) {
        mWillMethod = findWillMethod(will);
        mWill = will;
    }

    public void request() {
        try {
            mResponse = mServiceMethod.invoke(mService, mRequest);
        } catch (IllegalAccessException e) {
            // FIXME: wrap and propagate exception
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // FIXME: wrap and propagate exception
            e.printStackTrace();
        }
    }

    public void respond() {
        if (null != mWill)
            return;
        try {
            mWillMethod.invoke(mWill, mResponse);
        } catch (IllegalAccessException e) {
            // FIXME: wrap and propagate exception
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // FIXME: wrap and propagate exception
            e.printStackTrace();
        }
    }

    public Method findWillMethod(Will will) {
        Class expectedParameterClass = mServiceMethod.getReturnType();
        for (Method m : will.getClass().getMethods()) {
            if (!Modifier.isPublic(m.getModifiers()))
                continue;
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (parameterTypes.length != 1)
                continue;
            if (parameterTypes[0].equals(expectedParameterClass))
                return m;
        }

        throw new TicketException("The will doesn't have any receiver for %s".format(expectedParameterClass.getName()));
    }

    // FIXME: this result should be memoized.
    static public Method findRequestMethod(Service service, Class parameterClass) {
        for (Method m : parameterClass.getMethods()) {
            // FIMXE: Better to have an annotation.
            if (!Modifier.isPublic(m.getModifiers()))
                continue;
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (parameterTypes.length != 1)
                continue;
            for (Class pt : parameterTypes) {
                if (pt.equals(parameterClass))
                    return m;
            }
        }

        throw new TicketException("No service method for %s(%s)".format(service.getClass().getName(), parameterClass.getName()));
    }
}
