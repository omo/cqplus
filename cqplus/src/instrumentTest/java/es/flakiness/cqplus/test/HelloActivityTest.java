package es.flakiness.cqplus.test;

import android.test.ActivityInstrumentationTestCase2;

import es.flakiness.cqplus.MainActivity;

/**
 * Created by hajimemorrita on 9/8/13.
 */
public class HelloActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public HelloActivityTest() {
        super(MainActivity.class);
    }

    public void testHello() {
        assertEquals(getActivity().getClass(), MainActivity.class);
    }
}
