package com.smm.sapp.sproject.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    private TextView tv_next, tv_back;
    int current_page, total_pages, flag;

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
        getProjects("getallprojects?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", 1);
        initView();
        setBottomBar();
        setListener();
    }

    private void setBottomBar() {
        ConstantInterFace.tv_projects.setBackground(getResources().getDrawable(R.drawable.main_shape));
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
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
        tv_next = getView().findViewById(R.id.tv_next);
        tv_back = getView().findViewById(R.id.tv_back);
    }

    private void setListener() {
        mSearch.setOnClickListener(this);
        mMotionButton.setOnClickListener(this);
        mGhButton.setOnClickListener(this);
        mWallButton.setOnClickListener(this);
        mArchButton.setOnClickListener(this);
        mInButton.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        tv_back.setOnClickListener(this);


        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    s_search = textView.getText().toString();

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearch.getWindowToken(), 0);

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

                    getProjects("searchprojects?name=" + s_search, 1);
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

    private void getProjects(String url, int current) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/" + url + current, new OkHttpCallback() {
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
                                arrayList.clear();
                                TypeToken<List<ProjectsModels>> token = new TypeToken<List<ProjectsModels>>() {
                                };
                                arrayList = gson.fromJson(object.getJSONArray("projects").toString(), token.getType());
                                adapter = new BrowseProjectAdapter(getActivity(), arrayList);
                                mProjectsRecycler.setAdapter(adapter);

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
            case R.id.motion_button:
                flag = 1;

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

                setBottomBar();
                getProjects("getallprojectsmoshen?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", 1);
                break;

            case R.id.gh_button:
                flag = 2;

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

                setBottomBar();
                getProjects("getallprojectsgraphic?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", 1);
                break;

            case R.id.wall_button:
                flag = 3;

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

                setBottomBar();
                getProjects("getallprojectswall?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", 1);
                break;

            case R.id.arch_button:
                flag = 4;

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

                setBottomBar();
                getProjects("getallprojectsarch?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", 1);
                break;

            case R.id.in_button:
                flag = 5;

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

                setBottomBar();
                getProjects("getallprojectsinter?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", 1);
                break;

            case R.id.tv_next:
                setBottomBar();
                current_page++;

                if (flag == 1) {
                    getProjects("getallprojectsmoshen?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 2) {
                    getProjects("getallprojectsgraphic?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 3) {
                    getProjects("getallprojectswall?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 4) {
                    getProjects("getallprojectsarch?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 5) {
                    getProjects("getallprojectsinter?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else {
                    getProjects("getallprojects?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                }
                break;

            case R.id.tv_back:
                setBottomBar();
                current_page--;

                if (flag == 1) {
                    getProjects("getallprojectsmoshen?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 2) {
                    getProjects("getallprojectsgraphic?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 3) {
                    getProjects("getallprojectswall?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 4) {
                    getProjects("getallprojectsarch?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else if (flag == 5) {
                    getProjects("getallprojectsinter?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                } else {
                    getProjects("getallprojects?token=" + ConstantInterFace.USER.getToken() + "&i_current_page=", current_page);
                }
                break;
        }
    }
}
