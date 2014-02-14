package com.cs2114.vttransit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Handles switching fragments in the MainActivity
 * 
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

	/**
	 * Constructor for this class
	 * 
	 * @param fm
	 *            -> which fragments will this manager
	 */
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/**
	 * What to create in each tab of the bar
	 * 
	 * @param index
	 *            -> an int number of items in the bar
	 * @return Fragment -> the fragment to be created in the tab
	 */
	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			// Favorite fragment activity
			return new RoutesFragment();
		case 1:
			// Routes fragment activity
			return new FavoritesFragment();
		case 2:
			// Maps fragment activity
			return new MapFragment();
		}
		return null;
	}

	/**
	 * How many items in tabs?
	 * 
	 * @return int -> the number of items
	 */
	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}
}
