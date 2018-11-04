package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.WorkerOfferAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOffersFragment extends Fragment implements View.OnClickListener {


    private TextView mAllOfferExcluded;
    private TextView mAllOfferDone;
    private TextView mAllOfferUnderway;
    private TextView mAllOfferWait;
    private TextView titles;
    private RecyclerView mAllOfferRes;
    ImageView ic_back;

    LinearLayoutManager layoutManager;
    List<OfferModel> offerModels;
    List<OfferModel> offerModels1;
    List<OfferModel> offerModels2;
    List<OfferModel> offerModels3;

    List<OfferModel> arrayList;


    String name = "";

    public MyOffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_offers, container, false);
    }

    private void initView() {
        mAllOfferExcluded = getView().findViewById(R.id.all_offer_excluded);
        mAllOfferDone = getView().findViewById(R.id.all_offer_done);
        mAllOfferUnderway = getView().findViewById(R.id.all_offer_underway);
        mAllOfferWait = getView().findViewById(R.id.all_offer_wait);
        titles = getView().findViewById(R.id.titles);
        mAllOfferRes = getView().findViewById(R.id.all_offer_res);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAllOfferRes.setLayoutManager(layoutManager);
        ic_back = getView().findViewById(R.id.ic_back);

        offerModels = new ArrayList<>();
        offerModels1 = new ArrayList<>();
        offerModels2 = new ArrayList<>();
        offerModels3 = new ArrayList<>();

        mAllOfferDone.setOnClickListener(this);
        mAllOfferExcluded.setOnClickListener(this);
        mAllOfferUnderway.setOnClickListener(this);
        mAllOfferWait.setOnClickListener(this);
        ic_back.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        setBottomBar();

        Bundle bundle = getArguments();
        if (bundle.getBoolean("isUpdated")) {
            getWorkerOffersRequest();
        } else if (bundle.getBoolean("flag")) {
            mAllOfferDone.setClickable(false);
            mAllOfferExcluded.setClickable(false);
            mAllOfferUnderway.setClickable(false);
            mAllOfferWait.setClickable(false);
            switch (bundle.getInt("key")) {
                case 1:
                    titles.setText(" العرووض في انتظار الموافقة ");
                    mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), bundle.<OfferModel>getParcelableArrayList("array"), 1));
                    break;
                case 2:
                    titles.setText(" العرووض المستبعدة ");
                    mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), bundle.<OfferModel>getParcelableArrayList("array"), 2));
                    break;
                case 3:
                    titles.setText(" العرووض المكتملة ");
                    mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), bundle.<OfferModel>getParcelableArrayList("array"), 3));
                    break;
                case 4:
                    titles.setText(" العرووض قيد التنفيذ ");
                    mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), bundle.<OfferModel>getParcelableArrayList("array"), 4));
                    break;
            }

        } else {
            ProjectsModels projectsModels = bundle.getParcelable("object");
            name = projectsModels.getUser().getName();
            swichOffers(projectsModels.getOffers());
        }
    }

    private void setBottomBar() {
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
    }

    private void getWorkerOffersRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/myworkedprojects?token=" + ConstantInterFace.USER.getToken(), new OkHttpCallback() {
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
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                TypeToken<List<OfferModel>> token = new TypeToken<List<OfferModel>>() {
                                };
                                arrayList = gson.fromJson(object.getJSONArray("my_offers").toString(), token.getType());
                                swichOffers(arrayList);

                            } else {
                                Toast.makeText(getContext(), "" + object1.getBoolean("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void swichOffers(List<OfferModel> offers) {
        for (OfferModel model : offers) {
            if (model.getApproved().equals("0") && model.getFinished().equals("0")) {
                //انضار الموافقة
                offerModels.add(model);
            } else if (model.getApproved().equals("1") && model.getFinished().equals("0")) {
                //قيد العمل
                offerModels1.add(model);
            } else if (model.getApproved().equals("1") && model.getFinished().equals("1")) {
                //منتهي
                offerModels3.add(model);
            } else if (model.getApproved().equals("2")) {
                //مستبعد
                offerModels2.add(model);
            }
        }
        mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), offerModels, 1));
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.all_offer_excluded:
                mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), offerModels2, 2));
                break;
            case R.id.all_offer_done:
                mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), offerModels3, 3));
                break;
            case R.id.all_offer_underway:
                mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), offerModels1, 4));
                break;
            case R.id.all_offer_wait:
                mAllOfferRes.setAdapter(new WorkerOfferAdapter(getContext(), offerModels, 1));
                break;
            case R.id.ic_back:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
