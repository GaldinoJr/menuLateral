package com.galdino.examplemenulateral.domain;

import com.galdino.examplemenulateral.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by galdino on 05/12/17.
 */

public class HomeSideMenu
{
    private List<HomeSideMenuItem> mList;
    private void createList()
    {
        mList = new LinkedList<>();
        mList.add(new HomeSideMenuItem(HomeSideMenuItem.FIRST_SCREEN_ID, R.string.fragment_1,R.drawable.ic_1));
        mList.add(new HomeSideMenuItem(HomeSideMenuItem.SECOND_SCREEN_ID, R.string.fragment_2,R.drawable.ic_2));
    }

    public List<HomeSideMenuItem> getSideMenuList()
    {
        createList();
        return mList;
    }
}
