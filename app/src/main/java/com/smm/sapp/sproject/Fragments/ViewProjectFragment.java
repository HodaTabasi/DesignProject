package com.smm.sapp.sproject.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.obsez.android.lib.filechooser.ChooserDialog;
import com.smm.sapp.sproject.Adapters.ProjectAttachmentAdapter;
import com.smm.sapp.sproject.Adapters.ProjectPhotoAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class ViewProjectFragment extends Fragment {

    private TextView mUserName;
    private TextView mUserType;
    private CircleImageView mUserPhoto;
    private EditText mPName;
    private EditText mPType;
    private EditText mPStyle;
    private EditText mPColors;
    private EditText mPCity;
    private RecyclerView mPDesignYouLike;
    private RecyclerView mPProjectPhoto;
    private EditText mPArea;
    private EditText mPBalance;
    private EditText mPBio;
    private RecyclerView mPAttachment;
    private LinearLayout linear_add_proposal, pr_ditails;
    Bundle bundle;
    private TextView addOffer;
    ProjectsModels models;
    ImageView ic_back;

    private TextView mReceivableP;
    private EditText mBalanceP;
    private EditText mDurP;
    private EditText mProposalP;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mAttchP;
    private TextView mAddProposalP;
    private String filePath;
    private byte[] b;
    int id;
    //    ImageView ic_back;
    TextView back_two;
    OfferModel model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_project, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        initView(getView());
        addOnClickListener();

        if (ConstantInterFace.USER.getType().equals("client"))
            addOffer.setVisibility(View.INVISIBLE);
        else
            addOffer.setVisibility(View.VISIBLE);

        //لفحص هل هو تعديل ولا جاي من الفيو
        bundle = getArguments();
        if (bundle.getBoolean("flag")) {
            model = bundle.getParcelable("object");
            mBalanceP.setText(model.getBalance());
            mProposalP.setText(model.getDescr());
            mDurP.setText(model.getDur());
            mUserName.setText(model.getUser().getName());
            mUserType.setText(model.getUser().getType());
            Picasso.get().load(model.getUser().getPhoto_link()).into(mUserPhoto);
            linear_add_proposal.setVisibility(View.VISIBLE);
            pr_ditails.setVisibility(View.GONE);
        } else {
            putData();
        }

        addOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ConstantInterFace.IS_USER_COMPLETEED) {
                    if (!ConstantInterFace.IS_PROPOSAL_OPENED) {
                        addOffer.setBackgroundResource(R.drawable.dark_blue_shap);
                        addOffer.setTextColor(getResources().getColor(R.color.white));
                        linear_add_proposal.setVisibility(View.VISIBLE);
                        ConstantInterFace.IS_PROPOSAL_OPENED = true;
//                    pr_ditails.setVisibility(View.GONE);
                        id = models.getId();


                    } else if (ConstantInterFace.IS_PROPOSAL_OPENED) {
                        addOffer.setBackgroundResource(R.drawable.report_layout_shap);
                        addOffer.setTextColor(getResources().getColor(R.color.blue));
                        linear_add_proposal.setVisibility(View.GONE);
                        pr_ditails.setVisibility(View.VISIBLE);
                        ConstantInterFace.IS_PROPOSAL_OPENED = false;
                    }
                } else if (!ConstantInterFace.IS_USER_COMPLETEED) {
                    Snackbar snackbar = Snackbar.make(getView(), "يرجى تعبئة بياناتك الشخصية", Snackbar.LENGTH_LONG);
                    snackbar.show();

                    TextView tv = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextSize(12f);
                    Typeface font = Typeface.createFromAsset(getContext().getAssets(), "JFFlatregular.ttf");
                    tv.setTypeface(font);
                }

