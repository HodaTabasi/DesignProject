package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment implements View.OnClickListener {


    private TextView mReport;
    private TextView mCurrentAccount;
    private TextView mRechargeBalance;
    private TextView mDropBalance;
    ImageView ic_back;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    private void initView() {
        mReport = getView().findViewById(R.id.report);
        mCurrentAccount = getView().findViewById(R.id.current_account);
        mRechargeBalance = getView().findViewById(R.id.recharge_balance);
        mDropBalance = getView().findViewById(R.id.drop_balance);
        ic_back = getView().findViewById(R.id.ic_back);

        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
    }

    private void addListeners() {
        mReport.setOnClickListener(this);
        mCurrentAccount.setOnClickListener(this);
        mRechargeBalance.setOnClickListener(this);
        mDropBalance.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        addListeners();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.report:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ReportFragment(),true);
                break;
            case R.id.current_account:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new CurrentAccountFragment(),true);
                break;
            case R.id.recharge_balance:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ShippingBalanceFragment(),true);
                break;
            case R.id.drop_balance:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new RetractableBalanceFragment(),true);
                break;
        }
    }
}
