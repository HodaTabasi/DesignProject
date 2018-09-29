package com.smm.sapp.sproject.Fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
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

    private EditText mReceivableP;
    private EditText mBalanceP;
    private EditText mDurP;
    private EditText mProposalP;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mAttchP;
    private TextView mAddProposalP;
    private String filePath;
    private byte[] b;
    int id;
    ImageView ic_back;
    TextView back_two;

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
        
        initView(getView());
        Bundle bundle = getArguments();
        assert bundle != null;
        id = bundle.getInt("id",0);
        final OfferModel model = bundle.getParcelable("object");

        if (id == 0){
            mBalanceP.setText(model.getBalance());
            mProposalP.setText(model.getDescr());
            mDurP.setText(model.getDur());
        }
        
        mAttchP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileBrowse();
            }
        });

        mAddProposalP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == 0){
                        updateOfferRequest(model);
                }else {
                    addOfferRequest();
                }
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        back_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void updateOfferRequest(OfferModel model) {
        Log.e("ffd",id + " gg");
        MyRequest myRequest =new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("offer_id",model.getId()+"");
        stringMap.put("dur", mDurP.getText().toString());
        stringMap.put("balance", mBalanceP.getText().toString());
        stringMap.put("descr", mProposalP.getText().toString());
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/editanoffer", stringMap, filePath, "file_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object.getBoolean("success")){
                                Toast.makeText(getActivity(), ""+ object.getString("message"), Toast.LENGTH_SHORT).show();
                                Gson gson = new Gson();
                                OfferModel model = gson.fromJson(jsonObject.getJSONObject("offer").toString(),OfferModel.class);
                                getActivity().finish();
                            }else {
                                Toast.makeText(getActivity(), ""+ object.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });



            }
        });
    }

    private void addOfferRequest() {
        Log.e("ffd",id + " gg");
        MyRequest myRequest =new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("project_id",id + "");
        stringMap.put("dur", mDurP.getText().toString());
        stringMap.put("balance", mBalanceP.getText().toString());
        stringMap.put("descr", mProposalP.getText().toString());
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/makeanoffer", stringMap, filePath, "file_link", new OkHttpCallback() {
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
        ic_back = getView().findViewById(R.id.ic_back);
        back_two = view.findViewById(R.id.back_two);
    }
}
