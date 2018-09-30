package com.smm.sapp.sproject.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.smm.sapp.sproject.Adapters.BrowseProjectAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class BrowseProjectsFragment extends Fragment implements View.OnClickListener {

    ArrayList<ProjectsModels> arrayList = new ArrayList<>();
    BrowseProjectAdapter adapter;
    private EditText mSearch;
    private TextView mMotionButton;
    private TextView mGhButton;
    private TextView mWallButton;
    private TextView mArchButton;
    private TextView mInButton;
    private RecyclerView mProjectsRecycler;
    ImageView ic_back;
    String s_search;

    public BrowseProjectsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_browse_projects, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        getProjects("getallprojects");
        initView();
        setListener();


    }

    private void initView() {
        mSearch = (EditText) getView().findViewById(R.id.search);
        mMotionButton = (TextView) getView().findViewById(R.id.motion_button);
        mGhButton = (TextView) getView().findViewById(R.id.gh_button);
        mWallButton = (TextView) getView().findViewById(R.id.wall_button);
        mArchButton = (TextView) getView().findViewById(R.id.arch_button);
        mInButton = (TextView) getView().findViewById(R.id.in_button);
        mProjectsRecycler = (RecyclerView) getView().findViewById(R.id.projects_recycler);
        mProjectsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ic_back = getView().findViewById(R.id.ic_back);
    }

    private void setListener() {
        mSearch.setOnClickListener(this);
        mMotionButton.setOnClickListener(this);
        mGhButton.setOnClickListener(this);
        mWallButton.setOnClickListener(this);
        mArchButton.setOnClickListener(this);
        mInButton.setOnClickListener(this);

        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    s_search = textView.getText().toString();

                    mMotionButton.setBackgroundResource(R.drawable.account_shape);
                    mMotionButton.setTextColor(Color.parseColor("#000000"));

                    mArchButton.setBackgroundResource(R.drawable.account_shape);
                    mArchButton.setTextColor(Color.parseColor("#000000"));

                    mInButton.setBackgroundResource(R.drawable.account_shape);
                    mInButton.setTextColor(Color.parseColor("#000000"));

                    mGhButton.setBackgroundResource(R.drawable.account_shape);
                    mGhButton.setTextColor(Color.parseColor("#000000"));

                    mWallButton.setBackgroundResource(R.drawable.account_shape);
                    mWallButton.setTextColor(Color.parseColor("#000000"));

                    getProjects("searchprojects?name=" + s_search);


                    return true;
                }
                return false;
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

    private void getProjects(String url) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/" + url, new OkHttpCallback() {
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
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                arrayList.clear();
                                TypeToken<List<ProjectsModels>> token = new TypeToken<List<ProjectsModels>>() {
                                };
                                arrayList = gson.fromJson(object.getJSONArray("projects").toString(), token.getType());
                                adapter = new BrowseProjectAdapter(getActivity(), arrayList);
                                mProjectsRecycler.setAdapter(adapter);
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

    private void setBottomBarShap() {
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackground(getResources().getDrawable(R.drawable.main_shape));
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.motion_button:
                mMotionButton.setTextColor(Color.parseColor("#ffffff"));
                mMotionButton.setBackgroundResource(R.drawable.blue_shape);
                mGhButton.setTextColor(Color.parseColor("#000000"));
                mGhButton.setBackgroundResource(R.drawable.account_shape);
                mWallButton.setTextColor(Color.parseColor("#000000"));
                mWallButton.setBackgroundResource(R.drawable.account_shape);
                mArchButton.setTextColor(Color.parseColor("#000000"));
                mArchButton.setBackgroundResource(R.drawable.account_shape);
                mInButton.setTextColor(Color.parseColor("#000000"));
                mInButton.setBackgroundResource(R.drawable.account_shape);

                setBottomBarShap();
                getProjects("getallprojectsmoshen");
                break;

            case R.id.gh_button:
                mGhButton.setTextColor(Color.parseColor("#ffffff"));
                mGhButton.setBackgroundResource(R.drawable.blue_shape);
                mMotionButton.setTextColor(Color.parseColor("#000000"));
                mMotionButton.setBackgroundResource(R.drawable.account_shape);
                mWallButton.setTextColor(Color.parseColor("#000000"));
                mWallButton.setBackgroundResource(R.drawable.account_shape);
                mArchButton.setTextColor(Color.parseColor("#000000"));
                mArchButton.setBackgroundResource(R.drawable.account_shape);
                mInButton.setTextColor(Color.parseColor("#000000"));
                mInButton.setBackgroundResource(R.drawable.account_shape);

                setBottomBarShap();
                getProjects("getallprojectsgraphic");
                break;

            case R.id.wall_button:
                mWallButton.setTextColor(Color.parseColor("#ffffff"));
                mWallButton.setBackgroundResource(R.drawable.blue_shape);
                mMotionButton.setTextColor(Color.parseColor("#000000"));
                mMotionButton.setBackgroundResource(R.drawable.account_shape);
                mGhButton.setTextColor(Color.parseColor("#000000"));
                mGhButton.setBackgroundResource(R.drawable.account_shape);
                mArchButton.setTextColor(Color.parseColor("#000000"));
                mArchButton.setBackgroundResource(R.drawable.account_shape);
                mInButton.setTextColor(Color.parseColor("#000000"));
                mInButton.setBackgroundResource(R.drawable.account_shape);

                setBottomBarShap();
                getProjects("getallprojectswall");
                break;

            case R.id.arch_button:
                mArchButton.setTextColor(Color.parseColor("#ffffff"));
                mArchButton.setBackgroundResource(R.drawable.blue_shape);

                mMotionButton.setTextColor(Color.parseColor("#000000"));
                mMotionButton.setBackgroundResource(R.drawable.account_shape);
                mGhButton.setTextColor(Color.parseColor("#000000"));
                mGhButton.setBackgroundResource(R.drawable.account_shape);
                mWallButton.setTextColor(Color.parseColor("#000000"));
                mWallButton.setBackgroundResource(R.drawable.account_shape);
                mInButton.setTextColor(Color.parseColor("#000000"));
                mInButton.setBackgroundResource(R.drawable.account_shape);

                setBottomBarShap();
                getProjects("getallprojectsarch");
                break;

            case R.id.in_button:
                mInButton.setTextColor(Color.parseColor("#ffffff"));
                mInButton.setBackgroundResource(R.drawable.blue_shape);
                mMotionButton.setTextColor(Color.parseColor("#000000"));
                mMotionButton.setBackgroundResource(R.drawable.account_shape);
                mGhButton.setTextColor(Color.parseColor("#000000"));
                mGhButton.setBackgroundResource(R.drawable.account_shape);
                mWallButton.setTextColor(Color.parseColor("#000000"));
                mWallButton.setBackgroundResource(R.drawable.account_shape);
                mArchButton.setTextColor(Color.parseColor("#000000"));
                mArchButton.setBackgroundResource(R.drawable.account_shape);

                setBottomBarShap();
                getProjects("getallprojectsinter");
                break;
        }
    }
}
