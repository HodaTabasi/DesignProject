package com.smm.sapp.sproject.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.DesignProfileAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.SearchWorkersModel;
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


public class SearchFragment extends Fragment {
    RecyclerView resSearch;
    private EditText et_search;
    private TextView tv_motion;
    private TextView tv_graphic;
    private TextView tv_wall;
    private TextView tv_arch;
    private TextView tv_inter;
    private RatingBar mRattingDesigner;
    private ImageView ic_back;
    ArrayList<SearchWorkersModel> profilesList = new ArrayList<>();
    String s_search;
    DesignProfileAdapter adapter;


    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    private void initView() {
        et_search = getView().findViewById(R.id.txt_search);
        tv_motion = getView().findViewById(R.id.motion_button);
        tv_graphic = getView().findViewById(R.id.gh_button);
        tv_wall = getView().findViewById(R.id.wall_button);
        tv_arch = getView().findViewById(R.id.arch_button);
        tv_inter = getView().findViewById(R.id.in_button);
        mRattingDesigner = getView().findViewById(R.id.ratting_designer);
        resSearch = getView().findViewById(R.id.res_search);
        ic_back = getView().findViewById(R.id.ic_back);

    }

    private void setBottomBar() {
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
        setBottomBar();

        getWorkers("");

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    s_search = textView.getText().toString();
                    getWorkers("?name=" + s_search);

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

                    tv_motion.setBackgroundResource(R.drawable.account_shape);
                    tv_motion.setTextColor(Color.parseColor("#000000"));

                    tv_arch.setBackgroundResource(R.drawable.account_shape);
                    tv_arch.setTextColor(Color.parseColor("#000000"));

                    tv_inter.setBackgroundResource(R.drawable.account_shape);
                    tv_inter.setTextColor(Color.parseColor("#000000"));

                    tv_graphic.setBackgroundResource(R.drawable.account_shape);
                    tv_graphic.setTextColor(Color.parseColor("#000000"));

                    tv_wall.setBackgroundResource(R.drawable.account_shape);
                    tv_wall.setTextColor(Color.parseColor("#000000"));

                    return true;
                }
                return false;
            }
        });


        tv_arch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");

                tv_arch.setBackgroundResource(R.drawable.blue_shape);
                tv_arch.setTextColor(Color.parseColor("#ffffff"));

                tv_inter.setBackgroundResource(R.drawable.account_shape);
                tv_inter.setTextColor(Color.parseColor("#000000"));

                tv_graphic.setBackgroundResource(R.drawable.account_shape);
                tv_graphic.setTextColor(Color.parseColor("#000000"));

                tv_motion.setBackgroundResource(R.drawable.account_shape);
                tv_motion.setTextColor(Color.parseColor("#000000"));

                tv_wall.setBackgroundResource(R.drawable.account_shape);
                tv_wall.setTextColor(Color.parseColor("#000000"));

                getWorkers("?job_type=arch");

            }
        });

        tv_inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");

                tv_inter.setBackgroundResource(R.drawable.blue_shape);
                tv_inter.setTextColor(Color.parseColor("#ffffff"));

                tv_arch.setBackgroundResource(R.drawable.account_shape);
                tv_arch.setTextColor(Color.parseColor("#000000"));

                tv_graphic.setBackgroundResource(R.drawable.account_shape);
                tv_graphic.setTextColor(Color.parseColor("#000000"));

                tv_motion.setBackgroundResource(R.drawable.account_shape);
                tv_motion.setTextColor(Color.parseColor("#000000"));

                tv_wall.setBackgroundResource(R.drawable.account_shape);
                tv_wall.setTextColor(Color.parseColor("#000000"));

                getWorkers("?job_type=inter");
            }
        });

        tv_graphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");

                tv_graphic.setBackgroundResource(R.drawable.blue_shape);
                tv_graphic.setTextColor(Color.parseColor("#ffffff"));

                tv_arch.setBackgroundResource(R.drawable.account_shape);
                tv_arch.setTextColor(Color.parseColor("#000000"));

                tv_inter.setBackgroundResource(R.drawable.account_shape);
                tv_inter.setTextColor(Color.parseColor("#000000"));

                tv_motion.setBackgroundResource(R.drawable.account_shape);
                tv_motion.setTextColor(Color.parseColor("#000000"));

                tv_wall.setBackgroundResource(R.drawable.account_shape);
                tv_wall.setTextColor(Color.parseColor("#000000"));

                getWorkers("?job_type=graphic");
            }
        });

        tv_motion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");

                tv_motion.setBackgroundResource(R.drawable.blue_shape);
                tv_motion.setTextColor(Color.parseColor("#ffffff"));

                tv_arch.setBackgroundResource(R.drawable.account_shape);
                tv_arch.setTextColor(Color.parseColor("#000000"));

                tv_inter.setBackgroundResource(R.drawable.account_shape);
                tv_inter.setTextColor(Color.parseColor("#000000"));

                tv_graphic.setBackgroundResource(R.drawable.account_shape);
                tv_graphic.setTextColor(Color.parseColor("#000000"));

                tv_wall.setBackgroundResource(R.drawable.account_shape);
                tv_wall.setTextColor(Color.parseColor("#000000"));

                getWorkers("?job_type=moshen");
            }
        });

        tv_wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");

                tv_wall.setBackgroundResource(R.drawable.blue_shape);
                tv_wall.setTextColor(Color.parseColor("#ffffff"));

                tv_arch.setBackgroundResource(R.drawable.account_shape);
                tv_arch.setTextColor(Color.parseColor("#000000"));

                tv_inter.setBackgroundResource(R.drawable.account_shape);
                tv_inter.setTextColor(Color.parseColor("#000000"));

                tv_motion.setBackgroundResource(R.drawable.account_shape);
                tv_motion.setTextColor(Color.parseColor("#000000"));

                tv_graphic.setBackgroundResource(R.drawable.account_shape);
                tv_graphic.setTextColor(Color.parseColor("#000000"));

                getWorkers("?job_type=wall");
            }
        });


    }

    private void getWorkers(String URL) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/searchworkers" + URL, new OkHttpCallback() {
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
                            TypeToken<List<SearchWorkersModel>> token = new TypeToken<List<SearchWorkersModel>>() {
                            };
                            try {
                                profilesList = gson.fromJson(object.getJSONArray("workers").toString(), token.getType());
                                for (int i = 0; i < profilesList.size(); i++) {
                                    if (profilesList.get(i).getName().equals(ConstantInterFace.USER.getName())) {
                                        profilesList.remove(i);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter = new DesignProfileAdapter(getContext(), profilesList);
                            resSearch.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                            resSearch.setAdapter(adapter);
                        } else {
                            Toast.makeText(getContext(), "لا توجد نتائج", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }


}
