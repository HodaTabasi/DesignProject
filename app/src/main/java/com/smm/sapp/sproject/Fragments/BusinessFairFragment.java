package com.smm.sapp.sproject.Fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.PWorks;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class BusinessFairFragment extends Fragment {
    ImageView ic_back, img1, img2, img3;
    TextView tv_name, tv_descr, num_date, num_seen, num_like, update_work, delete_work, project_link;
    PWorks models;
    Bundle bundle;

    int isUpdated = 0;

    public BusinessFairFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_fair, container, false);

        ic_back = view.findViewById(R.id.ic_back);
        tv_name = view.findViewById(R.id.tv_name);
        num_date = view.findViewById(R.id.num_date);
        num_seen = view.findViewById(R.id.num_seen);
        num_like = view.findViewById(R.id.num_like);
        update_work = view.findViewById(R.id.update_work);
        delete_work = view.findViewById(R.id.delete_work);
        tv_descr = view.findViewById(R.id.tv_descr);
        project_link = view.findViewById(R.id.project_link);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        delete_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRequest();
            }
        });
        update_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle = getArguments();
                models = bundle.getParcelable("work");

                AddNewWorkFragment fragment = new AddNewWorkFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("work", models);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, false);
            }
        });

        getData();
    }


    private void deleteRequest() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        bundle = getArguments();
        models = bundle.getParcelable("work");

        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("pwork_id", String.valueOf(models.getId()));

        myRequest.PostCall("http://smm.smmim.com/waell/public/api/deletepwork", map, new OkHttpCallback() {
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
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject statusobj = jsonObject.getJSONObject("status");
                final String success = statusobj.getString("success");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (success.equals("true")) {
                            getFragmentManager().popBackStack();
                            Toast.makeText(getContext(), "تم الحذف بنجاح", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "لم يتم الحذف", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
    }

    private void getData() {
        bundle = getArguments();
        models = bundle.getParcelable("work");
        tv_name.setText(models.getName());
        num_date.setText(models.getMdate());
        num_seen.setText(models.getViews());
        num_like.setText(models.getLikes());
        tv_descr.setText(models.getDescr());


        project_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", models.getWork_link());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "The link copied to clipboard", Toast.LENGTH_LONG).show();
            }
        });

        Picasso.get().load(models.getPhoto_link()).into(img1);
        Picasso.get().load(models.getPhoto_link()).into(img2);
        Picasso.get().load(models.getPhoto_link()).into(img3);

    }


}
