package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.smm.sapp.sproject.Adapters.MyProjectsProposalsAdapter;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.MyProjectsProposals;
import com.smm.sapp.sproject.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProjectFragment extends Fragment implements View.OnClickListener {



    private Switch mSwitchOffer;
    /** المستبعدة */
    private TextView mMyProjectExcluded;
    /** قيد التنفيذ */
    private TextView mMyProjectUnderway;
    /** المكتملة */
    private TextView mMyProjectDone;
    private RecyclerView mMyProjectRes;
    private LinearLayout mOne;
    private CircleImageView mProfileImage;
    private LinearLayout mLWaitProject;
    private LinearLayout mLUnderwayProject;
    private LinearLayout mLExcludedProject;
    private LinearLayout mLDoneProject;
    private LinearLayout mTwo;

    LinearLayoutManager layoutManager;
    List<MyProjectsProposals> projectsProposals;

    public MyProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_project, container, false);
    }

    private void initView() {
        mSwitchOffer = getView().findViewById(R.id.switch_offer);
        mMyProjectExcluded = getView().findViewById(R.id.my_project_excluded);
        mMyProjectUnderway = getView().findViewById(R.id.my_project_underway);
        mMyProjectDone = getView().findViewById(R.id.my_project_done);
        mOne = getView().findViewById(R.id.one);
        mProfileImage = getView().findViewById(R.id.profile_image);
        mLWaitProject = getView().findViewById(R.id.l_wait_project);
        mLUnderwayProject = getView().findViewById(R.id.l_underway_project);
        mLExcludedProject = getView().findViewById(R.id.l_excluded_project);
        mLDoneProject = getView().findViewById(R.id.l_done_project);
        mTwo = getView().findViewById(R.id.two);

        mMyProjectRes = getView().findViewById(R.id.my_project_res);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mMyProjectRes.setLayoutManager(layoutManager);
        projectsProposals = new ArrayList<>();
        mMyProjectRes.setAdapter(new MyProjectsProposalsAdapter(getContext(),R.layout.fav_row,projectsProposals));

    }

    private void onClickMethod(){
        mSwitchOffer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mOne.setVisibility(View.VISIBLE);
                    mTwo.setVisibility(View.GONE);
                }else {
                    mOne.setVisibility(View.GONE);
                    mTwo.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        onClickMethod();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        MyOffersFragment fragment = new MyOffersFragment();
        Bundle bundle = new Bundle();
        switch (id){
            case R.id.l_done_project:
                bundle.putInt("flag",0);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity,fragment );
                break;
            case R.id.l_wait_project:
                bundle.putInt("flag",1);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity,fragment );
                break;
            case R.id.l_excluded_project:
                bundle.putInt("flag",2);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity,fragment );
                break;
            case R.id.l_underway_project:
                bundle.putInt("flag",3);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity,fragment );
                break;
        }
    }
}
