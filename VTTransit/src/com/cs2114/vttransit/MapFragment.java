package com.cs2114.vttransit;

import com.cs2114.vttransit.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment switched to via the TabsPagerAdapter on the MainActivity. Handles
 * displayed the map *if we get time*
 * 
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class MapFragment extends Fragment {

	/**
	 * Handles creating the new mapFragment on the MainActivity
	 * 
	 * @param inflater
	 *            -> the LayoutInflater used
	 * @param container
	 *            -> the viewGroup this fragment is in
	 * @param savedInstanceState
	 *            -> the bundle on which to create
	 * @return View -> this view
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_maps, container,
				false);
		return rootView;
	}

}