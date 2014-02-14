package com.cs2114.vttransit;

/**
 * Test class for Stop.
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */

public class StopTest
    extends student.TestCase
{

    private Stop stop;
    // ----------------------------------------------------------
    /**
     * Creates a new stop to use in tests.
     */
    public void setUp()
    {
        stop = new Stop("Burruss Hall", "1305");
    }


    /**
     * Tests the two getters for the two fields in Stop
     */
    public void testGetters()
    {
        assertTrue(stop.getStopName().equals("Burruss Hall"));
        assertTrue(stop.getStopCode().equals("1305"));
    }

}
