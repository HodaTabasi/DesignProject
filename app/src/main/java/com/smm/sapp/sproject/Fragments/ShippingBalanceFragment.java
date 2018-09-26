package com.smm.sapp.sproject.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.PathUtil;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShippingBalanceFragment extends Fragment implements View.OnClickListener {

    /**
     * تحويل بنكي
     */
    private TextView mBankShTransfer;
    /**
     * بطاقة ائتمانية
     */
    private TextView mCreditShCard;
    /**
     * التحويل البنكي
     */
    private TextView mTitle;
    /**
     * ارسال
     */
    private TextView mSendBank;
    /**
     * ارسال
     */
    private TextView mSendBank1;
    RelativeLayout one, two;
    TextView addPhotoShp;
    EditText userBankNameShp, bankNumberShp, bankNameShp, balance, transferDateSh;
    ImageView ic_back;
    String filePath;

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
        userBankNameShp = getView().findViewById(R.id.user_bank_name_shp);
        bankNameShp = getView().findViewById(R.id.bank_name_shp);
        bankNumberShp = getView().findViewById(R.id.bank_number_shp);
        balance = getView().findViewById(R.id.balance_shp);
        transferDateSh = getView().findViewById(R.id.transfer_date_sh);
        addPhotoShp = getView().findViewById(R.id.add_photo_shp);
        one = getView().findViewById(R.id.sh_one);
        two = getView().findViewById(R.id.sh_two);
    }

    private void addListeners() {
        mBankShTransfer.setOnClickListener(this);
        mCreditShCard.setOnClickListener(this);
        mSendBank.setOnClickListener(this);
        mSendBank1.setOnClickListener(this);
        addPhotoShp.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        addListeners();

        ic_back = getView().findViewById(R.id.ic_back);
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
                sendCardBank();
                break;
            case R.id.send_bank1:

                break;
            case R.id.add_photo_shp:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
        }
    }

    private void sendCardBank() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("name", userBankNameShp.getText().toString());
        stringMap.put("acc_number", bankNumberShp.getText().toString());
        stringMap.put("bank_name", bankNameShp.getText().toString());
        stringMap.put("total", balance.getText().toString());
        stringMap.put("date", transferDateSh.getText().toString());
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/chargemycredit", stringMap, filePath, "photo_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject object = jsonObject.getJSONObject("status");
                if (object.getBoolean("success")) {
                    Log.e("ggg", " " + object.getString("message"));
                } else {

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    filePath = PathUtil.getPath(getActivity(), selectedImage);
                    Log.e("dd", " " + filePath);
                    addPhotoShp.setText(filePath);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
