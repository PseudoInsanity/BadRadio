package com.example.edmirsuljic.badradio.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edmirsuljic.badradio.Adapters.RadioAdapter;
import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.RadioRelated.RadioHandler;

public class HomeFragment extends Fragment implements View.OnClickListener {
    RadioHandler radioHandler = new RadioHandler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_start, container, false);

        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id. radio_list);
//        recyclerView.setHasFixedSize(true);

        if (inflate instanceof RecyclerView) {
            Context context = inflate.getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new RadioAdapter(context, radioHandler.getRadioStation()));
        }
        return inflate;
    }


    @Override
    public void onClick(View v) {

    }
}
