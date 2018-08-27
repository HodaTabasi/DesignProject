package com.example.maryam.sproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShippingBalanceFragment extends Fragment implements View.OnClickListener{

    /** تحويل بنكي */
    private TextView mBankShTransfer;
    /** بطاقة ائتمانية */
    private TextView mCreditShCard;
    /** التحويل البنكي   */
    private TextView mTitle;
    /** ارسال  */
    private Button mSendBank;
    /** ارسال  */
    private Button mSendBank1;
    RelativeLayout one ,two;

    public ShippingBalanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shipping_balance, container, false);
    }

    private void initView() {
        mBankShTransfer = getView().findViewById(R.id.bank_sh_transfer);
        mCreditShCard = getView().findViewById(R.id.credit_sh_card);
        mSendBank = getView().findViewById(R.id.send_bank);
        mSendBank1 = getView().findViewById(R.id.send_bank1);
        one = getView().findViewById(R.id.sh_one);
        two = getView().findViewById(R.id.sh_two);
    }
    private void addListeners() {
        mBankShTransfer.setOnClickListener(this);
        mCreditShCard.setOnClickListener(this);
        mSendBank.setOnClickListener(this);
        mSendBank1.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        addListeners();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bank_sh_transfer:
                mBankShTransfer.setBackgroundResource(R.drawable.dark_blue_shap);
                mCreditShCard.setBackgroundResource(R.drawable.gray_shap);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.GONE);
                break;
            case R.id.credit_sh_card:
                mBankShTransfer.setBackgroundResource(R.drawable.gray_shap);
                mCreditShCard.setBackgroundResource(R.drawable.dark_blue_shap);
                one.setVisibility(View.GONE);
                two.setVisibility(View.VISIBLE);
                break;
            case R.id.send_bank:
                Toast.makeText(getContext(), "transfer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send_bank1:
                Toast.makeText(getContext(), "card", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
