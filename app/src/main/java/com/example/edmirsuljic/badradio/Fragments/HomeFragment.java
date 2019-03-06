package com.example.edmirsuljic.badradio.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edmirsuljic.badradio.R;

public class HomeFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_start, container, false);

        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id. radio_list);
        recyclerView.setHasFixedSize(true);

        return inflate;
    }


    @Override
    public void onClick(View v) {

    }
}
