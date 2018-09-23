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

import com.smm.sapp.sproject.Adapters.FeedbackAdapter;
import com.smm.sapp.sproject.Models.Comments;
import com.smm.sapp.sproject.Models.FeedbackModel;
import com.smm.sapp.sproject.R;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;


public class FeedbackFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Comments> arrayList = new ArrayList<>();
    FeedbackAdapter adapter;

    ImageView ic_back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        recyclerView = view.findViewById(R.id.recycel_feedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ic_back = view.findViewById(R.id.ic_back);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        arrayList = bundle.getParcelableArrayList("commentsInfo");
        adapter = new FeedbackAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }
}
