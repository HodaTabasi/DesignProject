package com.smm.sapp.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.BrowseProjectAdapter;
import com.smm.sapp.sproject.Adapters.PortfolioAdapter;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.PortfolioModel;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class PortfolioFragment extends Fragment {

    ImageView ic_back;
    TextView tv_inter, tv_arch, tv_wall, tv_graphic, tv_motion;
    EditText et_search;
    RecyclerView recyclerView;
    String s_search;
    ArrayList<PortfolioModel> arrayList = new ArrayList<>();
    PortfolioAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        ic_back = getView().findViewById(R.id.ic_back);
        tv_inter = getView().findViewById(R.id.tv_inter);
        tv_arch = getView().findViewById(R.id.tv_arch);
        tv_wall = getView().findViewById(R.id.tv_wall);
        tv_graphic = getView().findViewById(R.id.tv_graphic);
        tv_motion = getView().findViewById(R.id.tv_motion);
        et_search = getView().findViewById(R.id.search);
        recyclerView = getView().findViewById(R.id.recycler);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        s_search = et_search.getText().toString();
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.e("ooooooooooo", s_search);
                    //performSearch();
                    return true;
                }
                return false;
            }
        });


    }

    private void performSearch() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/searchpworks?name=" + s_search, new OkHttpCallback() {
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
                JSONObject statusObj = object.getJSONObject("status");
                final String success = statusObj.getString("success");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (success.equals("true")) {
                            Gson gson = new Gson();
                            TypeToken<List<PortfolioModel>> token = new TypeToken<List<PortfolioModel>>() {
                            };
                            try {
                                arrayList = gson.fromJson(object.getJSONArray("pworks").toString(), token.getType());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter = new PortfolioAdapter(getActivity(), arrayList);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
                            recyclerView.setAdapter(adapter);
                        } else {

                        }
                    }
                });
            }
        });
    }
}
