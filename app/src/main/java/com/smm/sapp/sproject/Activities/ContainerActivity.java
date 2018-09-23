package com.smm.sapp.sproject.Activities;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AccountFragment;
import com.smm.sapp.sproject.Fragments.AddNewWork2Fragment;
import com.smm.sapp.sproject.Fragments.BrowseProjectsFragment;
import com.smm.sapp.sproject.Fragments.MainFragment;
import com.smm.sapp.sproject.Fragments.PortfolioFragment;
import com.smm.sapp.sproject.Fragments.RegisterFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Fragments.MyMessageFragment;
import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ContainerActivity extends AppCompatActivity {

    Fragment currentFragment;
//    public static TextView tv_msgs, tv_projects, tv_home, tv_portfolio, tv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FragmentsUtil.addFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(), false);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "JFFlatregular.ttf", true);

        //Bottom_bar
        ConstantInterFace.tv_msgs = findViewById(R.id.tv_msgs);
        ConstantInterFace.tv_projects = findViewById(R.id.tv_projects);
        ConstantInterFace.tv_home = findViewById(R.id.tv_home);
        ConstantInterFace.tv_portfolio = findViewById(R.id.tv_portfolio);
        ConstantInterFace.tv_profile = findViewById(R.id.tv_profile);

        if (!ConstantInterFace.IS_REGISTER) {

            ConstantInterFace.tv_msgs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MyMessageFragment(), true);
                    ConstantInterFace.tv_msgs.setBackground(getResources().getDrawable(R.drawable.main_shape));
                    ConstantInterFace.tv_projects.setBackgroundResource(0);
                    ConstantInterFace.tv_home.setBackgroundResource(0);
                    ConstantInterFace.tv_portfolio.setBackgroundResource(0);
                    ConstantInterFace.tv_profile.setBackgroundResource(0);
                }
            });

            ConstantInterFace.tv_portfolio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new PortfolioFragment(), true);
                    ConstantInterFace.tv_portfolio.setBackground(getResources().getDrawable(R.drawable.main_shape));
                    ConstantInterFace.tv_msgs.setBackgroundResource(0);
                    ConstantInterFace.tv_projects.setBackgroundResource(0);
                    ConstantInterFace.tv_home.setBackgroundResource(0);
                    ConstantInterFace.tv_profile.setBackgroundResource(0);


                }
            });

            ConstantInterFace.tv_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new AccountFragment(), true);
                    ConstantInterFace.tv_profile.setBackground(getResources().getDrawable(R.drawable.main_shape));
                    ConstantInterFace.tv_msgs.setBackgroundResource(0);
                    ConstantInterFace.tv_projects.setBackgroundResource(0);
                    ConstantInterFace.tv_home.setBackgroundResource(0);
                    ConstantInterFace.tv_portfolio.setBackgroundResource(0);

                }
            });


        }
//        else {
//            new AlertDialog.Builder(this)
//                    .setMessage("انت غير مسجل هل تريد تسجيل الدخول ؟").setCancelable(false)
//                    .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                           FragmentsUtil.replaceFragment(ContainerActivity.this,R.id.container_activity,new  RegisterFragment());
//                        }
//                    })
//                    .setNegativeButton("لا", null)
//                    .show();
//        }

        ConstantInterFace.tv_projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new BrowseProjectsFragment(), true);
                ConstantInterFace.tv_projects.setBackground(getResources().getDrawable(R.drawable.main_shape));
                ConstantInterFace.tv_msgs.setBackgroundResource(0);
                ConstantInterFace.tv_home.setBackgroundResource(0);
                ConstantInterFace.tv_portfolio.setBackgroundResource(0);
                ConstantInterFace.tv_profile.setBackgroundResource(0);
            }
        });

        ConstantInterFace.tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(), true);
                ConstantInterFace.tv_home.setBackground(getResources().getDrawable(R.drawable.main_shape));
                ConstantInterFace.tv_msgs.setBackgroundResource(0);
                ConstantInterFace.tv_projects.setBackgroundResource(0);
                ConstantInterFace.tv_portfolio.setBackgroundResource(0);
                ConstantInterFace.tv_profile.setBackgroundResource(0);

            }
        });


    }

}
