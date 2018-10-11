package com.smm.sapp.sproject.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.PathUtil;
import com.smm.sapp.sproject.Models.SkillsModel;
import com.smm.sapp.sproject.Models.UserModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class AccountFragment extends Fragment {
    UserModel userModel;

    TextView tv_profile;
    TextView tv_skills;
    TextView tv_fav;
    TextView tv_notification;
    TextView tv_bankAccount;
    TextView tv_name;
    TextView tv_title;
    RatingBar ratingBar;
    LinearLayout linear_rate;
    ImageView ic_back, img_user, img_edit;


    String name, title;
    private static final int REQUEST_CODE = 1;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        setBottomBar();
        init();
        if (!ConstantInterFace.IS_REGISTER) {
            onClickMethod();
            getProfileDataRequest();
        }
    }

    private void setBottomBar() {
        ConstantInterFace.tv_profile.setBackground(getResources().getDrawable(R.drawable.main_shape));
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
    }

    private void init() {
        tv_profile = getView().findViewById(R.id.tv_profile);
        tv_skills = getView().findViewById(R.id.tv_skills);
        tv_fav = getView().findViewById(R.id.tv_fav);
        tv_notification = getView().findViewById(R.id.tv_notification);
        tv_bankAccount = getView().findViewById(R.id.tv_bankAccount);
        tv_name = getView().findViewById(R.id.name);
        tv_title = getView().findViewById(R.id.title);
        ratingBar = getView().findViewById(R.id.account_rate);
        linear_rate = getView().findViewById(R.id.linear_rate);
        ic_back = getView().findViewById(R.id.ic_back);
        img_user = getView().findViewById(R.id.img_user);
        img_edit = getView().findViewById(R.id.img_edit);

    }

    private void onClickMethod() {

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ProfileFragment(), true);

            }
        });

        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(pickPhoto, REQUEST_CODE);
            }
        });

        linear_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackFragment commentsFragment = new FeedbackFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("commentsInfo", userModel.getComments());
                commentsFragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, commentsFragment, true);
            }
        });

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantInterFace.IS_USER_COMPLETEED) {
                    if (userModel.getType().equals("worker")) {
                        ProfileFragment fragment = new ProfileFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("type", userModel.getType());
                        bundle.putString("job_type", userModel.getJob_type());
                        bundle.putString("busniess_type", userModel.getBusniess_type());
                        bundle.putString("name", userModel.getName());
                        bundle.putString("bio", userModel.getBio());
                        bundle.putString("bu_mobile", userModel.getPhone());
                        bundle.putString("email", userModel.getEmail());
                        bundle.putString("bu_gender", userModel.getGender());
                        bundle.putString("dob", userModel.getDob());

                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);

                        Log.e("eee", userModel.getType());
                        Log.e("eee", userModel.getJob_type());
                        Log.e("eee", userModel.getBusniess_type());
                        Log.e("eee", userModel.getName());
                        Log.e("eee", userModel.getBio());
                        Log.e("eee", userModel.getPhone());
                        Log.e("eee", userModel.getEmail());
                        Log.e("eee", userModel.getGender());
                        Log.e("eee", userModel.getDob());

                    } else if (userModel.getType().equals("client")) {
                        ProfileFragment fragment = new ProfileFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("type", userModel.getType());
                        bundle.putString("name", userModel.getName());
                        bundle.putString("bu_mobile", userModel.getPhone());
                        bundle.putString("email", userModel.getEmail());
                        bundle.putString("bu_gender", userModel.getGender());
                        bundle.putString("dob", userModel.getDob());

                        Log.e("eee", userModel.getType());
                        Log.e("eee", userModel.getName());
                        Log.e("eee", userModel.getPhone());
                        Log.e("eee", userModel.getEmail());
                        Log.e("eee", userModel.getGender());
                        Log.e("eee", userModel.getDob());

                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                    }

                } else {
                    ProfileFragment fragment = new ProfileFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", ConstantInterFace.USER.getPhone());
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                }

            }
        });

        tv_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkillsFragment skillsFragment = new SkillsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("skillsInfo", userModel.getSkills());
                skillsFragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, skillsFragment, true);


            }
        });
        tv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new FavoriteFragment(), true);

            }
        });
        tv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AccountNotificationFragment(), true);

            }
        });

        tv_bankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BankFragment fragment = new BankFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("bankInfo", userModel.getBanks());
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
            }
        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }


    private void getProfileDataRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/myprofile", stringMap, new OkHttpCallback() {
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
                JSONObject jsonObject = new JSONObject(response.body().string());
                Gson gson = new Gson();
                userModel = gson.fromJson(jsonObject.getJSONObject("user").toString(), UserModel.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            if (userModel.getType().equals("worker")) {
                                tv_name.setText(userModel.getName());
                                Picasso.get().load(userModel.getPhoto_link()).into(img_user);
                                if (userModel.getJob_type().equals("arch")) {
                                    tv_title.setText("تصميم معماري");
                                } else if (userModel.getJob_type().equals("graphic")) {
                                    tv_title.setText("تصميم جرافيكس");
                                } else if (userModel.getJob_type().equals("inter")) {
                                    tv_title.setText("تصميم داخلي");
                                } else if (userModel.getJob_type().equals("moshen")) {
                                    tv_title.setText("تصاميم موشن");
                                } else if (userModel.getJob_type().equals("wall")) {
                                    tv_title.setText("الرسم الجداري");
                                }
                                ratingBar.setRating(Float.valueOf(userModel.getRate()));

                            } else if (userModel.getType().equals("client")) {

                                tv_name.setText(userModel.getName());
                                tv_title.setText("صاحب مشاريع");
                                ratingBar.setRating(Float.valueOf(userModel.getRate()));

                            }
                        } catch (Exception e) {

                        }


                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    String filePath = PathUtil.getPath(getActivity(), selectedImage);
                    Log.e("dd", " " + filePath);
                    //attachMap.put("similars[" + (k++) + "]", filePath);
                    Toast.makeText(getContext(), "تم اضافة الصورة بنجاح", Toast.LENGTH_SHORT).show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();

                }
            }

        }
    }
}
