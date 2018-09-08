package com.example.maryam.sproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maryam.sproject.Adapters.BankAdapter;
import com.example.maryam.sproject.Models.BankModel;
import com.example.maryam.sproject.R;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;


public class BankFragment extends Fragment {

    RecyclerView recyclerView;
    TextView tv_add_account, tv_save;
    ArrayList<BankModel> arrayList = new ArrayList<>();
    BankAdapter adapter;

    public BankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank, container, false);

        recyclerView = view.findViewById(R.id.recycler_bank);
        tv_add_account = view.findViewById(R.id.tv_add_account);
        tv_save = view.findViewById(R.id.tv_save);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrayList.add(new BankModel("البنك العربي","2138","1534"));

        adapter = new BankAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        tv_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
    }
}
