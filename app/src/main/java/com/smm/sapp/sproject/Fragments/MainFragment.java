package com.smm.sapp.sproject.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.smm.sapp.sproject.Activities.RegistrationActivity;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.PathUtil;
import com.smm.sapp.sproject.HelperClass.SharedPreferencesApp;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.Models.UserModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
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

public class MainFragment extends Fragment {
    CircleImageView img_user;
    TextView _name;
    TextView _specialization;
    TextView tv_portfolio;
    TextView tv_budget;
    TextView tv_addProject;
    TextView tv_proposals;
    TextView tv_about;
    TextView tv_search;
    TextView notification_num;
    ImageView img_power;
    ImageView img_notification, img_edit;
    String refreshedToken;
    RatingBar ratting_designer;
    UserModel userModel;
    private static final int REQUEST_CODE = 1;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    private void init(View view) {
        img_user = view.findViewById(R.id.img_user);
        _name = view.findViewById(R.id.tv_name);
        _specialization = view.findViewById(R.id.tv_specialization);
        tv_portfolio = view.findViewById(R.id.tv_portfolio_main);
        tv_budget = view.findViewById(R.id.tv_budget);
        tv_addProject = view.findViewById(R.id.tv_addProject);
        tv_proposals = view.findViewById(R.id.tv_proposals);
        tv_about = view.findViewById(R.id.tv_about);
        tv_search = view.findViewById(R.id.tv_search);
        notification_num = view.findViewById(R.id.notification_num);
        img_power = view.findViewById(R.id.img_power);
        img_notification = view.findViewById(R.id.img_notification);
        img_edit = view.findViewById(R.id.img_edit);
        ratting_designer = view.findViewById(R.id.ratting_designer);
        refreshedToken = FirebaseInstanceId.getInstance().getToken();

        LayerDrawable stars = (LayerDrawable) ratting_designer.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
    }

