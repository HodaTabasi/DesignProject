package com.example.maryam.sproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.maryam.sproject.Fragments.AccountFragment;
import com.example.maryam.sproject.Fragments.AddNewWork2Fragment;
import com.example.maryam.sproject.Fragments.BrowseProjectsFragment;
import com.example.maryam.sproject.Fragments.MainFragment;
import com.example.maryam.sproject.Fragments.NotificationFragment;
import com.example.maryam.sproject.HelperClass.FragmentsUtil;
import com.example.maryam.sproject.R;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FragmentsUtil.addFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(),false);

        TextView tv_msgs = findViewById(R.id.tv_msgs);
        TextView tv_projects = findViewById(R.id.tv_projects);
        TextView tv_home = findViewById(R.id.tv_home);
        TextView tv_portfolio = findViewById(R.id.tv_portfolio);
        TextView tv_profile = findViewById(R.id.tv_profile);


        tv_msgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new NotificationFragment(), true);

            }
        });

        tv_projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new BrowseProjectsFragment(), true);

            }
        });

        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(), true);

            }
        });

        tv_portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new AddNewWork2Fragment(), true);

            }
        });

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new AccountFragment(), true);

            }
        });

    }
}
