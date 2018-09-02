package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maryam.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class RetractableBalanceFragment extends Fragment {


    private View view;
    /** 0.00 */
    private TextView mRetractableRetBalance;
    /** إرسال إلى حسابك البنكي */
    private TextView mSendRetractableRetBalance;

    public RetractableBalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retractable_balance, container, false);
    }

    private void initView() {
        mRetractableRetBalance = getView().findViewById(R.id.retractable_ret_balance);
        mSendRetractableRetBalance = getView().findViewById(R.id.send_retractable_ret_balance);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
    }

}
