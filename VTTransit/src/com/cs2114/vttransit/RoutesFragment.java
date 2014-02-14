package com.cs2114.vttransit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.cs2114.vttransit.R;
import com.cs2114.vttransit.TBXML.TBXMLException;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * The routes fragment, which is switched to via the TabsPagerAdapter on the
 * MainActivity.
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class RoutesFragment
    extends Fragment
{

    private ListView       listview;
    private View           rootView;
    private final String[] routeNames      = new String[] { "BT Commuter",
        "CRC Hospital", "CRC Shuttle", "Harding Avenue", "Hethwood",
        "Hokie Express", "Main Street North", "Main Street South",
        "Patrick Henry", "Progress Street", "The Explorer", "Toms Creek",
        "Two Town Trolley", "University City Boulevard", "University Mall" };
    private final String[] routeShortNames = new String[] { "BTC", "CRCH",
        "CRC", "HDG", "HWD", "HXP", "MSN", "MSS", "PHD", "PRG", "TE", "TC",
        "TTT", "UCB", "UMS"               };
    private static Route   currentRoute;
    private Stop           currentStop;
    private static int     screenIndex;
    private List<Stop>     stopList;


    /**
     * Used for constructing the fragment
     */
    public RoutesFragment()
    {
        currentRoute = null;
        currentStop = null;
        screenIndex = 0;
    }


    /**
     * Used for building the fragment on the MainActivity
     *
     * @return View -> this view
     * @param inflater
     *            -> the LayoutInflater for this fragment
     * @param container
     *            -> the ViewGroup this fragment is in
     * @param savedInstanceState
     *            -> the Bundle on which to build
     */
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.fragment_routes, container, false);

        // now you must initialize your list view
        listview = (ListView)rootView.findViewById(R.id.list);

        ArrayAdapter<String> adapter =
            new ArrayAdapter<String>(
                getActivity(),
                R.layout.text_view,
                routeNames);

        listview.setAdapter(adapter);

        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Handles when an item in the listView is clicked
             *
             * @param arg0
             *            -> the AdapterView
             * @param view
             *            -> this view
             * @param position
             *            -> the position of the clicked view by in
             * @param arg3
             *            -> the long position
             */
            @Override
            public void onItemClick(
                AdapterView<?> arg0,
                View view,
                int position,
                long arg3)
            {
                StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                if (screenIndex == 0)
                {
                    String fullName = routeNames[position];
                    String shortCode = routeShortNames[position];
                    Parser thisParser = new Parser();
                    Route thisRoute = new Route(fullName, shortCode);
                    currentRoute = thisRoute;
                    List<Stop> currentStops = new ArrayList<Stop>();
                    try
                    {
                        currentStops = thisParser.getStopsOnRoute(thisRoute);
                    }
                    catch (TBXMLException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    stopList = currentStops;
                    ArrayList<String> stopNames = new ArrayList<String>();
                    for (Stop s : currentStops)
                    {
                        stopNames.add(s.getStopName());
                    }
                    ArrayAdapter<String> adapter1 =
                        new ArrayAdapter<String>(
                            getActivity(),
                            R.layout.text_view,
                            stopNames);
                    listview.setAdapter(adapter1);
                    screenIndex++;
                }
                else if (screenIndex == 1)
                {
                    currentStop = stopList.get(position);
                    Parser thisParser = new Parser();
                    List<String> timesList = new ArrayList<String>();
                    try
                    {
                        timesList =
                            thisParser.getTimesForRouteAndStop(
                                currentRoute,
                                currentStop);
                    }
                    catch (TBXMLException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    timesList = thisParser.makeTimesMoreReadable(timesList);
                    if (timesList.size() == 0)
                    {
                        timesList
                            .add("There are currently no times on this route, sorry");
                    }
                    ArrayAdapter<String> adapter1 =
                        new ArrayAdapter<String>(
                            getActivity(),
                            R.layout.text_view,
                            timesList);
                    listview.setAdapter(adapter1);
                    screenIndex++;
                }
            }
        });

        return rootView;
    }


    /**
     * Resets screen index.
     */
    static void resetScreen()
    {
        screenIndex = 0;
    }

    /**
     * A static method such that it can interact with the favorites function
     *
     * @return Route -> the current Route a user is in
     */
    static Route getCurrentRoute()
    {
        return currentRoute;
    }
}
