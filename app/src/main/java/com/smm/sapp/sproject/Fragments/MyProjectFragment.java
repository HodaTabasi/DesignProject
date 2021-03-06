package com.smm.sapp.sproject.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
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
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.ClientProjectAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
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

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProjectFragment extends Fragment implements View.OnClickListener {


    private Switch mSwitchOffer;
    private TextView mMyProjectExcluded;
    private TextView mMyProjectUnderway;
    private TextView mMyProjectDone;
    private RecyclerView mMyProjectRes;
    private LinearLayout mOne;
    private CircleImageView mProfileImage;
    private LinearLayout mLWaitProject;
    private LinearLayout mLUnderwayProject;
    private LinearLayout mLExcludedProject;
    private LinearLayout mLDoneProject;
    private LinearLayout mTwo;
    ClientProjectAdapter adapter;

    ImageView ic_back;
    List<ProjectsModels> projects;
    LinearLayoutManager layoutManager;
    List<ProjectsModels> arrayList;
    List<ProjectsModels> arrayList1;
    List<ProjectsModels> arrayList2;
    ArrayList<OfferModel> underList, doneList, waitList, excludedList;
    int done = 0, wait = 0, under = 0, excluded = 0;
    TextView doneTV, waitTV, underTV, excludedTV;
    PieChart chart;
    private int total = 0;
    int current_page, total_pages, flag;
    private TextView tv_next, tv_back, tv_projects;

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
        getProjects("myprojects?token=" + ConstantInterFace.USER.getToken(), 1);
        //addToChart();
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
        excludedTV = getView().findViewById(R.id.go);
        chart = getView().findViewById(R.id.chart);
        tv_next = getView().findViewById(R.id.tv_next);
        tv_back = getView().findViewById(R.id.tv_back);
        tv_projects = getView().findViewById(R.id.tv_projects);

        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();

        //للعروض
        underList = new ArrayList<>();
        waitList = new ArrayList<>();
        doneList = new ArrayList<>();
        excludedList = new ArrayList<>();

        mMyProjectRes = getView().findViewById(R.id.my_project_res);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mMyProjectRes.setLayoutManager(layoutManager);

        mMyProjectDone.setOnClickListener(this);
        mMyProjectExcluded.setOnClickListener(this);
        mMyProjectUnderway.setOnClickListener(this);
        underTV.setOnClickListener(this);
        doneTV.setOnClickListener(this);
        waitTV.setOnClickListener(this);
        excludedTV.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        tv_next.setOnClickListener(this);

        done = 0;
        wait = 0;
        under = 0;
        excluded = 0;

        setBottomBar();

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

    private void getProjects(final String url, int current) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/" + url + "&i_current_page=" + current, new OkHttpCallback() {
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
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");
                final JSONObject paginationObj = object.getJSONObject("pagination");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                projects = gson.fromJson(object.getJSONArray("projects").toString(), new TypeToken<List<ProjectsModels>>() {
                                }.getType());
                                adapter = new ClientProjectAdapter(getContext(), projects);
                                mMyProjectRes.setAdapter(adapter);

                                current_page = Integer.valueOf(paginationObj.getString("i_current_page"));
                                total_pages = Integer.valueOf(paginationObj.getString("i_total_pages"));

                                if (total_pages > current_page && current_page != 1) {
                                    //two are visible
                                    tv_next.setVisibility(View.VISIBLE);
                                    tv_back.setVisibility(View.VISIBLE);
                                } else if (total_pages == current_page && current_page != 1) {
                                    //back visible, next gone
                                    tv_next.setVisibility(View.GONE);
                                    tv_back.setVisibility(View.VISIBLE);
                                } else if (total_pages > current_page && current_page == 1) {
                                    //next visible, back gone
                                    tv_next.setVisibility(View.VISIBLE);
                                    tv_back.setVisibility(View.GONE);
                                } else if (total_pages == 1 || total_pages == 0) {
                                    //two are gone
                                    tv_next.setVisibility(View.GONE);
                                    tv_back.setVisibility(View.GONE);
                                }

//                                for (int i = 0; i <= jsonArray.length(); i++) {
//                                    JSONObject object2 = jsonArray.getJSONObject(i);
//                                    total += object2.length();
//                                    ProjectsModels models = gson.fromJson(object2.toString(), ProjectsModels.class);
//                                    if (object2.getString("accepted").equals("0")) {
////                                        قيد الموافقة
//                                        arrayList.add(models);
//                                    } else {
//                                        switch (object2.getString("status")) {
//                                            case "1":
//                                            case "0":
////                                                قيد العمل
//                                                arrayList1.add(models);
//                                                break;
//                                            case "2":
//                                                //DONE
//                                                arrayList2.add(models);
//                                                break;
//                                        }
//                                    }

//                                    for (OfferModel offerModel : models.getOffers()) {
//                                        if (offerModel.getApproved().equals("0") && offerModel.getFinished().equals("0")) {
//                                            wait++;
//                                            waitTV.setText(wait + " عرض ");
//                                            waitList.add(offerModel);
//                                        } else if (offerModel.getApproved().equals("1") && offerModel.getFinished().equals("0")) {
//                                            under++;
//                                            underTV.setText(under + " عرض ");
//                                            underList.add(offerModel);
//                                        } else if (offerModel.getApproved().equals("1") && offerModel.getFinished().equals("1")) {
//                                            done++;
//                                            doneTV.setText(done + " عرض ");
//                                            doneList.add(offerModel);
//                                        } else {
//                                            excluded++;
//                                            excludedTV.setText(excluded + " عرض ");
//                                            excludedList.add(offerModel);
//                                        }
//                                    }
//                                }

//                                wait = (wait/total) *100;
//                                under = (under/total) *100;
//                                done = (done/total) *100;
//                                excluded = (excluded/total) *100;

                            } else {
                                Toast.makeText(getContext(), "" + object1.getBoolean("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //addToChart();
//                        mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList2));
                    }
                });


            }

        });
    }

    private void addToChart() {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(wait));
        entries.add(new PieEntry(done));
        entries.add(new PieEntry(under));
        entries.add(new PieEntry(excluded));

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
        MyOffersFragment fragment = new MyOffersFragment();
        Bundle bundle = new Bundle();
        int id = v.getId();
        switch (id) {
            case R.id.my_project_excluded:
                flag = 1;
                tv_projects.setText("مشاريع قيد الموافقة");
                tv_projects.setBackgroundResource(R.drawable.yeallow_shap);
                tv_projects.setTextColor(Color.WHITE);
                getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=0", 1);
//                mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList));
                break;
            case R.id.my_project_done:
                flag = 2;
                tv_projects.setText("مشاريع مكتملة");
                tv_projects.setBackgroundResource(R.drawable.green_shap);
                tv_projects.setTextColor(Color.WHITE);
                getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=2", 1);
//                mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList2));
                break;
            case R.id.my_project_underway:
                flag = 3;
                tv_projects.setText("مشاريع قيد التنفيذ");
                tv_projects.setBackgroundResource(R.drawable.dark_blue_shap);
                tv_projects.setTextColor(Color.WHITE);
                getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=1", 1);
//                mMyProjectRes.setAdapter(new ClientProjectAdapter(getContext(), arrayList1));
                break;
            case R.id.wait:
                bundle.putParcelableArrayList("array", waitList);
                bundle.putBoolean("flag", true);
                bundle.putInt("key", 1);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                break;
            case R.id.go:
                bundle.putParcelableArrayList("array", excludedList);
                bundle.putBoolean("flag", true);
                bundle.putInt("key", 2);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                break;
            case R.id.done:
                bundle.putParcelableArrayList("array", doneList);
                bundle.putBoolean("flag", true);
                bundle.putInt("key", 3);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                break;
            case R.id.under:
                bundle.putParcelableArrayList("array", underList);
                bundle.putBoolean("flag", true);
                bundle.putInt("key", 4);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                break;

            case R.id.tv_next:
                setBottomBar();
                current_page++;
                if (flag == 1) {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=0", current_page);
                } else if (flag == 2) {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=2", current_page);
                } else if (flag == 3) {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=1", current_page);
                } else {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken(), current_page);
                }
                break;

            case R.id.tv_back:
                setBottomBar();
                current_page--;
                if (flag == 1) {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=0", current_page);
                } else if (flag == 2) {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=2", current_page);
                } else if (flag == 3) {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken() + "&status=1", current_page);
                } else {
                    getProjects("myprojects?token=" + ConstantInterFace.USER.getToken(), current_page);
                }
                break;
        }
    }
}
