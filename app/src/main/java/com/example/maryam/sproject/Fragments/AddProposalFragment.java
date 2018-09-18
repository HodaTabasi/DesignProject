package com.example.maryam.sproject.Fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maryam.sproject.HelperClass.MyProgressDialog;
import com.example.maryam.sproject.Models.OfferModel;
import com.example.maryam.sproject.MyRequest;
import com.example.maryam.sproject.OkHttpCallback;
import com.example.maryam.sproject.R;
import com.google.gson.Gson;
import com.obsez.android.lib.filechooser.ChooserDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class AddProposalFragment extends Fragment {


    /**
     * يوم
     */
    private EditText mReceivableP;
    /**
     * يوم
     */
    private EditText mBalanceP;
    /**
     * يوم
     */
    private EditText mDurP;
    /**
     * مممممم
     */
    private EditText mProposalP;
    /**
     * يمنع استخدام وسائل تواصل خارجية
     */
    private TextView mTv1;
    /**
     * سيتم وضع معرض اعمالك مع العرض
     */
    private TextView mTv2;
    /**
     * ارفق ملف
     */
    private TextView mAttchP;
    /**
     * اضف عرضك
     */
    private TextView mAddProposalP;
    private String filePath;
    private byte[] b;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_proposal, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        assert bundle != null;
        id = bundle.getInt("id",0);

        initView(getView());
        mAttchP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileBrowse();
            }
        });

        mAddProposalP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOfferRequest();
            }
        });
    }

    private void addOfferRequest() {
        Log.e("ffd",id + " gg");
        MyRequest myRequest =new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODU4NS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM0NzcwMTA0LCJleHAiOjIxNDc0ODM2NDcsIm5iZiI6MTUzNDc3MDEwNCwianRpIjoiRnByN1h6aEI3SWtHb0xpVyJ9.HC4LMZ1_wioWsUfEeKOUa2RlkTkBh98bHYbT-RYHy5o");
        stringMap.put("project_id",id + "");
        stringMap.put("dur", mDurP.getText().toString());
        stringMap.put("balance", mBalanceP.getText().toString());
        stringMap.put("descr", mProposalP.getText().toString());
        myRequest.PostCallWithAttachment("http://mustafa.smmim.com/waell/public/api/makeanoffer", stringMap, filePath, "file_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject object = jsonObject.getJSONObject("status");
                if (object.getBoolean("success")){
                    Log.e("ggg"," " + object.getString("message"));
                    Gson gson = new Gson();
                    OfferModel model = gson.fromJson(jsonObject.getJSONObject("offer").toString(),OfferModel.class);
                    Log.e("ff",model.getBalance());
                }else {
                    Log.e("ff","dglegh98");
                }
            }
        });
    }

    private void fileBrowse() {
        new ChooserDialog().with(getContext())
                .withFilter(false, false, "pdf", "docx", "xlsx")
                .withStartFile(Environment.getExternalStorageDirectory().getPath())
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        Toast.makeText(getContext(), "FOLDER: " + path, Toast.LENGTH_SHORT).show();
                        filePath = path;
                        mAttchP.setText(path);

                    }
                })
                .build()
                .show();
    }
    
    private void initView(View view) {
        mReceivableP = (EditText) view.findViewById(R.id.receivable_p);
        mBalanceP = (EditText) view.findViewById(R.id.balance_p);
        mDurP = (EditText) view.findViewById(R.id.dur_p);
        mProposalP = (EditText) view.findViewById(R.id.proposal_p);
        mTv1 = (TextView) view.findViewById(R.id.tv1);
        mTv2 = (TextView) view.findViewById(R.id.tv2);
        mAttchP = (TextView) view.findViewById(R.id.attch_p);
        mAddProposalP = (TextView) view.findViewById(R.id.add_proposal_p);
    }
}
