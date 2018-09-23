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
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.Adapters.MyProjectsProposalsAdapter;
import com.smm.sapp.sproject.Models.MyProjectsProposals;
import com.smm.sapp.sproject.R;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOffersFragment extends Fragment implements View.OnClickListener {



    /** المستبعدة */
    private TextView mAllOfferExcluded;
    /** المكتملة */
    private TextView mAllOfferDone;
    /** قيد التنفيذ */
    private TextView mAllOfferUnderway;
    /** بانتظار الموافقة */
    private TextView mAllOfferWait;
    private RecyclerView mAllOfferRes;
    ImageView ic_back;

    LinearLayoutManager layoutManager;
    List<MyProjectsProposals> projectsProposals;

    public MyOffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_offers, container, false);
    }

    private void initView() {
        mAllOfferExcluded = getView().findViewById(R.id.all_offer_excluded);
        mAllOfferDone = getView().findViewById(R.id.all_offer_done);
        mAllOfferUnderway = getView().findViewById(R.id.all_offer_underway);
        mAllOfferWait = getView().findViewById(R.id.all_offer_wait);
        mAllOfferRes = getView().findViewById(R.id.all_offer_res);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mAllOfferRes.setLayoutManager(layoutManager);
        projectsProposals = new ArrayList<>();
        mAllOfferRes.setAdapter(new MyProjectsProposalsAdapter(getContext(),R.layout.fav_row,projectsProposals));
        ic_back = getView().findViewById(R.id.ic_back);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });


        // Bundle bundle = getArguments();
       /* int flag = bundle.getInt("flag");
        if (flag == 0){
            Toast.makeText(getContext(), "done recycle", Toast.LENGTH_SHORT).show();
        }else  if (flag == 1){
            Toast.makeText(getContext(), "wait recycle", Toast.LENGTH_SHORT).show();
        }else if (flag == 2){
            Toast.makeText(getContext(), "excluded recycle", Toast.LENGTH_SHORT).show();
        }else if (flag == 3){
            Toast.makeText(getContext(), "underway recycle", Toast.LENGTH_SHORT).show();
        }*/
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.all_offer_excluded:
                break;
            case R.id.all_offer_done:
                break;
            case R.id.all_offer_underway:
                break;
            case R.id.all_offer_wait:
                break;
        }
    }
}
