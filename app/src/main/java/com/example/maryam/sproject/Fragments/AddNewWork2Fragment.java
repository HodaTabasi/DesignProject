package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.Adapters.ClientJobAdapter;
import com.example.maryam.sproject.HelperClass.FragmentsUtil;
import com.example.maryam.sproject.Models.ClientJobs;
import com.example.maryam.sproject.R;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewWork2Fragment extends Fragment {

    TextView title1 ;
    RecyclerView mnewWorkRes;
    LinearLayoutManager layoutManager;
    List<ClientJobs> jobs;


    public AddNewWork2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_work2, container, false);
    }

    private void initView() {
         title1 = getView().findViewById(R.id.title1);
        mnewWorkRes = getView().findViewById(R.id.new_work_res);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mnewWorkRes.setLayoutManager(layoutManager);
        jobs = new ArrayList<>();
        mnewWorkRes.setAdapter(new ClientJobAdapter(getContext(), R.layout.layout_item_new_work2, jobs));

        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddNewWorkFragment(), true);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
    }
}
