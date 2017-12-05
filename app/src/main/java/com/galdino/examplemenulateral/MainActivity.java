package com.galdino.examplemenulateral;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.galdino.NavDrawerListAdapter;
import com.galdino.examplemenulateral.adapter.HomeSideMenuAdapter;
import com.galdino.examplemenulateral.domain.HomeSideMenuItem;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    private View mDrawerView;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<HomeSideMenuItem> homeSideMenuItems;
//    private NavDrawerListAdapter adapter;
    private HomeSideMenuAdapter mAdapter;
    private static boolean alreadyOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.list_slidermenu);
        mDrawerView = findViewById(R.id.drawer_view);

        homeSideMenuItems = new ArrayList<>();

        // adding nav drawer items to array
        // Home
        homeSideMenuItems.add(new HomeSideMenuItem(navMenuTitles[0], FirstFragment.));
        // Find People
        homeSideMenuItems.add(new HomeSideMenuItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeSideMenuAdapter();
        mAdapter.setData(homeSideMenuItems);
        mAdapter.observableItemPairClick().subscribe()
        if (savedInstanceState == null)
        {
            displayView(0);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Log.i("Already Open", "" + alreadyOpen);

        if(!alreadyOpen)
        {
            mDrawerLayout.openDrawer(mDrawerView);
            Handler h = new Handler();
            h.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    mDrawerLayout.closeDrawer(mDrawerView);
                }
            }, 2000);

            alreadyOpen = true;
        }
    }

//    private void initNavigationDrawer()
//    {
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.navigation_view);
//
//        if (navigationView != null)
//        {
//            navigationView.setNavigationItemSelectedListener(this);
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        item.setChecked(true);
//        drawerLayout.closeDrawers();
//
//        return true;
//    }

//    private class SlideMenuClickListener implements ListView.OnItemClickListener
//    {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//        {
//            // display view for selected nav drawer item
//            displayView(position);
//        }
//    }

    // Click
    Consumer<HomeSideMenuItem> onSideMenuItemClick = homeSideMenuItem ->
    {
        homeSideMenuItem.getId();
    };

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = new FirstFragment();
                break;

            case 1:
                fragment = new SecondFragment();
                break;

            default:
                break;
        }

        if (fragment != null)
        {
            FragmentManager fragmentManager = getFragmentManager();

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
            {
                fragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout)
                        .replace(R.id.frame_container, fragment).commit();
            }
            else
            {
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
            }

            // update selected item and title, then close the drawer
//            mDrawerList.setItemChecked(position, true);
//            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerView);
        }
        else
        {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
