package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentAccountFragment extends Fragment {

    /**
     * 0.00
     */
    private TextView mHangingCuBalance;
    /**
     * 0.00
     */
    private TextView mTotalCuBalance;
    /**
     * 0.00
     */
    private TextView mRetractableCuBalance;
    /**
     * 0.00
     */
    private TextView mAvailableCuBalance;

    public CurrentAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_account, container, false);
    }

    private void initView() {
        mHangingCuBalance = getView().findViewById(R.id.hanging_cu_balance);
        mTotalCuBalance = getView().findViewById(R.id.total_cu_balance);
        mRetractableCuBalance = getView().findViewById(R.id.retractable_cu_balance);
        mAvailableCuBalance = getView().findViewById(R.id.available_cu_balance);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
    }


}
