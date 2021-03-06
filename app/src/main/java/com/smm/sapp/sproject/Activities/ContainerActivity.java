package com.smm.sapp.sproject.Activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AccountFragment;
import com.smm.sapp.sproject.Fragments.BrowseProjectsFragment;
import com.smm.sapp.sproject.Fragments.EditProposalFragment;
import com.smm.sapp.sproject.Fragments.MainFragment;
import com.smm.sapp.sproject.Fragments.MessageDitailsFragment;
import com.smm.sapp.sproject.Fragments.MyProjectFragment;
import com.smm.sapp.sproject.Fragments.NotificationFragment;
import com.smm.sapp.sproject.Fragments.PortfolioFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Fragments.MyMessageFragment;
import com.smm.sapp.sproject.Models.NotificationPayLoad;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class ContainerActivity extends AppCompatActivity {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Bundle bundle = new Bundle();
         if (getIntent().getBooleanExtra("notifiy",false)){
             switch (getIntent().getIntExtra("type",0)){
                 case 1:
                     NotificationPayLoad payLoad = getIntent().getParcelableExtra("messagePayload");
                     bundle = new Bundle();
                     MessageDitailsFragment fragment = new MessageDitailsFragment();
                     bundle.putString("userId",payLoad.getSender_id());
                     fragment.setArguments(bundle);
                     FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, fragment, false);
                     break;
                 case 2:
                     OfferModel model = getIntent().getParcelableExtra("messagePayload");
                     bundle = new Bundle();
                     bundle.putParcelable("object",model);
                     EditProposalFragment editProposalFragment = new EditProposalFragment();
                     editProposalFragment.setArguments(bundle);
                     FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, editProposalFragment, false);
                     break;
                 case 3:
                     FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MyProjectFragment(), false);
                     break;
                 case 4:
                     FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MyProjectFragment(), false);
                     break;
                 case 5:
                     FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(), false);
                     String name = getIntent().getStringExtra("name");
                     String id = getIntent().getStringExtra("id");
                     String type = getIntent().getStringExtra("ptype");
                     String reason = getIntent().getStringExtra("reason");
                     showRejectProjectDialog(name,id,type,reason);
                     break;
                 default:
                     FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new NotificationFragment(), true);
                     break;
             }

         }else {
             FragmentsUtil.addFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(), false);
         }

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "JFFlatregular.ttf", true);

        setBottomBar();


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

    private void showRejectProjectDialog(String name, String id, String type, String reason) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.done_dialog);
        TextView tv = dialog.findViewById(R.id.tv);
        TextView tv1 = dialog.findViewById(R.id.tv1);
        ImageButton send_bank11 = dialog.findViewById(R.id.send_bank11);

        tv.setText( "تم رفض مشروع  " + name);
        tv1.setText(" السبب: "+reason);
        send_bank11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                FragmentsUtil.replaceFragment(ContainerActivity.this, R.id.container_activity, new MyProjectFragment(), false);
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void setBottomBar() {
        ConstantInterFace.tv_msgs = findViewById(R.id.tv_msgs);
        ConstantInterFace.tv_projects = findViewById(R.id.tv_projects);
        ConstantInterFace.tv_home = findViewById(R.id.tv_home);
        ConstantInterFace.tv_portfolio = findViewById(R.id.tv_portfolio);
        ConstantInterFace.tv_profile = findViewById(R.id.tv_profile);
    }

}
