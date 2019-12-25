package com.smm.sapp.sproject.Fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.SkillsSearchAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.Models.SkillsModel;
import com.smm.sapp.sproject.Models.User;
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


public class AccountSearchFragment extends Fragment {
    ImageView ic_back;
    CircleImageView profileImg;
    TextView tv_name, tv_title, tv_comments, tv_portfolio, tv_completed, tv_inProgress, tv_rateProjects, tv_fav, tv_chooseMe, tv_noSkills;
    RatingBar rate_bar;
    EditText et_bio;
    RecyclerView recycler_skill;
    Bundle bundle;
    User models;
    int worker_id;
    ArrayList<SkillsModel> arrayList = new ArrayList<>();
    ArrayList<ProjectsModels> finishedProjectList = new ArrayList<>();
    ArrayList<ProjectsModels> inProgressProjectList = new ArrayList<>();

    SkillsSearchAdapter adapter;
    int rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_account, container, false);
        ic_back = view.findViewById(R.id.ic_back);
        profileImg = view.findViewById(R.id.profileImg);
        tv_name = view.findViewById(R.id.tv_name);
        tv_title = view.findViewById(R.id.tv_title);
        tv_comments = view.findViewById(R.id.tv_comments);
        tv_portfolio = view.findViewById(R.id.tv_portfolio);
        tv_completed = view.findViewById(R.id.tv4);
        tv_inProgress = view.findViewById(R.id.tv5);
        tv_rateProjects = view.findViewById(R.id.tv6);
        tv_fav = view.findViewById(R.id.tv_fav);
        tv_chooseMe = view.findViewById(R.id.tv_chooseMe);
        rate_bar = view.findViewById(R.id.rate_bar);
        et_bio = view.findViewById(R.id.et_bio);
        recycler_skill = view.findViewById(R.id.recycler_skill);
        tv_noSkills = view.findViewById(R.id.tv_noSkills);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        getData();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        tv_portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PortfolioFragment fragment = new PortfolioFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("designer_id", models.getId());
                bundle.putString("designer_name", models.getName());
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
            }
        });

        tv_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackFragment commentsFragment = new FeedbackFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("designer_id", models.getId());
                commentsFragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, commentsFragment, true);
            }
        });

        tv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ConstantInterFace.IS_USER_FAVORITE = false) {
                addTofav();
//                }

            }
        });
        tv_chooseMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void addTofav() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("target_id", String.valueOf(worker_id));
        map.put("target_type", "user");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/likedislike", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MyProgressDialog.dismissDialog();
                Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                JSONObject statusObj = object.getJSONObject("status");
                final String success = statusObj.getString("success");
                final String message = statusObj.getString("message");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (success.equals("true") && message.equals("like Returned")) {
//                            ConstantInterFace.IS_USER_FAVORITE = true;
                            Toast.makeText(getContext(), "تمت الاضافة للمفضلة", Toast.LENGTH_LONG).show();

                        } else if (success.equals("true") && message.equals("dislike Returned")) {
//                            ConstantInterFace.IS_USER_FAVORITE = false;
                            Toast.makeText(getContext(), "تم الحذف من المفضلة", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }


    private void getData() {
        bundle = getArguments();
        models = bundle.getParcelable("worker");

        //rate_bar.setRating(Float.valueOf(models.getRate()));

        ///////////////

        if (models.getName() == null) {
            tv_name.setText("");
        } else {
            StringBuilder name = new StringBuilder(models.getName());
            for (int i = 1; i < name.length() - 1; i++) {
                name.setCharAt(i, '*');
            }
            tv_name.setText(name);
        }

        if (models.getPhoto_link() != null) {
            Picasso.get().load(models.getPhoto_link()).into(profileImg);
        }

        if (models.getJob_type() != null) {
            if (models.getJob_type().equals("wall")) {
                tv_title.setText("مصمم جداري");
            } else if (models.getJob_type().equals("arch")) {
                tv_title.setText("مصمم معماري");
            } else if (models.getJob_type().equals("inter")) {
                tv_title.setText("مصمم داخلي");
            } else if (models.getJob_type().equals("moshen")) {
                tv_title.setText("مصمم موشن");
            } else if (models.getJob_type().equals("graphic")) {
                tv_title.setText("مصمم جرافيكس");
            }
        } else {
            tv_title.setText("");
        }

        if (models.getBio() != null) {
            et_bio.setText(models.getBio());

        } else {
            et_bio.setText("");
        }


        getSkills();

    }

    private void getSkills() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("user_id", String.valueOf(models.getId()));

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
                            TypeToken<List<SkillsModel>> token = new TypeToken<List<SkillsModel>>() {
                            };
                            TypeToken<List<ProjectsModels>> token1 = new TypeToken<List<ProjectsModels>>() {
                            };
                            try {
                                finishedProjectList = gson.fromJson(userObj.getJSONArray("finished_projects").toString(), token1.getType());
                                inProgressProjectList = gson.fromJson(userObj.getJSONArray("inprogress_projects").toString(), token1.getType());

                                int finished = finishedProjectList.size();
                                int in_progress = inProgressProjectList.size();

                                try {
//                                    rate = (finished / in_progress) * 100;
                                    rate = (in_progress / (finished + in_progress)) * 100;
                                } catch (Exception e) {
                                }

                                tv_completed.setText(String.valueOf(finished));
                                tv_inProgress.setText(String.valueOf(in_progress));
                                tv_rateProjects.setText(String.valueOf(rate) + "%");

                                arrayList = gson.fromJson(userObj.getJSONArray("skills").toString(), token.getType());
                                if (arrayList.isEmpty()) {
                                    recycler_skill.setVisibility(View.GONE);
                                    tv_noSkills.setVisibility(View.VISIBLE);
                                } else {
                                    recycler_skill.setVisibility(View.VISIBLE);
                                    tv_noSkills.setVisibility(View.GONE);

                                    adapter = new SkillsSearchAdapter(getActivity(), arrayList);
                                    recycler_skill.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
                                    recycler_skill.setAdapter(adapter);
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

