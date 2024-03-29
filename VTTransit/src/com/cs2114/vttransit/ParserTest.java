package com.cs2114.vttransit;

import java.io.IOException;
import java.util.List;
import com.cs2114.vttransit.TBXML.TBXMLException;

/**
 * Test class for Route.
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */

public class ParserTest
    extends student.TestCase
{
    private Parser parser;


    /**
     * Creates a new parser to test.
     */
    public void setUp()
    {
        parser = new Parser();
    }


    /**
     * Tests the parser to get times for a route and a stop.
     */
    public void testGetTimes()
    {
        Route route = new Route("Tom's Creek", "TC");
        Stop stop = new Stop("Progress/Broce Nbnd", "1308");
        List<String> result = null;
        try
        {
            result = parser.getTimesForRouteAndStop(route, stop);
        }
        catch (TBXMLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("TIMES!!!!");
        result = parser.makeTimesMoreReadable(result);
        for (int i = 0; i < result.size(); i++)
        {
            System.out.println(result.get(i));
        }
        assertTrue(result.get(0).equals("9:54 PM"));
    }


    /**
     * Tests the parser to get stops on a route.
     */
    public void testGetStops()
    {
        Route route = new Route("Tom's Creek", "TC");
        List<Stop> result = null;
        try
        {
            result = parser.getStopsOnRoute(route);
        }
        catch (TBXMLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("STOPS!!!");
        for (int i = 0; i < result.size(); i++)
        {
            System.out.println(result.get(i).getStopCode());
        }
        assertTrue(result.get(0).getStopCode().equals("1124"));
    }
}
