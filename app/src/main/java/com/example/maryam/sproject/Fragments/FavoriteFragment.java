package com.example.maryam.sproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maryam.sproject.Adapters.FavoritePortfolioAdapter;
import com.example.maryam.sproject.Models.FavoritePortfolioModel;
import com.example.maryam.sproject.R;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;


public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FavoritePortfolioModel> arrayList = new ArrayList<>();
    FavoritePortfolioAdapter adapter;

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = view.findViewById(R.id.recycler_fav);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new FavoritePortfolioAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
    }
}
