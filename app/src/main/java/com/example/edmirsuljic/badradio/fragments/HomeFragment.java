package com.example.edmirsuljic.badradio.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edmirsuljic.badradio.R;
import com.example.edmirsuljic.badradio.Radio_Related.RadioHandler;
import com.example.edmirsuljic.badradio.Adapters.RadioAdapter;

public class HomeFragment extends Fragment {

    private RadioHandler radioHandler;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RadioAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);

        radioHandler = new RadioHandler();

        buildRecyclerView(inflate);

        return inflate;
    }

    private void buildRecyclerView(final View view) {
        System.out.println("Im in the home fragment");

        recyclerView = view.findViewById(R.id.radio_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new RadioAdapter(radioHandler.getRadioStationList());

        adapter.setOnItemClickListener(new RadioAdapter.OnItemClickListener() {
            @Override
            public void onPlayClicked(int position) {

                Snackbar.make(view, "Clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
