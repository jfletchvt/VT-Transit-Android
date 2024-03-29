package com.cs2114.vttransit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class for storing information about a specific route, identified by its
 * fullName and shortName
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class Route
{
    private String fullName;
    private String shortName;


    /**
     * A constructor to initialize all of the fields, takes two params
     *
     * @param fullN
     *            -> a String for the fullName of the route
     * @param shortN
     *            -> a String for the route's shortName (aka its code)
     */
    public Route(String fullN, String shortN)
    {
        fullName = fullN;
        shortName = shortN;
    }


    /**
     * A getter for the fullName field
     *
     * @return String -> the fullName field
     */
    public String getFullName()
    {
        return fullName;
    }


    /**
     * A getter for the shortName field (aka the route's code)
     *
     * @return String -> the shortName field
     */
    public String getShortName()
    {
        return shortName;
    }


    /**
     * Sets the route short name for a Route.
     *
     * @param shortN
     *            -> a string of the shortName for a Route
     */
    public void setShortName(String shortN)
    {
        shortName = shortN;
    }


    /**
     * Gets all of the Blacksburg Transit Routes from a text file.
     *
     * @return a list of routes
     * @throws FileNotFoundException
     */
    public static List<Route> allRoutes()
        throws FileNotFoundException
    {
        List<Route> allRoutes = new ArrayList<Route>();
        // get the text file being passed in
        File inputFile = new File("assets/routes.txt");
        // create a scanner for that text file
        Scanner fileScanner = new Scanner(inputFile);
        while (fileScanner.hasNext())
        {
            allRoutes.add(new Route(fileScanner.nextLine(), null));
        }
        inputFile = new File("assets/shortroutenames.txt");
        fileScanner = new Scanner(inputFile);
        for (int i = 0; i < allRoutes.size(); i++)
        {
            allRoutes.get(i).setShortName(fileScanner.nextLine());
        }
        for (int i = 0; i < allRoutes.size(); i++)
        {
            System.out.println(allRoutes.get(i).getFullName() + " "
                + allRoutes.get(i).getShortName());
        }
        return allRoutes;
    }
}
