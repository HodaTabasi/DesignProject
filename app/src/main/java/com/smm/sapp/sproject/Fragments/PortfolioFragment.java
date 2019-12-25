package com.smm.sapp.sproject.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
    private TextView tv_next, tv_back;
    int current_page, total_pages, flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        setBottomBar();
        initView();

        bundle = getArguments();
        if (bundle != null && bundle.containsKey("designer_id") && bundle.containsKey("designer_name")) {
            designer_id = bundle.getInt("designer_id");
            designer_name = bundle.getString("designer_name");
            getDesignerPworks(designer_id, designer_name);
        } else {
            getPworks("&i_current_page=", 1);

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

                    getPworks("&name=" + s_search + "&i_current_page=", 1);

                    return true;
                }
                return false;
            }
        });


        tv_arch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                flag = 1;

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

                getPworks("&type=arch&i_current_page=", 1);

            }
        });

        tv_inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                flag = 2;

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

                getPworks("&type=inter&i_current_page=", 1);
            }
        });

        tv_graphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                flag = 3;

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

                getPworks("&type=graphic&i_current_page=", 1);
            }
        });

        tv_motion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                flag = 4;

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

                getPworks("&type=moshen&i_current_page=", 1);
            }
        });

        tv_wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                flag = 5;

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

                getPworks("&type=wall&i_current_page=", 1);
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBottomBar();
                current_page++;

                if (flag == 1) {
                    getPworks("&type=arch&i_current_page=", current_page);
                } else if (flag == 2) {
                    getPworks("&type=inter&i_current_page=", current_page);
                } else if (flag == 3) {
                    getPworks("&type=graphic&i_current_page=", current_page);
                } else if (flag == 4) {
                    getPworks("&type=moshen&i_current_page=", current_page);
                } else if (flag == 5) {
                    getPworks("&type=wall&i_current_page=", current_page);
                } else {
                    getPworks("&i_current_page=", current_page);
                }
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBottomBar();
                current_page--;
                if (flag == 1) {
                    getPworks("&type=arch&i_current_page=", current_page);
                } else if (flag == 2) {
                    getPworks("&type=inter&i_current_page=", current_page);
                } else if (flag == 3) {
                    getPworks("&type=graphic&i_current_page=", current_page);
                } else if (flag == 4) {
                    getPworks("&type=moshen&i_current_page=", current_page);
                } else if (flag == 5) {
                    getPworks("&type=wall&i_current_page=", current_page);
                } else {
                    getPworks("&i_current_page=", current_page);
                }
            }
        });

    }

    private void setBottomBar() {
        ConstantInterFace.tv_portfolio.setBackground(getResources().getDrawable(R.drawable.main_shape));
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
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
        tv_next = getView().findViewById(R.id.tv_next);
        tv_back = getView().findViewById(R.id.tv_back);
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

    private void getPworks(String URL, int current) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/searchpworks?token=" + ConstantInterFace.USER.getToken() + URL + current, new OkHttpCallback() {
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
                final JSONObject paginationObj = object.getJSONObject("pagination");
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
                                adapter = new PortfolioAdapter(getActivity(), arrayList);
                                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                                recyclerView.setAdapter(adapter);

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
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(getContext(), "لا يوجد نتائج", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
