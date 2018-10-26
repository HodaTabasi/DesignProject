package com.smm.sapp.sproject.Fragments;

import android.content.Context;
import android.net.Uri;
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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.AddNewPworkAdapter;
import com.smm.sapp.sproject.Adapters.PortfolioAdapter;
import com.smm.sapp.sproject.Adapters.PworksAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.PWorks;
import com.smm.sapp.sproject.Models.PortfolioModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class PortfolioBrowseProject extends Fragment {

    ImageView ic_back;
    CircleImageView profileImg;
    TextView tv_name, tv_title;
    RatingBar rate_bar;
    RecyclerView res_search;

    ArrayList<PWorks> arrayList = new ArrayList<>();
    PworksAdapter adapter;

    public PortfolioBrowseProject() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portfolio_browse_project, container, false);

        ic_back = view.findViewById(R.id.ic_back);
        profileImg = view.findViewById(R.id.profileImg);
        tv_name = view.findViewById(R.id.tv_name);
        rate_bar = view.findViewById(R.id.rate_bar);
        tv_title = view.findViewById(R.id.tv_title);
        res_search = view.findViewById(R.id.res_search);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });


        Bundle bundle = getArguments();
        if (bundle.getBoolean("flag")) {
            String name = bundle.getString("name");

            getData(bundle.getString("id"), name);

            Picasso.get().load(bundle.getString("photo")).into(profileImg);

            StringBuilder s_name = new StringBuilder(bundle.getString("name"));
            for (int i = 1; i < s_name.length() - 1; i++) {
                s_name.setCharAt(i, '*');
            }
            tv_name.setText(s_name);
            rate_bar.setRating(bundle.getFloat("rate"));
            tv_title.setText(bundle.getString("speacialization"));


        }
    }


    private void getData(String user_id, final String user_name) {
        MyProgressDialog.showDialog(getActivity());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
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
                            adapter = new PworksAdapter(getActivity(), arrayList, user_name);
                            res_search.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                            res_search.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                });


            }
        });


    }

}
