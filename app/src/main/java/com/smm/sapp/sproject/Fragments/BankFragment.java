package com.smm.sapp.sproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.Adapters.BankAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.BankModel;
import com.smm.sapp.sproject.Models.UserModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class BankFragment extends Fragment {

    RecyclerView recyclerView;
    FrameLayout frameLayout;
    TextView tv_add_account, tv_save;
    EditText et_bankName, et_bankNum, et_kNum;
    ArrayList<UserModel.BanksBean> arrayList = new ArrayList<>();
    BankAdapter adapter;
    ImageView ic_back;

    public BankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bank, container, false);
        return view;
    }

    private void init() {
        recyclerView = getView().findViewById(R.id.recycler_bank);
        tv_add_account = getView().findViewById(R.id.tv_add_account);
        tv_save = getView().findViewById(R.id.tv_save);
        frameLayout = getView().findViewById(R.id.add_bank);
        ic_back = getView().findViewById(R.id.ic_back);

        et_bankName = getView().findViewById(R.id.et_bankName);
        et_bankNum = getView().findViewById(R.id.et_bankNum);
        et_kNum = getView().findViewById(R.id.et_kNum);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new BankAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void onClickMethod() {
        tv_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.VISIBLE);
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewBankRequest();
                frameLayout.setVisibility(View.GONE);
            }
        });
    }

    private void addNewBankRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("name", et_bankName.getText().toString());
        stringMap.put("iban", et_kNum.getText().toString());
        stringMap.put("number", et_bankNum.getText().toString());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/addbank", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                String s = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "تم الاضافة بنجاح", Toast.LENGTH_LONG).show();
                        JSONObject jsonObject = new JSONObject();
                        Gson gson = new Gson();
                        UserModel.BanksBean banksBean = null;
                        et_bankName.setText("");
                        et_bankNum.setText("");
                        et_kNum.setText("");
                        try {
                            banksBean = gson.fromJson(jsonObject.getString("bank"), UserModel.BanksBean.class);
                            arrayList.add(banksBean);
                            notifys();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private void notifys() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        arrayList = bundle.getParcelableArrayList("bankInfo");

        init();
        onClickMethod();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }
}
