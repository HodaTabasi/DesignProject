package com.example.maryam.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maryam.sproject.Models.ProjectsModels;
import com.example.maryam.sproject.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;


public class ViewProjectFragment extends Fragment {
    /**
     * منال القرشي
     */
    private TextView mUserName;
    /**
     * عميل صاحب مشاريع
     */
    private TextView mUserType;
    private CircleImageView mUserPhoto;
    private EditText mPName;
    private EditText mPType;
    private EditText mPStyle;
    private EditText mPColors;
    private EditText mPCity;
    private RecyclerView mPDesignYouLike;
    private RecyclerView mPProjectPhoto;
    private EditText mPArea;
    private EditText mPBalance;
    private EditText mPBio;
    private RecyclerView mPAttachment;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_project, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        initView(getView());
        putData();
    }

    private void initView(View view) {
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserType = (TextView) view.findViewById(R.id.user_type);
        mUserPhoto = (CircleImageView) view.findViewById(R.id.user_photo);
        mPName = (EditText) view.findViewById(R.id.p_name);
        mPType = (EditText) view.findViewById(R.id.p_type);
        mPStyle = (EditText) view.findViewById(R.id.p_style);
        mPColors = (EditText) view.findViewById(R.id.p_colors);
        mPCity = (EditText) view.findViewById(R.id.p_city);
        mPDesignYouLike = (RecyclerView) view.findViewById(R.id.p_design_you_like);
        mPProjectPhoto = (RecyclerView) view.findViewById(R.id.p_project_photo);
        mPArea = (EditText) view.findViewById(R.id.p_area);
        mPBalance = (EditText) view.findViewById(R.id.p_balance);
        mPBio = (EditText) view.findViewById(R.id.p_bio);
        mPAttachment = (RecyclerView) view.findViewById(R.id.p_attachment);
    }
    private void putData(){
        bundle = getArguments();
        ProjectsModels models = bundle.getParcelable("theProject");
        mUserName.setText(models.getUser().getName());
        mUserType.setText(models.getUser().getType());
        Picasso.get().load(models.getUser().getPhoto_link()).into(mUserPhoto);

        mPName.setText(models.getName());
        mPType.setText(models.getType());
        mPStyle.setText(models.getAddtion_info().getStyle());
        mPColors.setText(models.getAddtion_info().getColors());
        mPCity.setText(models.getAddtion_info().getCity());
        mPArea.setText(models.getAddtion_info().getArea());
        mPBalance.setText(models.getBalance());
        mPBio.setText(models.getDescr());
    }
}
