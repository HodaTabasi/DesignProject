package com.smm.sapp.sproject.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smm.sapp.sproject.Fragments.AccountFragment;
import com.smm.sapp.sproject.Fragments.AddNewWork2Fragment;
import com.smm.sapp.sproject.Fragments.BrowseProjectsFragment;
import com.smm.sapp.sproject.Fragments.MainFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Fragments.MyMessageFragment;
import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ContainerActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FragmentsUtil.addFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(), false);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "JFFlatregular.ttf", true);

        //Bottom_bar
        final TextView tv_msgs = findViewById(R.id.tv_msgs);
        final TextView tv_projects = findViewById(R.id.tv_projects);
        final TextView tv_home = findViewById(R.id.tv_home);
        final TextView tv_portfolio = findViewById(R.id.tv_portfolio);
        final TextView tv_profile = findViewById(R.id.tv_profile);


        tv_msgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MyMessageFragment(), true);
                tv_msgs.setBackground(getResources().getDrawable(R.drawable.main_shape));
                tv_projects.setBackgroundResource(0);
                tv_home.setBackgroundResource(0);
                tv_portfolio.setBackgroundResource(0);
                tv_profile.setBackgroundResource(0);
            }
        });

        tv_projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new BrowseProjectsFragment(), true);
                tv_projects.setBackground(getResources().getDrawable(R.drawable.main_shape));
                tv_msgs.setBackgroundResource(0);
                tv_home.setBackgroundResource(0);
                tv_portfolio.setBackgroundResource(0);
                tv_profile.setBackgroundResource(0);
            }
        });

        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(), true);
                tv_home.setBackground(getResources().getDrawable(R.drawable.main_shape));
                tv_msgs.setBackgroundResource(0);
                tv_projects.setBackgroundResource(0);
                tv_portfolio.setBackgroundResource(0);
                tv_profile.setBackgroundResource(0);

            }
        });

        tv_portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new AddNewWork2Fragment(), true);
                tv_portfolio.setBackground(getResources().getDrawable(R.drawable.main_shape));
                tv_msgs.setBackgroundResource(0);
                tv_projects.setBackgroundResource(0);
                tv_home.setBackgroundResource(0);
                tv_profile.setBackgroundResource(0);


            }
        });

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new AccountFragment(), true);
                tv_profile.setBackground(getResources().getDrawable(R.drawable.main_shape));
                tv_msgs.setBackgroundResource(0);
                tv_projects.setBackgroundResource(0);
                tv_home.setBackgroundResource(0);
                tv_portfolio.setBackgroundResource(0);

            }
        });

    }
}
