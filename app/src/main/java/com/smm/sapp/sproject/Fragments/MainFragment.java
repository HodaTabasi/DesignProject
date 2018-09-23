package com.smm.sapp.sproject.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.Activities.ContainerActivity;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class MainFragment extends Fragment {
    CircleImageView img_user ;
    TextView _name ;
    TextView _specialization ;
    TextView tv_portfolio ;
    TextView tv_budget ;
    TextView tv_addProject;
    TextView tv_proposals;
    TextView tv_about ;
    TextView tv_search ;
    ImageView img_power ;
    ImageView img_notification;
    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    private void init(View view){
         img_user = view.findViewById(R.id.img_user);
         _name = view.findViewById(R.id.tv_name);
         _specialization = view.findViewById(R.id.tv_specialization);
         tv_portfolio = view.findViewById(R.id.tv_portfolio_main);
         tv_budget = view.findViewById(R.id.tv_budget);
         tv_addProject = view.findViewById(R.id.tv_addProject);
         tv_proposals = view.findViewById(R.id.tv_proposals);
         tv_about = view.findViewById(R.id.tv_about);
         tv_search = view.findViewById(R.id.tv_search);
         img_power = view.findViewById(R.id.img_power);
         img_notification = view.findViewById(R.id.img_notification);
    }

    private void onClickMethod(){
        _name.setText(ConstantInterFace.USER.getName());
        Picasso.get().load(ConstantInterFace.USER.getPhoto_link()).into(img_user);

        if (ConstantInterFace.USER.getType().equals("worker")) {
            _specialization.setText(ConstantInterFace.USER.getJob_type());
        } else {
            _specialization.setText("صاحب مشاريع");
        }

        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AccountFragment(), true);

            }
        });

        tv_portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddNewWork2Fragment(), true);

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

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddProjectFragment(), true);

            }
        });

        tv_proposals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyOffersFragment(), true);


            }
        });


        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SearchFragment(), true);

            }
        });

        img_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(getActivity())
                        .setMessage("هل تريد الخروج من التطبيق؟").setCancelable(false)
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("لا", null)
                        .show();

            }
        });

        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new NotificationFragment(), true);

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        init(getView());
        if (!ConstantInterFace.IS_REGISTER){
            onClickMethod();
        }
//        else {
//            new AlertDialog.Builder(getActivity())
//                    .setMessage("انت غير مسجل هل تريد تسجيل الدخول ؟").setCancelable(false)
//                    .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            FragmentsUtil.replaceFragment(getActivity(),R.id.container_activity,new  RegisterFragment());
//                        }
//                    })
//                    .setNegativeButton("لا", null)
//                    .show();
//        }

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SameemFragment(), true);

            }
        });
    }
}
