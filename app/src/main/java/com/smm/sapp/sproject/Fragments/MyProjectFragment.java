package com.smm.sapp.sproject.Fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.ClientProjectAdapter;
import com.smm.sapp.sproject.Adapters.MyProjectsProposalsAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.MyProjectsProposals;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProjectFragment extends Fragment implements View.OnClickListener {


    private Switch mSwitchOffer;
    /**
     * المستبعدة
     */
    private TextView mMyProjectExcluded;
    /**
     * قيد التنفيذ
     */
    private TextView mMyProjectUnderway;
    /**
     * المكتملة
     */
    private TextView mMyProjectDone;
    private RecyclerView mMyProjectRes;
    private LinearLayout mOne;
    private CircleImageView mProfileImage;
    private LinearLayout mLWaitProject;
    private LinearLayout mLUnderwayProject;
    private LinearLayout mLExcludedProject;
    private LinearLayout mLDoneProject;
    private LinearLayout mTwo;

    ImageView ic_back;

    LinearLayoutManager layoutManager;
    List<ProjectsModels> arrayList;
    List<ProjectsModels> arrayList1;
    List<ProjectsModels> arrayList2;
    int done = 0, wait = 0, under = 0;
    TextView doneTV, waitTV, underTV;
    PieChart chart;

    public MyProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_project, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        onClickMethod();

        getProjects("myprojects?token=" + ConstantInterFace.USER.getToken());

    }


    private void initView() {
        mSwitchOffer = getView().findViewById(R.id.switch_offer);
        mMyProjectExcluded = getView().findViewById(R.id.my_project_excluded);
        mMyProjectUnderway = getView().findViewById(R.id.my_project_underway);
        mMyProjectDone = getView().findViewById(R.id.my_project_done);
        mOne = getView().findViewById(R.id.one);
//        mProfileImage = getView().findViewById(R.id.profile_image);
        mLWaitProject = getView().findViewById(R.id.l_wait_project);
        mLUnderwayProject = getView().findViewById(R.id.l_underway_project);
        mLExcludedProject = getView().findViewById(R.id.l_excluded_project);
        mLDoneProject = getView().findViewById(R.id.l_done_project);
        mTwo = getView().findViewById(R.id.two);
        ic_back = getView().findViewById(R.id.ic_back);
        doneTV = getView().findViewById(R.id.done);
        underTV = getView().findViewById(R.id.under);
        waitTV = getView().findViewById(R.id.wait);
        chart = (PieChart) getView().findViewById(R.id.chart);

        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();

        mMyProjectRes = getView().findViewById(R.id.my_project_res);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mMyProjectRes.setLayoutManager(layoutManager);

        mMyProjectDone.setOnClickListener(this);
        mMyProjectExcluded.setOnClickListener(this);
        mMyProjectUnderway.setOnClickListener(this);

        setBottomBar();
        addToChart();
    }

    private void setBottomBar() {
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
    }

    private void onClickMethod() {
        mSwitchOffer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mOne.setVisibility(View.VISIBLE);
                    mTwo.setVisibility(View.GONE);
                } else {
                    mOne.setVisibility(View.GONE);
                    mTwo.setVisibility(View.VISIBLE);
                }
            }
        });
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void getProjects(final String url) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/" + url, new OkHttpCallback() {
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
                                JSONArray jsonArray = object.getJSONArray("projects");
                                Gson gson = new Gson();
                                TypeToken<List<ProjectsModels>> token = new TypeToken<List<ProjectsModels>>() {
                                };
//                                arrayList = gson.fromJson(object.getJSONArray("projects").toString(), token.getType());
                                for (int i = 0; i <= jsonArray.length(); i++) {
                                    JSONObject object2 = jsonArray.getJSONObject(i);
                                    ProjectsModels models = gson.fromJson(object2.toString(), ProjectsModels.class);
                                    switch (object2.getString("status")) {
                                        case "0":
                                            //قيد الموافقة
                                            arrayList.add(models);
                                            waitTV.setText(arrayList.size() + " عرض ");
                                            wait++;
                                            break;
                                        case "1":
                                            //قيد العمل
                                            arrayList1.add(models);
                                            underTV.setText(arrayList1.size() + " عرض ");
                                            under++;
                                            break;
                                        case "2":
                                            //قم التسليم
                                            arrayList2.add(models);
                                            doneTV.setText(arrayList2.size() + " عرض ");
                                            done++;
                                            break;
                                    }
                                }

                                mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList2));
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

    private void addToChart() {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f));
        entries.add(new PieEntry(26.7f));
        entries.add(new PieEntry(24.0f));
        entries.add(new PieEntry(30.8f));

        PieDataSet set = new PieDataSet(entries, "  ");
        set.setColors(new int[]{R.color.yalow, R.color.green, R.color.darkBlue, R.color.red}, getActivity());
        set.setValueTextSize(15f);
        set.setValueTextColor(Color.WHITE);
        set.setDrawIcons(false);

        set.setSliceSpace(3f);
        set.setIconsOffset(new MPPointF(0, 40));
        set.setSelectionShift(5f);
        set.setDrawIcons(false);


        PieData data = new PieData(set);
        chart.setData(data);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setRotationEnabled(false);
        chart.setHighlightPerTapEnabled(false);
        chart.setDrawCenterText(true);
//        chart.setCenterTextTypeface(mTfLight);
        SpannableString s = new SpannableString("150");
        chart.setCenterText(s);
        chart.invalidate(); // refresh



    }

//    private SpannableString generateCenterSpannableText() {
//
//        SpannableString s = new SpannableString("150");
//        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
//        return s;
//    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.my_project_excluded:
                mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList));
                break;
            case R.id.my_project_done:
                mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList2));
                break;
            case R.id.my_project_underway:
                mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList1));
                break;
        }
    }
}
