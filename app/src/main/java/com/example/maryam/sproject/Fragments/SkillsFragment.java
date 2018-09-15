package com.example.maryam.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.Adapters.SkillsAdapter;
import com.example.maryam.sproject.HelperClass.MyProgressDialog;
import com.example.maryam.sproject.Models.SkillsModel;
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


public class SkillsFragment extends Fragment {

    RecyclerView recyclerView;
    TextView tv_add_new, tv_save;
    ArrayList<SkillsModel> arrayList = new ArrayList<>();
    SkillsAdapter adapter;

    public SkillsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skills, container, false);

        recyclerView = view.findViewById(R.id.recycler_skills);
        tv_add_new = view.findViewById(R.id.tv_add_new);
        tv_save = view.findViewById(R.id.tv_save);


        adapter = new SkillsAdapter(getActivity(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        tv_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewSkill();
            }
        });

        return view;
    }

    private void addNewSkill() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbW" +
                "ltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2NTYyNjExLCJleHAiOjQ4MDgxNzYwNDU5MzIyODc0MTEsI" +
                "m5iZiI6MTUzNjU2MjYxMSwianRpIjoiQ2NHRFlQOW4wcno4cjJCMCJ9.8fOb9OQliz0Z63t-SiZcTnRdExskt_Xtx68AWYy4hWU");


        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/addskills", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                Log.e("tag", response.body().string());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        arrayList = bundle.getParcelableArrayList("skillsInfo");

    }
}
