package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.MyMessageAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.MyMessageModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class MyMessageFragment extends Fragment {


    private RecyclerView mMyMessage;
    MyMessageAdapter adapter;
    List<MyMessageModel> myMessageModelList;
    ImageView ic_back;
    int current_page, total_pages;
    private TextView tv_next, tv_back;

    public MyMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_masseages, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView(getView());
        setBottomBar();
        getMyConversationsRequest(1);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBottomBar();
                current_page++;
                getMyConversationsRequest(current_page);
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBottomBar();
                current_page--;
                getMyConversationsRequest(current_page);
            }
        });
    }

    private void initView(View view) {
        mMyMessage = view.findViewById(R.id.my_message);
        mMyMessage.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ic_back = getView().findViewById(R.id.ic_back);
        tv_next = getView().findViewById(R.id.tv_next);
        tv_back = getView().findViewById(R.id.tv_back);
    }

    private void setBottomBar() {
        ConstantInterFace.tv_msgs.setBackground(getResources().getDrawable(R.drawable.main_shape));
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
    }


    private void getMyConversationsRequest(int current) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/getmyconversations?i_current_page=" + current, stringMap, new OkHttpCallback() {
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
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");
                final JSONObject paginationObj = object.getJSONObject("pagination");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                TypeToken<List<MyMessageModel>> token = new TypeToken<List<MyMessageModel>>() {
                                };
                                myMessageModelList = gson.fromJson(object.getJSONArray("convs").toString(), token.getType());
                                adapter = new MyMessageAdapter(getContext(), R.layout.layout_item_notification, myMessageModelList);
                                mMyMessage.setAdapter(adapter);

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

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
