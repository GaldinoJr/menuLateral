package com.galdino.examplemenulateral;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FirstFragment extends Fragment {

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public FirstFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

//        setupToolbar(view);
        return view;
    }

//    private void setupToolbar(View view)
//    {
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);
//
//        final ActionBar bar = activity.getSupportActionBar();
//        if (bar != null)
//        {
//            bar.setDisplayHomeAsUpEnabled(true);
//            bar.setShowHideAnimationEnabled(true);
//            bar.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
//            bar.setTitle(activity.getString(R.string.fragment_1));
//        }
//    }
}
