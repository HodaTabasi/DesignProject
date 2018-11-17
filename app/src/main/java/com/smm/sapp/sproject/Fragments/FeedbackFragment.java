package com.smm.sapp.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.smm.sapp.sproject.Adapters.FeedbackAdapter;
import com.smm.sapp.sproject.Adapters.PortfolioAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.Comments;
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


public class FeedbackFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Comments> arrayList = new ArrayList<>();
    ArrayList<Comments> arrayList1 = new ArrayList<>();
    FeedbackAdapter adapter;
    ImageView ic_back;
    Bundle bundle;
    int designer_id;
    private TextView tv_next, tv_back;
    int current_page, total_pages, flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        recyclerView = view.findViewById(R.id.recycel_feedback);
        ic_back = view.findViewById(R.id.ic_back);
        tv_next = getView().findViewById(R.id.tv_next);
        tv_back = getView().findViewById(R.id.tv_back);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        bundle = getArguments();
        if (bundle != null && bundle.containsKey("designer_id")) {
            designer_id = bundle.getInt("designer_id");
            getDesignerComments(designer_id);
            flag = 1;
        } else if (bundle != null && bundle.containsKey("commentsInfo")) {
            arrayList = bundle.getParcelableArrayList("commentsInfo");
            adapter = new FeedbackAdapter(getActivity(), arrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
            flag = 2;
        }

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void getDesignerComments(int designer_id) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("user_id", String.valueOf(designer_id));

        myRequest.PostCall("http://smm.smmim.com/waell/public/api/userprofile", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, final IOException e) {
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
                final JSONObject userObj = object.getJSONObject("user");
                JSONObject statusObj = object.getJSONObject("status");
                final String success = statusObj.getString("success");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (success.equals("true")) {
                            Gson gson = new Gson();
                            TypeToken<List<Comments>> token = new TypeToken<List<Comments>>() {
                            };
                            try {
                                arrayList1 = gson.fromJson(userObj.getJSONArray("comments_on_profile").toString(), token.getType());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter = new FeedbackAdapter(getActivity(), arrayList1);

                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                        } else {

                            Toast.makeText(getContext(), "لا يوجد نتائج", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });


    }
}
