package com.smm.sapp.sproject.Fragments;

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
import android.widget.Toast;

import com.smm.sapp.sproject.Adapters.SkillsAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.SkillsModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

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
    FrameLayout add_skills;
    EditText et_skill, et_experience;

    public SkillsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skills, container, false);
        return view;
    }

    private void init() {
        recyclerView = getView().findViewById(R.id.recycler_skills);
        tv_add_new = getView().findViewById(R.id.tv_add_new);
        tv_save = getView().findViewById(R.id.tv_save);
        add_skills = getView().findViewById(R.id.add_skills);
        et_skill = getView().findViewById(R.id.et_skill);
        et_experience = getView().findViewById(R.id.et_experience);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new SkillsAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void onClickMethod() {
        tv_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_skills.setVisibility(View.VISIBLE);
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkillsModel model = new SkillsModel(et_skill.getText().toString(), et_experience.getText().toString());
                arrayList.add(model);
                putParameter();
                add_skills.setVisibility(View.GONE);
            }
        });
    }

    private void addNewSkill(Map<String, String> stringMap) {
        MyRequest myRequest = new MyRequest();

        myRequest.PostCall("http://smm.smmim.com/waell/public/api/addskills", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject jsonObject = object.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (jsonObject.getBoolean("success")) {
                                Toast.makeText(getContext(), "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                notifys();
                            } else {
                                Toast.makeText(getContext(), "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                //                    arrayList.remove(arrayList.size());
                            }
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

    private void putParameter() {
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());

        for (int i = 0; i < arrayList.size(); i++) {
            stringMap.put("years[" + i + "]", arrayList.get(i).getYears());
            stringMap.put("name[" + i + "]", arrayList.get(i).getName());
        }
        addNewSkill(stringMap);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        arrayList = bundle.getParcelableArrayList("skillsInfo");
        Log.e("fff", arrayList.get(0).getName());

        init();
        onClickMethod();
    }
}
