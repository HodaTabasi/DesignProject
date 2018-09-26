package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class AboutUsFragment extends Fragment {

    ImageView ic_back;
    TextView tv_about, tv_title;
    int send_Flag;

    public AboutUsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ic_back = view.findViewById(R.id.ic_back);
        tv_about = view.findViewById(R.id.about);
        tv_title = view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        String s_about = bundle.getString("about");
        String s_rights = bundle.getString("rights");
        String s_conditions = bundle.getString("conditions");

        if (s_about != null) {
            tv_title.setText("نبذة عن صمم");
            send_Flag = 0;
        } else if (s_rights != null) {
            tv_title.setText("ضمان حقوقك");
            send_Flag = 1;
        } else if (s_conditions != null) {
            tv_title.setText("شروط الاستخدام");
            send_Flag = 2;

        }
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        getRequest(send_Flag);
    }

    private void getRequest(final int flag) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/sets", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());

                if (flag == 0) {
                    final JSONObject aboutObject = jsonObject.getJSONObject("about");
                    final String s = aboutObject.getString("value");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_about.setText(Html.fromHtml(Html.fromHtml(s).toString()));
                        }
                    });

                } else if (flag == 1) {
                    final JSONObject aboutObject = jsonObject.getJSONObject("rights");
                    final String s = aboutObject.getString("value");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_about.setText(Html.fromHtml(s));
                        }
                    });
                } else if (flag == 2) {
                    final JSONObject aboutObject = jsonObject.getJSONObject("policy");
                    final String s = aboutObject.getString("value");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_about.setText(Html.fromHtml(Html.fromHtml(s).toString()));
                        }
                    });
                }

            }
        });
    }
}
