package com.smm.sapp.sproject.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class EditProposalFragment extends Fragment {

    ImageView ic_back;
    private TextView mTvAccount;
    private ImageView mIcBack;
    private RelativeLayout mToolbar;
    private CircleImageView mImg;
    private TextView mTvName;
    private RatingBar mRateBar;
    private TextView mTvSpecialization;
    private TextView mDay;
    private TextView mExhibition;
    private TextView mMoney;
    private LinearLayout mLinear;
    private View mView;
    private EditText mEtProposal;
    private View mView2;
    private TextView mEdit;
    private TextView mAtidim;
    private TextView mTalk;
    private LinearLayout mDwe;
    User user;
    OfferModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_proposal, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        model = bundle.getParcelable("object");

        ic_back = getView().findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        onClickMethod();
        if (ConstantInterFace.USER.getType().equals("worker")) {
            mEdit.setVisibility(View.VISIBLE);
            mDwe.setVisibility(View.GONE);
            mEtProposal.setEnabled(true);
        } else {
            mDwe.setVisibility(View.VISIBLE);
            mEdit.setVisibility(View.GONE);
            mEtProposal.setEnabled(false);
        }
        getUserProfile(model.getId());

    }

    private void getUserProfile(int id) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("user_id", id + "");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/userprofile", stringMap, new OkHttpCallback() {
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
                String s = response.body().string();
                Log.e("dd", s);
                final JSONObject object = new JSONObject(s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONObject object1 = object.getJSONObject("status");
                            Gson gson = new Gson();
                            if (object1.getBoolean("success")) {
                                user = gson.fromJson(object.getJSONObject("user").toString(), User.class);
                                putData(user);
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void approveAnOffer(int id) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("offer_id", id + "");
        stringMap.put("project_id", model.getProject_id());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/approveanoffer", stringMap, new OkHttpCallback() {
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
                String s = response.body().string();
                Log.e("dd", s);
                final JSONObject object = new JSONObject(s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONObject object1 = object.getJSONObject("status");
                            if (object1.getBoolean("success")) {
                                Toast.makeText(getActivity(), "تم اعتماد العرض", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "حصل خطا ما", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void putData(User user) {

        try {
            Picasso.get().load(user.getPhoto_link()).into(mImg);
            mTvName.setText(user.getName());
            mRateBar.setRating(Float.valueOf(user.getRate()));

            if (user.getJob_type().equals("inter")) {
                mTvSpecialization.setText("مصمم داخلي");
            } else if (user.getJob_type().equals("arch")) {
                mTvSpecialization.setText("مصمم معمماري");
            } else if (user.getJob_type().equals("graphic")) {
                mTvSpecialization.setText("مصمم جرافيكس");
            } else if (user.getJob_type().equals("moshen")) {
                mTvSpecialization.setText("مصمم موشن");
            } else if (user.getJob_type().equals("wall")) {
                mTvSpecialization.setText("مصمم جداري");
            }

            mMoney.setText(" السعر " + "" + model.getBalance() + " ريال ");
            mDay.setText(" في " + model.getDur() + " يوم ");
            mEtProposal.setText(model.getDescr());
        } catch (Exception e) {

        }


        Log.e("pppppppp", user.getName() + user.getRate() + user.getJob_type() + model.getBalance()
                + model.getDur() + model.getDescr());
    }

    private void initView(View view) {
        mTvAccount = (TextView) view.findViewById(R.id.tv_account);
        mIcBack = (ImageView) view.findViewById(R.id.ic_back);
        mToolbar = (RelativeLayout) view.findViewById(R.id.toolbar);
        mImg = (CircleImageView) view.findViewById(R.id.img);
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mRateBar = (RatingBar) view.findViewById(R.id.rate_bar);
        mTvSpecialization = (TextView) view.findViewById(R.id.tv_specialization);
        mDay = (TextView) view.findViewById(R.id.day);
        mExhibition = (TextView) view.findViewById(R.id.exhibition);
        mMoney = (TextView) view.findViewById(R.id.money);
        mLinear = (LinearLayout) view.findViewById(R.id.linear);
        mView = (View) view.findViewById(R.id.view);
        mEtProposal = (EditText) view.findViewById(R.id.et_proposal);
        mView2 = (View) view.findViewById(R.id.view2);
        mEdit = (TextView) view.findViewById(R.id.edit);
        mAtidim = (TextView) view.findViewById(R.id.Atidim);
        mTalk = (TextView) view.findViewById(R.id.talk);
        mDwe = (LinearLayout) view.findViewById(R.id.dwe);
    }

    private void onClickMethod() {

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getApproved().equals("0") && model.getFinished().equals("0")) {
                    ViewProjectFragment fragment = new ViewProjectFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("object", model);
                    bundle.putBoolean("flag", true);
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                } else
                    Toast.makeText(getContext(), "لا يمكن التعديل على هذا العرض ", Toast.LENGTH_SHORT).show();
            }
        });

        mAtidim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveAnOffer(model.getId());
            }
        });

        mTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnderwayFragment fragment = new UnderwayFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("offer", model);
                bundle.putParcelable("user", user);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
            }
        });
    }
}
