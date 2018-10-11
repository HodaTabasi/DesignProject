package com.smm.sapp.sproject.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smm.sapp.sproject.Adapters.LikesDesignAdapter;
import com.smm.sapp.sproject.Adapters.LikesPWorkAdapter;
import com.smm.sapp.sproject.Adapters.LikesProjectAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.Likes;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class FavoriteFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    ArrayList<Likes> projectList = new ArrayList<>();
    ArrayList<Likes> workList = new ArrayList<>();
    ArrayList<Likes> designList = new ArrayList<>();
    TextView designs, pWork, project;
    ImageView ic_back;

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);


//        adapter = new FavoritePortfolioAdapter(getActivity(), arrayList);
//        recyclerView.setAdapter(adapter);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_fav);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        designs = view.findViewById(R.id.designs);
        pWork = view.findViewById(R.id.works);
        project = view.findViewById(R.id.projects);
        ic_back = getView().findViewById(R.id.ic_back);

        designs.setOnClickListener(this);
        pWork.setOnClickListener(this);
        project.setOnClickListener(this);
    }

    private void getAllLikes() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/mylikes?token=" + ConstantInterFace.USER.getToken(), new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");
                final Gson gson = new Gson();
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                JSONArray jsonArray = object.getJSONArray("likes");
                                for (int i = 0; i <= jsonArray.length(); i++) {
                                    JSONObject object2 = jsonArray.getJSONObject(i);
                                    Likes likes = gson.fromJson(object2.toString(), Likes.class);
                                    switch (likes.getTarget_type()) {
                                        case "project":
                                            projectList.add(likes);
                                            break;
                                        case "user":
                                            if (ConstantInterFace.USER.getId() != likes.getUser().getId()) {
                                                designList.add(likes);
                                            }
                                            break;
                                        case "pwork":
                                            workList.add(likes);
                                            break;
                                    }
                                }
                            } else {
                                Toast.makeText(getContext(), "" + object1.getBoolean("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        LikesDesignAdapter designAdapter = new LikesDesignAdapter(getContext(), R.layout.item_layout_profile, designList);
                        recyclerView.setAdapter(designAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        init(getView());
        getAllLikes();

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
            case R.id.designs:
                designs.setTextColor(Color.parseColor("#ffffff"));
                designs.setBackgroundResource(R.drawable.blue_shape);
                pWork.setTextColor(Color.parseColor("#000000"));
                pWork.setBackgroundResource(R.drawable.account_shape);
                project.setTextColor(Color.parseColor("#000000"));
                project.setBackgroundResource(R.drawable.account_shape);
//                recyclerView.setVisibility(View.INVISIBLE);
                Log.e("dded",designList.size() +" dd");
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                LikesDesignAdapter designAdapter = new LikesDesignAdapter(getContext(), R.layout.item_layout_profile, designList);
                recyclerView.setAdapter(designAdapter);

                break;
            case R.id.works:
                pWork.setTextColor(Color.parseColor("#ffffff"));
                pWork.setBackgroundResource(R.drawable.blue_shape);
                designs.setTextColor(Color.parseColor("#000000"));
                designs.setBackgroundResource(R.drawable.account_shape);
                project.setTextColor(Color.parseColor("#000000"));
                project.setBackgroundResource(R.drawable.account_shape);
                Log.e("dded",workList.size() +" ww");
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                LikesPWorkAdapter workAdapter = new LikesPWorkAdapter(getContext(), R.layout.fav2_row, workList);
                recyclerView.setAdapter(workAdapter);
//                recyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.projects:
                project.setTextColor(Color.parseColor("#ffffff"));
                project.setBackgroundResource(R.drawable.blue_shape);
                pWork.setTextColor(Color.parseColor("#000000"));
                pWork.setBackgroundResource(R.drawable.account_shape);
                designs.setTextColor(Color.parseColor("#000000"));
                designs.setBackgroundResource(R.drawable.account_shape);
//                recyclerView.setVisibility(View.VISIBLE);
                Log.e("dded",projectList.size() +" pp");
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                LikesProjectAdapter projectAdapter = new LikesProjectAdapter(getContext(), R.layout.fav_row, projectList);
                recyclerView.setAdapter(projectAdapter);
                break;
        }

    }
}
