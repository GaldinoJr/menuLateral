package com.galdino.examplemenulateral;

import android.app.Fragment;
import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.Pair;

import com.galdino.examplemenulateral.adapter.HomeSideMenuAdapter;
import com.galdino.examplemenulateral.databinding.ActivityMainBinding;
import com.galdino.examplemenulateral.domain.HomeSideMenu;
import com.galdino.examplemenulateral.domain.HomeSideMenuItem;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding mBinding;

    private HomeSideMenuAdapter mAdapter;
    private static boolean alreadyOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        HomeSideMenu homeSideMenu = new HomeSideMenu();
        mBinding.rvSideMenu.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeSideMenuAdapter();
        mAdapter.setData(homeSideMenu.getSideMenuList());
        mAdapter.observableItemPairClick().subscribe(onSideMenuItemClick);
        mBinding.rvSideMenu.setAdapter(mAdapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("Already Open", "" + alreadyOpen);
        if(!alreadyOpen)
        {
            mBinding.drawerLayout.openDrawer(mBinding.drawerView);
            Handler h = new Handler();
            h.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    mBinding.drawerLayout.closeDrawer(mBinding.drawerView);
                }
            }, 2000);

            alreadyOpen = true;
        }
    }

    // Click
    Consumer<Pair<HomeSideMenuItem, Integer>> onSideMenuItemClick = pair ->
    {
        HomeSideMenuItem homeSideMenuItem = pair.first;
        int position = pair.second;
        Fragment fragment = null;
        setTitle(getString(homeSideMenuItem.getTitleResourceId()));
        switch (homeSideMenuItem.getId())
        {
            case HomeSideMenuItem.FIRST_SCREEN_ID:
                fragment = new FirstFragment();
                break;
            case HomeSideMenuItem.SECOND_SCREEN_ID:
                fragment = new SecondFragment();
                break;
        }
        openFragment(fragment);
    };

    private void openFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout)
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
//            mDrawerList.setItemChecked(position, true);
//            mDrawerList.setSelection(position);
            mBinding.drawerLayout.closeDrawer(mBinding.drawerView);
        }
        else
        {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
