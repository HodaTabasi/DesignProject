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
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.LikesDesignAdapter;
import com.smm.sapp.sproject.Adapters.LikesPWorkAdapter;
import com.smm.sapp.sproject.Adapters.LikesProjectAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.Likes;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    List<Likes> likes;
    LikesDesignAdapter designAdapter;
    int current_page, total_pages, flag;
    private TextView tv_next, tv_back;

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_fav);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        designs = view.findViewById(R.id.designs);
        pWork = view.findViewById(R.id.works);
        project = view.findViewById(R.id.projects);
        ic_back = view.findViewById(R.id.ic_back);
        tv_next = view.findViewById(R.id.tv_next);
        tv_back = view.findViewById(R.id.tv_back);

        designs.setOnClickListener(this);
        pWork.setOnClickListener(this);
        project.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        init(getView());
        getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=project", 1);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void getLikes(final String url, int current) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/mylikes" + url + "&i_current_page=" + current, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");
                final JSONObject paginationObj = object.getJSONObject("pagination");

                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                likes = gson.fromJson(object.getJSONArray("likes").toString(), new TypeToken<List<Likes>>() {
                                }.getType());

                                if (flag ==1){
                                    designAdapter = new LikesDesignAdapter(getContext(), R.layout.item_layout_profile, likes);
                                }else if(flag ==2){
                                    designAdapter = new LikesDesignAdapter(getContext(), R.layout.fav2_row, likes);
                                }else if(flag ==3){
                                    designAdapter = new LikesDesignAdapter(getContext(), R.layout.fav_row, likes);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                                }

                                //designAdapter = new LikesDesignAdapter(getContext(), R.layout.item_layout_profile, likes);
                                recyclerView.setAdapter(designAdapter);

                                current_page = Integer.valueOf(paginationObj.getString("i_current_page"));
                                total_pages = Integer.valueOf(paginationObj.getString("i_total_pages"));

                                if (total_pages > current_page && current_page != 1) {
                                    //two are visible
                                    tv_next.setVisibility(View.VISIBLE);
                                    tv_back.setVisibility(View.VISIBLE);
                                } else if (total_pages == current_page && current_page != 1) {
                                    //back visible, next gone
                                    tv_next.setVisibility(View.GONE);
                                    tv_back.setVisibility(View.VISIBLE);
                                } else if (total_pages > current_page && current_page == 1) {
                                    //next visible, back gone
                                    tv_next.setVisibility(View.VISIBLE);
                                    tv_back.setVisibility(View.GONE);
                                } else if (total_pages == 1 || total_pages == 0) {
                                    //two are gone
                                    tv_next.setVisibility(View.GONE);
                                    tv_back.setVisibility(View.GONE);
                                }
//                                JSONArray jsonArray = object.getJSONArray("likes");
//                                for (int i = 0; i <= jsonArray.length(); i++) {
//                                    JSONObject object2 = jsonArray.getJSONObject(i);
//                                    Likes likes = gson.fromJson(object2.toString(), Likes.class);
//                                    switch (likes.getTarget_type()) {
//                                        case "project":
//                                            projectList.add(likes);
//                                            break;
//                                        case "user":
//                                            if (ConstantInterFace.USER.getId() != likes.getUser().getId()) {
//                                                designList.add(likes);
//                                            }
//                                            break;
//                                        case "pwork":
//                                            workList.add(likes);
//                                            break;
//                                    }
//                                }
                            } else {
                                Toast.makeText(getContext(), "" + object1.getBoolean("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.designs:
                flag = 1;
                designs.setTextColor(Color.parseColor("#ffffff"));
                designs.setBackgroundResource(R.drawable.blue_shape);
                pWork.setTextColor(Color.parseColor("#000000"));
                pWork.setBackgroundResource(R.drawable.account_shape);
                project.setTextColor(Color.parseColor("#000000"));
                project.setBackgroundResource(R.drawable.account_shape);
                getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=user", 1);
                break;

            case R.id.works:
                flag = 2;
                pWork.setTextColor(Color.parseColor("#ffffff"));
                pWork.setBackgroundResource(R.drawable.blue_shape);
                designs.setTextColor(Color.parseColor("#000000"));
                designs.setBackgroundResource(R.drawable.account_shape);
                project.setTextColor(Color.parseColor("#000000"));
                project.setBackgroundResource(R.drawable.account_shape);
                getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=pwork", 1);
                break;

                case R.id.projects:
                flag = 3;
                project.setTextColor(Color.parseColor("#ffffff"));
                project.setBackgroundResource(R.drawable.blue_shape);
                pWork.setTextColor(Color.parseColor("#000000"));
                pWork.setBackgroundResource(R.drawable.account_shape);
                designs.setTextColor(Color.parseColor("#000000"));
                designs.setBackgroundResource(R.drawable.account_shape);
                getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=project", 1);
                break;

            case R.id.tv_next:
                current_page++;
                if (flag == 1) {
                    getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=user", current_page);
                } else if (flag == 2) {
                    getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=pwork", current_page);
                } else if (flag == 3) {
                    getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=project", current_page);
                } else {
                    getLikes("?token=" + ConstantInterFace.USER.getToken(), current_page);
                }
                break;

            case R.id.tv_back:
                current_page--;
                if (flag == 1) {
                    getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=user", current_page);
                } else if (flag == 2) {
                    getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=pwork", current_page);
                } else if (flag == 3) {
                    getLikes("?token=" + ConstantInterFace.USER.getToken() + "&target_type=project", current_page);
                } else {
                    getLikes("?token=" + ConstantInterFace.USER.getToken(), current_page);
                }
                break;
        }

    }
}