//                AddProposalFragment fragment = new AddProposalFragment();
//                Bundle bundle1 = new Bundle();
//                bundle1.putInt("id", models.getId());
//                Log.e("from", models.getId() + " ");
//                fragment.setArguments(bundle1);
//                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);


            }
        });

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

    private void addOnClickListener() {
        mBalanceP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    double b = Double.parseDouble(mBalanceP.getText().toString());
                    double total = b * 0.95;
                    mReceivableP.setText(total + " ");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAttchP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileBrowse();
            }
        });

        mAddProposalP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ffd", id + "");
                if (bundle.getBoolean("flag")) {
                    Log.e("ffd", id + " gg");
                    updateOfferRequest(model);
                } else {
                    if (mReceivableP.getText().toString().matches("")
                            || mBalanceP.getText().toString().matches("")
                            || mDurP.getText().toString().matches("")
                            || mProposalP.getText().toString().matches("")) {
                        Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                    } else {
                        addOfferRequest();
                    }

                }

            }
        });
    }

    private void initView(View view) {
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserType = (TextView) view.findViewById(R.id.user_type);
        mUserPhoto = (CircleImageView) view.findViewById(R.id.user_photo);
        mPName = (EditText) view.findViewById(R.id.p_name);
        mPType = (EditText) view.findViewById(R.id.p_type);
        mPStyle = (EditText) view.findViewById(R.id.p_style);
        mPColors = (EditText) view.findViewById(R.id.p_colors);
        mPCity = (EditText) view.findViewById(R.id.p_city);
        mPDesignYouLike = (RecyclerView) view.findViewById(R.id.p_design_you_like);
        mPProjectPhoto = (RecyclerView) view.findViewById(R.id.p_project_photo);
        mPArea = (EditText) view.findViewById(R.id.p_area);
        mPBalance = (EditText) view.findViewById(R.id.p_balance);
        mPBio = (EditText) view.findViewById(R.id.p_bio);
        mPAttachment = (RecyclerView) view.findViewById(R.id.p_attachment);
        addOffer = view.findViewById(R.id.add_offer);
        linear_add_proposal = view.findViewById(R.id.linear_add_proposal);
        pr_ditails = view.findViewById(R.id.pr_ditails);

        mReceivableP = (TextView) view.findViewById(R.id.receivable_p);
        mBalanceP = (EditText) view.findViewById(R.id.balance_p);
        mDurP = (EditText) view.findViewById(R.id.dur_p);
        mProposalP = (EditText) view.findViewById(R.id.proposal_p);
        mTv1 = (TextView) view.findViewById(R.id.tv1);
        mTv2 = (TextView) view.findViewById(R.id.tv2);
        mAttchP = (TextView) view.findViewById(R.id.attch_p);
        mAddProposalP = (TextView) view.findViewById(R.id.add_proposal_p);
        ic_back = getView().findViewById(R.id.ic_back);
        back_two = view.findViewById(R.id.back_two);

    }

    private void putData() {
        models = bundle.getParcelable("theProject");
        mUserName.setText(models.getUser().getName());
        mUserType.setText(models.getUser().getType());
        Picasso.get().load(models.getUser().getPhoto_link()).into(mUserPhoto);
        mPName.setText(models.getName());

        if (models.getType().equals("inter")) {
            mPType.setText("تصميم داخلي");
        } else if (models.getType().equals("arch")) {
            mPType.setText("تصميم معماري");
        } else if (models.getType().equals("moshen")) {
            mPType.setText("تصميم موشن");
        } else if (models.getType().equals("graphic")) {
            mPType.setText("تصميم جرافيكس");
        } else if (models.getType().equals("wall")) {
            mPType.setText("تصميم جداري");
        }


        if (models.getAddtion_info() != null) {
            mPStyle.setText(models.getAddtion_info().getStyle());
            mPColors.setText(models.getAddtion_info().getColors());
            mPCity.setText(models.getAddtion_info().getCity());
            mPArea.setText(models.getAddtion_info().getArea());
        }
        mPBalance.setText(ConstantInterFace.array[Integer.parseInt(models.getBalance())]);
        mPBio.setText(models.getDescr());

        mPProjectPhoto.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ProjectPhotoAdapter adapter = new ProjectPhotoAdapter(getContext(), R.layout.layout_item_photos, models.getPhotos());
        mPProjectPhoto.setAdapter(adapter);

//        mPProjectPhoto.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        ProjectPhotoAdapter adapter1 = new ProjectPhotoAdapter(getContext(),R.layout.layout_item_photos,models.getSimilars());
//        mPProjectPhoto.setAdapter(adapter);

        mPAttachment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ProjectAttachmentAdapter adapter2 = new ProjectAttachmentAdapter(getContext(), R.layout.layout_item_attachment, models.getAttachs());
        mPAttachment.setAdapter(adapter2);
// عرض الاوفر للمستخدم لو كان مقدم
        if (models.getOffers().size() != 0){
            for(OfferModel s: models.getOffers()){
                if(s.getCreated_by().equals(ConstantInterFace.USER.getId() +"")){
                    linear_add_proposal.setVisibility(View.VISIBLE);
                    mBalanceP.setText(s.getBalance());
                    mProposalP.setText(s.getDescr());
                    mDurP.setText(s.getDur());
                    addOffer.setVisibility(View.INVISIBLE);
                    break;
                }
            }
        }
    }

    private void updateOfferRequest(OfferModel model) {
        Log.e("ffd", id + " gg");
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("offer_id", model.getId() + "");
        stringMap.put("dur", mDurP.getText().toString());
        stringMap.put("balance", mBalanceP.getText().toString());
        stringMap.put("descr", mProposalP.getText().toString());
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/editanoffer", stringMap, filePath, "file_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Gson gson = new Gson();
                                OfferModel model = gson.fromJson(jsonObject.getJSONObject("offer").toString(), OfferModel.class);
                                Toast.makeText(getActivity(), "م تعديل العرض بنجاح" , Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "" + object.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }

    private void addOfferRequest() {
        Log.e("ffd", id + " gg");
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("project_id", id + "");
        stringMap.put("dur", mDurP.getText().toString());
        stringMap.put("balance", mBalanceP.getText().toString());
        stringMap.put("descr", mProposalP.getText().toString());

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/makeanoffer", stringMap, filePath, "file_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Toast.makeText(getContext(), "تم اضافة العرض بنجاح", Toast.LENGTH_SHORT).show();
                                //                    Gson gson = new Gson();
                                //                    OfferModel model = gson.fromJson(jsonObject.getJSONObject("offer").toString(),OfferModel.class);
                                //                    getFragmentManager().popBackStack();
                                //                    Log.e("ff",model.getBalance());
                                addOffer.setBackgroundResource(R.drawable.report_layout_shap);
                                addOffer.setTextColor(getResources().getColor(R.color.blue));
                                linear_add_proposal.setVisibility(View.GONE);
                                pr_ditails.setVisibility(View.VISIBLE);
                                ConstantInterFace.IS_PROPOSAL_OPENED = false;
                            } else {
                                Log.e("uuuu", object.getString("error"));
                                if (object.getString("error").equals("you already made an offer , just edit")) {
                                    Toast.makeText(getContext(), "لقد قمت باضافة عرض، يمكنك تعديل العرض فقط", Toast.LENGTH_SHORT).show();

                                }
                                //Toast.makeText(getContext(), "" + object.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void fileBrowse() {
        new ChooserDialog().with(getContext())
                .withFilter(false, false, "pdf", "docx", "xlsx")
                .withStartFile(Environment.getExternalStorageDirectory().getPath())
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        filePath = path;
                        // mAttchP.setText(path);
                        Toast.makeText(getContext(), "تم ارفاق الملف", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .show();
    }

}
