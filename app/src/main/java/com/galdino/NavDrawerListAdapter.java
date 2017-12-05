package com.galdino;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.galdino.examplemenulateral.HomeSideMenuItem;
import com.galdino.examplemenulateral.R;

import java.util.ArrayList;

/**
 * Created by galdino on 05/12/17.
 */

public class NavDrawerListAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<HomeSideMenuItem> homeSideMenuItems;

    public NavDrawerListAdapter(Context context, ArrayList<HomeSideMenuItem> homeSideMenuItems){
        this.context = context;
        this.homeSideMenuItems = homeSideMenuItems;
    }

    @Override
    public int getCount() {
        return homeSideMenuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return homeSideMenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.home_side_menu_list_item, null);
        }

        ImageView imgIcon = convertView.findViewById(R.id.icon);
        TextView txtTitle = convertView.findViewById(R.id.title);
        TextView txtCount = convertView.findViewById(R.id.counter);

        imgIcon.setImageResource(homeSideMenuItems.get(position).getIcon());
        txtTitle.setText(homeSideMenuItems.get(position).getTitle());

        // displaying count
        // check whether it set visible or not
        if(homeSideMenuItems.get(position).getCounterVisibility()){
            txtCount.setText(homeSideMenuItems.get(position).getCount());
        }else{
            // hide the counter view
            txtCount.setVisibility(View.GONE);
        }

        return convertView;
    }

}

