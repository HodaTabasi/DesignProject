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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.PortfolioAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.PortfolioModel;
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

public class PortfolioFragment extends Fragment {

    ImageView ic_back;
    TextView tv_inter, tv_arch, tv_wall, tv_graphic, tv_motion;
    EditText et_search;
    RecyclerView recyclerView;
    String s_search, designer_name;
    ArrayList<PortfolioModel> arrayList = new ArrayList<>();
    PortfolioAdapter adapter;
    Bundle bundle;
    int designer_id;

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
        initView();

        bundle = getArguments();
        if (bundle != null && bundle.containsKey("designer_id") && bundle.containsKey("designer_name")) {
            designer_id = bundle.getInt("designer_id");
            designer_name = bundle.getString("designer_name");
            getDesignerPworks(designer_id, designer_name);
        } else {
            getPworks("");

        }


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

                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

                    getPworks("?name=" + s_search);


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

                getPworks("?type=arch");

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

                getPworks("?type=inter");
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

                getPworks("?type=graphic");
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

                getPworks("?type=moshen");
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

                getPworks("?type=wall");
            }
        });

    }

    private void getDesignerPworks(int id, final String name) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("user_id", String.valueOf(id));

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
                            TypeToken<List<PortfolioModel>> token = new TypeToken<List<PortfolioModel>>() {
                            };
                            try {
                                arrayList = gson.fromJson(userObj.getJSONArray("pworks").toString(), token.getType());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter = new PortfolioAdapter(getActivity(), arrayList, name);

                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                            recyclerView.setAdapter(adapter);
                        } else {

                            Toast.makeText(getContext(), "لا يوجد نتائج", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }

    private void initView() {
        ic_back = getView().findViewById(R.id.ic_back);
        tv_inter = getView().findViewById(R.id.tv_inter);
        tv_arch = getView().findViewById(R.id.tv_arch);
        tv_wall = getView().findViewById(R.id.tv_wall);
        tv_graphic = getView().findViewById(R.id.tv_graphic);
        tv_motion = getView().findViewById(R.id.tv_motion);
        et_search = getView().findViewById(R.id.search);
        recyclerView = getView().findViewById(R.id.recycler);
    }

    private void getPworks(String URL) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/searchpworks" + URL, new OkHttpCallback() {
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

                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
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