    private void onClickMethod() {

        if (ConstantInterFace.IS_USER_COMPLETEED) {
            //registered user
            img_user.setImageResource(0);
            getProfileData();
            Log.e("ttttt","completed");

        } else if (ConstantInterFace.USER.getPhoto_link() == null &&
                ConstantInterFace.USER.getName() == null &&
                ConstantInterFace.USER.getJob_type() == null) {

            Log.e("ttttt","notcompleted");

            ConstantInterFace.IS_USER_COMPLETEED = false;
            img_edit.setImageResource(R.drawable.ic_edit_red);
            _name.setText("");
            _specialization.setText("");

            Snackbar snackbar = Snackbar.make(getView(), "يرجى تعبئة بياناتك الشخصية", Snackbar.LENGTH_LONG);
            snackbar.show();

            TextView tv = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextSize(12f);
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "JFFlatregular.ttf");
            tv.setTypeface(font);

        } else if (ConstantInterFace.USER.getPhoto_link() != null ||
                ConstantInterFace.USER.getName() != null ||
                ConstantInterFace.USER.getJob_type() != null) {

            ConstantInterFace.IS_USER_COMPLETEED = true;
            img_user.setImageResource(0);
            getProfileData();
            Log.e("ttttt","2");

        }

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstantInterFace.tv_profile.setBackground(getResources().getDrawable(R.drawable.main_shape));
                ConstantInterFace.tv_projects.setBackgroundResource(0);
                ConstantInterFace.tv_home.setBackgroundResource(0);
                ConstantInterFace.tv_portfolio.setBackgroundResource(0);
                ConstantInterFace.tv_msgs.setBackgroundResource(0);

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

                    } else if (userModel.getType().equals("client")) {
                        ProfileFragment fragment = new ProfileFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("type", userModel.getType());
                        bundle.putString("name", userModel.getName());
                        bundle.putString("bu_mobile", userModel.getPhone());
                        bundle.putString("email", userModel.getEmail());
                        bundle.putString("bu_gender", userModel.getGender());
                        bundle.putString("dob", userModel.getDob());

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

        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_CODE);
            }
        });

        tv_portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("flag", true);
                AddNewWork2Fragment fragment = new AddNewWork2Fragment();
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);

            }
        });

        tv_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyAccountFragment(), true);

            }
        });

        tv_addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantInterFace.USER.getType().equals("client")) {
                    AddProjectFragment fragment = new AddProjectFragment();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                } else
                    Toast.makeText(getContext(), "غير مخول لك بالدخول هذه الواجهة خاصة بصاحب المشاريع ", Toast.LENGTH_SHORT).show();


            }
        });

        tv_proposals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantInterFace.USER.getType().equals("client")){
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyProjectFragment(), true);
            } else {

                    MyOffersFragment fragment = new MyOffersFragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isUpdated", true);
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                }


            }
        });


        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SearchFragment(), true);

            }
        });


        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification_num.setVisibility(View.INVISIBLE);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new NotificationFragment(), true);

            }
        });

    }


    private void getProfileData() {
        MyRequest myRequest = new MyRequest();
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/myprofile", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();

                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                Log.e("yyyy", "ooooo");

                JSONObject jsonObject = new JSONObject(response.body().string());
                Gson gson = new Gson();
                userModel = gson.fromJson(jsonObject.getJSONObject("user").toString(), UserModel.class);

                Log.e("yyyyyy", jsonObject.toString());


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        img_edit.setImageResource(R.drawable.ic_edit);
                        if (userModel.getType().equals("worker")) {
                            _name.setText(userModel.getName());
                            if (userModel.getJob_type().equals("arch")) {
                                _specialization.setText("تصميم معماري");
                            } else if (userModel.getJob_type().equals("graphic")) {
                                _specialization.setText("تصميم جرافيكس");
                            } else if (userModel.getJob_type().equals("inter")) {
                                _specialization.setText("تصميم داخلي");
                            } else if (userModel.getJob_type().equals("moshen")) {
                                _specialization.setText("تصاميم موشن");
                            } else if (userModel.getJob_type().equals("wall")) {
                                _specialization.setText("الرسم الجداري");
                            }
                            Picasso.get().load(ConstantInterFace.USER.getPhoto_link()).into(img_user);
                            ratting_designer.setRating(Float.valueOf(userModel.getRate()));

                        } else if (userModel.getType().equals("client")) {
                            _name.setText(userModel.getName());
                            _specialization.setText("صاحب مشاريع");
                            Picasso.get().load(ConstantInterFace.USER.getPhoto_link()).into(img_user);
                            ratting_designer.setRating(Float.valueOf(userModel.getRate()));
                        }
                       getSetsData();
                    }
                });
            }
        });
    }

    private void getSetsData() {
        MyRequest myRequest = new MyRequest();

        myRequest.GetCall("http://smm.smmim.com/waell/public/api/sets?token="+ConstantInterFace.USER.getToken(), new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                String s = response.body().string();
                Log.e("okHttpClient",""+s);

                final JSONObject jsonObject = new JSONObject(s);
                final JSONObject object = jsonObject.getJSONObject("status");
                final Gson gson = new Gson();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Log.e("dfrgthyum",jsonObject.getString("unseen_notifications") + "dcs");
                               ConstantInterFace.NOTIFICATION_NUMBER = Integer.parseInt(jsonObject.getString("unseen_notifications"));
                               if (ConstantInterFace.NOTIFICATION_NUMBER == 0)
                                   notification_num.setVisibility(View.INVISIBLE);
                               else {
                                   notification_num.setVisibility(View.VISIBLE);
                                   notification_num.setText(ConstantInterFace.NOTIFICATION_NUMBER+" ");
                               }

                            } else {
                                Toast.makeText(getContext(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        setBottomBar();
        init(getView());

        if (ConstantInterFace.USER.getType().equals("client"))
            tv_proposals.setText("مشاريعي");
         else
            tv_proposals.setText("عروضي");

        //registered user
        if (!ConstantInterFace.IS_REGISTER) {
            onClickMethod();
            changeToken();

            if (ConstantInterFace.NOTIFICATION_NUMBER != 0) {
                notification_num.setVisibility(View.VISIBLE);
                notification_num.setText(ConstantInterFace.NOTIFICATION_NUMBER + " ");
            } else {
                notification_num.setVisibility(View.INVISIBLE);
            }

        }
        //unregistered user
        else {
            _name.setText("");
            _specialization.setText("");
            getSnack();

            img_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSnack();
                }
            });
            img_notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSnack();
                }
            });
            tv_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSnack();
                }
            });
            tv_addProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSnack();
                }
            });
            tv_budget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSnack();
                }
            });
            tv_portfolio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSnack();
                }
            });
            tv_proposals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getSnack();
                }
            });
        }

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SameemFragment(), true);
            }
        });


        img_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("هل تريد الخروج من التطبيق؟").setCancelable(false)
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferencesApp.getInstance(getContext()).clearData();
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("لا", null)
                        .show();
            }
        });

    }

    private void setBottomBar() {
        ConstantInterFace.tv_home.setBackground(getResources().getDrawable(R.drawable.main_shape));
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
    }

    private void changeToken() {
        MyProgressDialog.showDialog(getActivity());
        MyRequest request = new MyRequest();
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("fcm_token", refreshedToken);
//        Log.e("dd",refreshedToken);
        request.PostCall("http://smm.smmim.com/waell/public/api/changeuserfcm", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.e("fffd", "dsgsgew");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                String s = response.body().string();
                Log.e("okHttpClient", "" + s);
                JSONObject object = new JSONObject(s);
                JSONObject object1 = object.getJSONObject("status");
                Log.e("fffds", "dsgsgew");
                if (object1.getBoolean("success")) {
                    Log.e("fff", object1.getString("message"));
                } else {
                    Log.e("fff", object1.getString("message") + "dsgsgew");
                }
            }
        });
    }

    private void getSnack() {
        Snackbar snackbar = Snackbar.make(getView(), "أنت غير مسجل في صمم!", Snackbar.LENGTH_LONG)
                .setAction("تسجيل", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ConstantInterFace.IS_REGISTER = false;
                        Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });

        snackbar.show();

        TextView tv = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        TextView tv2 = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_action);
        tv.setTextSize(12f);
        tv2.setTextSize(12f);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "JFFlatregular.ttf");
        tv.setTypeface(font);
        tv2.setTypeface(font);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    String filePath = PathUtil.getPath(getActivity(), selectedImage);
                    sendImgRequest(filePath);
                } catch (URISyntaxException e) {
                    e.printStackTrace();

                }
            }

        }
    }

    private void sendImgRequest(final String filePath) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/updateProfilePhoto", map, filePath, "photo_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();

                final JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject statusobj = jsonObject.getJSONObject("status");
                String success = statusobj.getString("success");
                final Gson gson = new Gson();
//                userModel = gson.fromJson(jsonObject.getJSONObject("user").toString(), UserModel.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (statusobj.getBoolean("success")) {
                                ConstantInterFace.USER = gson.fromJson(jsonObject.getJSONObject("user").toString(), User.class);
                                Picasso.get().load(ConstantInterFace.USER.getPhoto_link()).into(img_user);
                                SharedPreferencesApp.getInstance(getContext()).updateObject(ConstantInterFace.USER);
                                Toast.makeText(getActivity(), "تم اضافة الصورة بنجاح", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getActivity(), "لم يتم الاضافة" , Toast.LENGTH_LONG).show();
                                Log.e("fffdec",statusobj.getString("error") + "  /  " + filePath);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }});
            }
        });
    }
}
