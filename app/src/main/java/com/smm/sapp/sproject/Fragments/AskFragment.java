package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smm.sapp.sproject.Adapters.AskUsAdapter;
import com.smm.sapp.sproject.Models.AskUs;
import com.smm.sapp.sproject.R;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class AskFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<AskUs> askUses;
    ImageView ic_back;

    public AskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.ask_us);
        ic_back = getView().findViewById(R.id.ic_back);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        askUses = new ArrayList<>();
        recyclerView.setAdapter(new AskUsAdapter(getContext(), R.layout.layout_item_ask, askUses));

        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }
}
