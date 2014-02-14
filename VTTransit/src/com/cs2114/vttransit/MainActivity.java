package com.cs2114.vttransit;

import com.cs2114.vttransit.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * The main activity on which the rest of the app is built using fragments,
 * except the "AboutActivity" called from the menu button.
 *
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity
    extends FragmentActivity
    implements ActionBar.TabListener
{

    private ViewPager        viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar        actionBar;
    // Tab titles
    private String[]         tabs = { "Routes", "Favorite", "Map" };


    /**
     * What to do when creating this activity
     *
     * @param savedInstanceState
     *            -> the Bundle on which to create
     */
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialization
        viewPager = (ViewPager)findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Adding Tabs
        for (String tabName : tabs)
        {
            actionBar.addTab(actionBar.newTab().setText(tabName)
                .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * A method for handling clicks on the ViewPager
             *
             * @param position
             *            -> which element was clicked by int
             */
            @Override
            public void onPageSelected(int position)
            {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }


            /**
             * A method for handling scrolling
             *
             * @param arg0
             *            -> an int
             * @param arg1
             *            -> a float
             * @param arg2
             *            -> an int
             */
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
                // does nothing be design
            }


            /**
             * A method for handling Scrolling Changes
             *
             * @param arg0
             *            -> an int
             */
            @Override
            public void onPageScrollStateChanged(int arg0)
            {
                // does nothing by design
            }
        });
    }


    /**
     * Handles reselecting tabs, does nothing
     *
     * @param tab
     *            -> which tab clicked
     * @ft -> the transaction to occur
     */
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft)
    {
        // does nothing by design
    }


    /**
     * Handles when a tab is clicked
     *
     * @param tab
     *            -> which tab clicked
     * @param ft
     *            -> the transaction to occur
     */
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft)
    {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }


    /**
     * When the tab is unclicked, does nothing
     *
     * @param tab
     *            -> which tab
     * @param ft
     *            -> the transaction
     */
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft)
    {
        // Do nothing by design
    }


    /**
     * Handles inflating the menu when menu is clicked in the app
     *
     * @param menu
     *            -> menu clicked?
     * @return boolean -> was menu created?
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sharedpreferencesmenu, menu);
        return true;
    }


    /**
     * Handles the buttons in the menu
     *
     * @param item
     *            -> which item was clicked?
     * @return boolean -> was the right action performed?
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save_preferences:
                Route toAddFav = RoutesFragment.getCurrentRoute();
                if (toAddFav == null)
                {
                    Toast.makeText(
                        MainActivity.this,
                        "No Route Selected",
                        Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences pref =
                        getApplicationContext().getSharedPreferences(
                            "MyPref",
                            0);
                    Editor editor = pref.edit();
                    if (pref.getString("RouteName", "") != null)
                    {
                        editor.remove("RouteName");
                        editor.remove("ShortName");
                    }
                    editor.putString("RouteName", toAddFav.getFullName());
                    editor.putString("ShortName", toAddFav.getShortName());
                    ;
                    editor.commit();
                    Toast.makeText(
                        MainActivity.this,
                        toAddFav.getFullName() + " added as favorite route",
                        Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.about_option:
                Intent i =
                    new Intent(getApplicationContext(), AboutActivity.class);
                startActivityForResult(i, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Overrides the back button to work with fragments
     */
    @Override
    public void onBackPressed()
    {
        viewPager = (ViewPager)findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        RoutesFragment.resetScreen();
    }
}
