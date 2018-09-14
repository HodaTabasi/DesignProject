package com.example.maryam.sproject.Fragments;

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
import android.widget.TextView;

import com.example.maryam.sproject.Adapters.BankAdapter;
import com.example.maryam.sproject.HelperClass.MyProgressDialog;
import com.example.maryam.sproject.Models.BankModel;
import com.example.maryam.sproject.Models.UserModel;
import com.example.maryam.sproject.MyRequest;
import com.example.maryam.sproject.OkHttpCallback;
import com.example.maryam.sproject.R;
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
        stringMap.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjIsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2ODMxMDU3LCJleHAiOjQ4MDgxNzYwNDU5MzI1NTU4NTcsIm5iZiI6MTUzNjgzMTA1NywianRpIjoiN3FmUXZVQW1lNWxLaWdBeSJ9.JaZRD1eLJ6It2DuR6Qn1F5kNL8lyMWhYz_NYsjRW-qs");
        stringMap.put("name", et_bankName.getText().toString());
        stringMap.put("iban", et_kNum.getText().toString());
        stringMap.put("number", et_bankNum.getText().toString());
        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/addbank", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
//                Log.e("tagr", response.body().string());
                JSONObject jsonObject = new JSONObject(response.body().string());
                Gson gson = new Gson();
                UserModel.BanksBean banksBean = gson.fromJson(jsonObject.getString("bank"), UserModel.BanksBean.class);
                arrayList.add(banksBean);
//                Log.e("tag1", userModel.getBanks().get(0).getNumber() + " ");
                notifys();
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
        Log.e("log", arrayList.get(0).getNumber());

        init();
        onClickMethod();
    }
}
