package com.smm.sapp.sproject.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.AddNewPworkAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.PWorks;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class AddNewWork2Fragment extends Fragment {

    TextView title1;
    ImageView ic_back;

    RecyclerView mnewWorkRes;
    ArrayList<PWorks> arrayList = new ArrayList<>();
    AddNewPworkAdapter adapter;

    public AddNewWork2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_work2, container, false);
    }

    private void initView() {
        title1 = getView().findViewById(R.id.title1);
        mnewWorkRes = getView().findViewById(R.id.new_work_res);
        ic_back = getView().findViewById(R.id.ic_back);
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ConstantInterFace.IS_USER_COMPLETEED) {
                    Snackbar snackbar = Snackbar.make(getView(), "يرجى تعبئة بياناتك الشخصية", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    TextView tv = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextSize(12f);
                    Typeface font = Typeface.createFromAsset(getContext().getAssets(), "JFFlatregular.ttf");
                    tv.setTypeface(font);
                } else {
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddNewWorkFragment(), true);
                }
            }
        });

        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        getData();
    }

    private void getData() {
        MyProgressDialog.showDialog(getActivity());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("user_id", String.valueOf(ConstantInterFace.USER.getId()));
        map.put("token", ConstantInterFace.USER.getToken());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/userprofile", map, new OkHttpCallback() {
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
                JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject userJson = jsonObject.getJSONObject("user");


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        TypeToken<List<PWorks>> token = new TypeToken<List<PWorks>>() {
                        };
                        try {
                            arrayList = gson.fromJson(userJson.getJSONArray("pworks").toString(), token.getType());
                            adapter = new AddNewPworkAdapter(getActivity(), arrayList);
                            mnewWorkRes.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            mnewWorkRes.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                });


            }
        });


    }
}
