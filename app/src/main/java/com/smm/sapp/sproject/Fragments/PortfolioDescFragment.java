package com.smm.sapp.sproject.Fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
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
import com.smm.sapp.sproject.Models.PortfolioModel;
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

public class PortfolioDescFragment extends Fragment {

    ImageView ic_back;
    TextView tv_name, tv_like, tv_shows, tv_calender, tv_descr, tv_ilike_it, tv_link, tv_addSimlilarWork, tv_add_to_fav;
    ImageView img1, img2, img3;
    Bundle bundle;
    PortfolioModel models;
    PWorks pWorks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_portfolio_desc, container, false);

        ic_back = view.findViewById(R.id.ic_back);
        tv_name = view.findViewById(R.id.tv1);
        tv_like = view.findViewById(R.id.tv_like);
        tv_shows = view.findViewById(R.id.tv_shows);
        tv_calender = view.findViewById(R.id.tv_calender);
        tv_descr = view.findViewById(R.id.tv2);
        tv_ilike_it = view.findViewById(R.id.tv_ilike_it);
        tv_link = view.findViewById(R.id.tv_link);
        tv_addSimlilarWork = view.findViewById(R.id.tv_addSimlilarWork);
        tv_add_to_fav = view.findViewById(R.id.tv_add_to_fav);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);

        if (ConstantInterFace.USER.getType().equals("worker"))
            tv_addSimlilarWork.setVisibility(View.GONE);
        else
            tv_addSimlilarWork.setVisibility(View.VISIBLE);


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

        getData();
    }

    private void getData() {
        bundle = getArguments();

        if (bundle.getBoolean("flag")) {
            pWorks = bundle.getParcelable("pworklist");
            setPworksData(pWorks);
        } else {
            models = bundle.getParcelable("pwork");
            setPortfolioData(models);
        }


    }

    private void setPortfolioData(final PortfolioModel models) {
        tv_name.setText(models.getName());
        tv_like.setText(String.valueOf(models.getLikes()));
        tv_shows.setText(models.getViews());
        tv_calender.setText(models.getMdate());
        tv_descr.setText(models.getDescr());
        Picasso.get().load(models.getPhoto_link()).into(img1);
        Picasso.get().load(models.getPhoto_link()).into(img2);
        Picasso.get().load(models.getPhoto_link()).into(img3);

        tv_ilike_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", models.getWork_link());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "The link copied to clipboard", Toast.LENGTH_LONG).show();
            }
        });
        tv_addSimlilarWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantInterFace.USER.getType().equals("worker")) {
                    AddNewWork2Fragment fragment = new AddNewWork2Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", models.getId() + "");
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                } else {
                    AddProjectFragment fragment = new AddProjectFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", (String) models.getType());
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                }

            }
        });
        tv_add_to_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFav(models, null);
                Log.e("ppppp", String.valueOf(models.getId()));
            }
        });

        tv_ilike_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void setPworksData(final PWorks models) {
        tv_name.setText(models.getName());
        tv_like.setText(String.valueOf(models.getLikes()));
        tv_shows.setText(models.getViews());
        tv_calender.setText(models.getMdate());
        tv_descr.setText(models.getDescr());
        Picasso.get().load(models.getPhoto_link()).into(img1);
        Picasso.get().load(models.getPhoto_link()).into(img2);
        Picasso.get().load(models.getPhoto_link()).into(img3);

        tv_ilike_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", models.getWork_link());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "The link copied to clipboard", Toast.LENGTH_LONG).show();
            }
        });
        tv_addSimlilarWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantInterFace.USER.getType().equals("worker")) {
                    AddNewWork2Fragment fragment = new AddNewWork2Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("flag", models.getId() + "");
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                } else {
                    AddProjectFragment fragment = new AddProjectFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", (String) models.getType());
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                }

            }
        });
        tv_add_to_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToFav(null, models);
            }
        });

        tv_ilike_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void addToFav(PortfolioModel portfolioModel, PWorks pWorks) {
        MyProgressDialog.showDialog(getContext());
        if (portfolioModel != null) {
            MyRequest myRequest = new MyRequest();
            Map<String, String> map = new HashMap<>();
            map.put("token", ConstantInterFace.USER.getToken());
            map.put("target_id", String.valueOf(portfolioModel.getId()));
            map.put("target_type", "pwork");
            myRequest.PostCall("http://smm.smmim.com/waell/public/api/likedislike", map, new OkHttpCallback() {
                @Override
                public void onFailure(Call call, final IOException e) {
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
                    final JSONObject object = new JSONObject(response.body().string());
                    JSONObject statusObj = object.getJSONObject("status");
                    final String success = statusObj.getString("success");
                    final String message = statusObj.getString("message");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (success.equals("true") && message.equals("like Returned")) {
                                Toast.makeText(getContext(), "تمت الاضافة للمفضلة", Toast.LENGTH_LONG).show();

                            } else if (success.equals("true") && message.equals("dislike Returned")) {

                                Toast.makeText(getContext(), "تم الحذف من المفضلة", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            });
        } else if (pWorks != null) {
            MyRequest myRequest = new MyRequest();
            Map<String, String> map = new HashMap<>();
            map.put("token", ConstantInterFace.USER.getToken());
            map.put("target_id", String.valueOf(pWorks.getId()));
            map.put("target_type", "pwork");
            myRequest.PostCall("http://smm.smmim.com/waell/public/api/likedislike", map, new OkHttpCallback() {
                @Override
                public void onFailure(Call call, final IOException e) {
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
                    final JSONObject object = new JSONObject(response.body().string());
                    JSONObject statusObj = object.getJSONObject("status");
                    final String success = statusObj.getString("success");
                    final String message = statusObj.getString("message");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (success.equals("true") && message.equals("like Returned")) {
                                Toast.makeText(getContext(), "تمت الاضافة للمفضلة", Toast.LENGTH_LONG).show();

                            } else if (success.equals("true") && message.equals("dislike Returned")) {

                                Toast.makeText(getContext(), "تم الحذف من المفضلة", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
            });
        }

    }
}
