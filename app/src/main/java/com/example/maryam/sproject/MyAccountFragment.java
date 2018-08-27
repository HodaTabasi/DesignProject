package com.example.maryam.sproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment implements View.OnClickListener{



    private LinearLayout mReport;
    private LinearLayout mCurrentAccount;
    private LinearLayout mRechargeBalance;
    private LinearLayout mDropBalance;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.report:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.activity_container,new ReportFragment());
                break;
            case R.id.current_account:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.activity_container,new CurrentAccountFragment());
                break;
            case R.id.recharge_balance:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.activity_container,new ShippingBalanceFragment());
                break;
            case R.id.drop_balance:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.activity_container,new RetractableBalanceFragment());
                break;
        }
    }
}
