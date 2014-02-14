package com.cs2114.vttransit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.cs2114.vttransit.R;
import com.cs2114.vttransit.TBXML.TBXMLException;
import android.content.SharedPreferences;
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
 * The fragment that handles the "favorites" tab. Swtiched to via the
 * tabsPagerAdapter
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class FavoritesFragment extends Fragment {
	private ListView listview;
	private View rootView;
	private boolean whichScreen; // determines if on routes or stops
	private Route currentRoute;
	private List<Stop> stopList;

	/**
	 * Handles what to do when the view is freshly created
	 *
	 * @return View -> the created view
	 * @param inflater
	 *            -> the LayoutInflater
	 * @param container
	 *            -> the viewGroup of all views
	 * @param savedInstanceState
	 *            -> the Bundle on which to create
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_favorites, container,
				false);
		// now you must initialize your list view
		listview = (ListView) rootView.findViewById(R.id.list);

		SharedPreferences pref = getActivity().getApplicationContext()
				.getSharedPreferences("MyPref", 0);
		currentRoute = new Route(pref.getString("RouteName", ""),
				pref.getString("ShortName", ""));
		String[] items = new String[] { currentRoute.getFullName() };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.text_view, items);
		whichScreen = false;
		listview.setAdapter(adapter);
		listview.setClickable(true);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			/**
			 * Determines what to do when an item in the listView is clicked
			 *
			 * @param arg0
			 *            -> the AdapterView
			 * @param view
			 *            -> this view
			 * @param position
			 *            -> Which item in the listView by int
			 * @param arg3
			 *            -> the long position
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
				if (!whichScreen) {
					Parser thisParser = new Parser();
					List<Stop> currentStops = new ArrayList<Stop>();
					try {
						currentStops = thisParser.getStopsOnRoute(currentRoute);
					} catch (TBXMLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					stopList = currentStops;
					ArrayList<String> stopNames = new ArrayList<String>();
					for (Stop s : currentStops) {
						stopNames.add(s.getStopName());
					}
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
							getActivity(), R.layout.text_view, stopNames);
					listview.setAdapter(adapter1);
					whichScreen = true;
				} else {
					Parser thisParser = new Parser();
					List<String> timesList = new ArrayList<String>();
					try {
						timesList = thisParser.getTimesForRouteAndStop(
								currentRoute, stopList.get(position));
					} catch (TBXMLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					timesList = thisParser.makeTimesMoreReadable(timesList);
					if (timesList.size() == 0) {
						timesList
								.add("There are currently no times on this route, sorry");
					}
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
							getActivity(), R.layout.text_view, timesList);
					listview.setAdapter(adapter1);
					whichScreen = false;
				}
			}
		});
		return rootView;
	}
}