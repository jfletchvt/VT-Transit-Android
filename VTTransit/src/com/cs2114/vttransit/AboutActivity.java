package com.cs2114.vttransit;

import com.cs2114.vttransit.R;
import android.app.Activity;
import android.os.Bundle;

/**
 * A blank activity that simply contains a textView with info about the creators
 * and a simple usage guide
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class AboutActivity extends Activity {
	/**
	 * A method for setting up the activity when it is created
	 *
	 * @param savedInstanceState
	 *            -> the Bundle on which to generate
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_main);
	}
}
