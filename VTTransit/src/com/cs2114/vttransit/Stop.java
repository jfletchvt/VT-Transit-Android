package com.cs2114.vttransit;

/**
 * A class that handles information about a particular bus stop, as identified
 * by name and code
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/2013
 */
public class Stop
{
    private String stopName;
    private String stopCode;


    /**
     * A constructor that takes two params, the stopName and the stopCode
     *
     * @param stopN
     *            -> a string for the stopName
     * @param stopC
     *            -> an int for the stopCode
     */
    public Stop(String stopN, String stopC)
    {
        stopName = stopN;
        stopCode = stopC;
    }


    /**
     * A getter for the stopName
     *
     * @return String -> the stopName
     */
    public String getStopName()
    {
        return stopName;
    }


    /**
     * A getter for the stopCode
     *
     * @return int -> the stopCode
     */
    public String getStopCode()
    {
        return stopCode;
    }

}
