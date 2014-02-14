package com.cs2114.vttransit;

/**
 * Test class for Route.
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class RouteTest
    extends student.TestCase
{
    private Route route;


    /**
     * Creates a new route to use in tests.
     */
    public void setUp()
    {
        route = new Route("Main Street North", "MSN");
    }


    /**
     * Tests the static method to get all of the routes from a text file.
     */
    public void testAllRoutes()
    {
        Exception e = null;
        try
        {
            Route.allRoutes();
        }
        catch (Exception ex)
        {
            e = ex;
        }
        assertNull(e);
    }


    /**
     * Tests the getters for the two fields in a Route.
     */
    public void testGetters()
    {
        assertTrue(route.getFullName().equals("Main Street North"));
        assertTrue(route.getShortName().equals("MSN"));
    }

}
