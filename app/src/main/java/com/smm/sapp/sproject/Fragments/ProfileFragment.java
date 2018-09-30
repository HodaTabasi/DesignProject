package com.smm.sapp.sproject.Fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class ProfileFragment extends Fragment {

    TextView tv_worker, tv_client, tv_company, tv_individual, tv_bio, tv_save, tv_dob1, tv_dob2, tv_dob3;
    EditText et_name, et_bio, et_mobile1, et_mobile2, et_email;
    LinearLayout linear2;
    Spinner sp_specialization, sp_gender;
    String type, busniess_type, myFormat, date_format, st_specialization, st_gender, st_mobile;
    SimpleDateFormat sdf;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    String st_type, st_job_type, st_busniess_type, st_name, st_bio, mobile, st_email, gender, st_dob;
    ImageView ic_back;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tv_worker = view.findViewById(R.id.tv_worker);
        tv_client = view.findViewById(R.id.tv_client);
        tv_company = view.findViewById(R.id.tv_company);
        tv_individual = view.findViewById(R.id.tv_individual);
        tv_save = view.findViewById(R.id.tv_save);
        sp_specialization = view.findViewById(R.id.sp_specialization);
        et_name = view.findViewById(R.id.et_name);
        et_bio = view.findViewById(R.id.et_bio);
        et_mobile1 = view.findViewById(R.id.et_mobile1);
        et_mobile2 = view.findViewById(R.id.et_mobile2);
        et_email = view.findViewById(R.id.et_email);
        sp_gender = view.findViewById(R.id.sp_gender);
        tv_dob1 = view.findViewById(R.id.tv_dob1);
        tv_dob2 = view.findViewById(R.id.tv_dob2);
        tv_dob3 = view.findViewById(R.id.tv_dob3);
        linear2 = view.findViewById(R.id.linear2);
        tv_bio = view.findViewById(R.id.tv_bio);

        ic_back = view.findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_specialization.setAdapter(adapter);
        sp_specialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        //st_specialization = "inter";
                        getSpecialization("inter");
                        break;
                    case 1:
                        getSpecialization("arch");
                        //st_specialization = "arch";
                        break;
                    case 2:
                        getSpecialization("graphic");
                        //st_specialization = "graphic";
                        break;
                    case 3:
                        getSpecialization("wall");
                        //st_specialization = "wall";
                        break;
                    case 4:
                        getSpecialization("moshen");
                        //st_specialization = "moshen";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> gender_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.gender_items, android.R.layout.simple_spinner_item);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(gender_adapter);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        getGender("male");
                        break;
                    case 1:
                        getGender("female");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        calendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                myFormat = "dd/MM/yy";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                date_format = sdf.format(calendar.getTime());
                //et_specialization.setText(sdf.format(calendar.getTime()));

                tv_dob1.setText(calendar.get(Calendar.YEAR) + "");
                tv_dob2.setText(calendar.get(Calendar.MONTH) + 1 + "");
                tv_dob3.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");

            }
        };

        tv_dob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tv_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_client.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_client.setTextColor(Color.WHITE);
                tv_worker.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_worker.setTextColor(getResources().getColor(R.color.textGray));
                sp_specialization.setVisibility(View.GONE);
                linear2.setVisibility(View.GONE);
                tv_bio.setVisibility(View.GONE);
                et_bio.setVisibility(View.GONE);

                type = "client";
            }
        });

        tv_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_worker.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_worker.setTextColor(Color.WHITE);
                tv_client.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_client.setTextColor(getResources().getColor(R.color.textGray));
                sp_specialization.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.VISIBLE);
                tv_bio.setVisibility(View.VISIBLE);
                et_bio.setVisibility(View.VISIBLE);

                type = "worker";

            }
        });

        tv_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_company.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_company.setTextColor(getResources().getColor(R.color.white));

                tv_individual.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_individual.setTextColor(getResources().getColor(R.color.textGray));

                busniess_type = "company";
            }
        });

        tv_individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_individual.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_individual.setTextColor(getResources().getColor(R.color.white));

                tv_company.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_company.setTextColor(getResources().getColor(R.color.textGray));

                busniess_type = "individual";
            }
        });


        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                st_mobile = et_mobile2.getText().toString() + et_mobile1.getText().toString();
                Log.e("ttt", type);
                MyRequest myRequest = new MyRequest();
                Map<String, String> stringMap = new HashMap<>();


                if (type.equals("worker")) {
                    Log.e("yes", "yes");
                    stringMap.put("token", ConstantInterFace.USER.getToken());
                    stringMap.put("name", et_name.getText().toString());
                    stringMap.put("email", et_email.getText().toString());
                    stringMap.put("gender", st_gender);
                    stringMap.put("job_type", st_specialization);
                    stringMap.put("bio", et_bio.getText().toString());
                    stringMap.put("dob", date_format);
                    stringMap.put("busniess_type", busniess_type);
                    stringMap.put("phone", st_mobile);
                    stringMap.put("type", type);

                    for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                        Log.e(entry.getKey() + " ff", entry.getValue() + " 11");
                    }

                    myRequest.PostCall("http://smm.smmim.com/waell/public/api/updateProfile", stringMap, new OkHttpCallback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            Log.e("f1", e.getMessage() + " 0");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException, JSONException {

                            Log.e("r1", response.body().string() + " d");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "تم حفظ التعديلات", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                } else if (type.equals("client")) {

                    stringMap.put("token", ConstantInterFace.USER.getToken());
                    stringMap.put("name", et_name.getText().toString());
                    stringMap.put("email", et_email.getText().toString());
                    stringMap.put("gender", st_gender);
                    stringMap.put("dob", date_format);
                    stringMap.put("phone", st_mobile);
                    stringMap.put("type", type);

                    myRequest.PostCall("https://mustafa.smmim.com/waell/public/api/updateProfile", stringMap, new OkHttpCallback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.v("f2", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException, JSONException {
                            Log.v("r2", response.body().string());
                        }
                    });
                }

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        Bundle bundle = getArguments();
        st_type = bundle.getString("type");
        st_job_type = bundle.getString("job_type");
        st_busniess_type = bundle.getString("busniess_type");
        busniess_type = bundle.getString("busniess_type");
        st_name = bundle.getString("name");
        st_bio = bundle.getString("bio");
        mobile = bundle.getString("mobile");
        st_email = bundle.getString("email");
        gender = bundle.getString("gender");
        st_dob = bundle.getString("dob");
        date_format = bundle.getString("dob");

        type = getType(st_type);

        getProfileData();
    }

    private void getProfileData() {
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/myprofile", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("eeeeeeeeee", e.getMessage());
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                Log.v("response", response.body().string());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (st_type.equals("worker")) {
                            tv_worker.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                            tv_worker.setTextColor(Color.WHITE);
                            tv_client.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                            tv_client.setTextColor(getResources().getColor(R.color.textGray));
                            type = "worker";
                        } else {
                            tv_client.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                            tv_client.setTextColor(Color.WHITE);
                            tv_worker.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                            tv_worker.setTextColor(getResources().getColor(R.color.textGray));
                            type = "client";
                        }

                        if (st_job_type.equals("arch")) {
                            sp_specialization.setSelection(1);
                        } else if (st_job_type.equals("graphic")) {
                            sp_specialization.setSelection(2);
                        } else if (st_job_type.equals("inter")) {
                            sp_specialization.setSelection(0);
                        } else if (st_job_type.equals("moshen")) {
                            sp_specialization.setSelection(4);
                        } else if (st_job_type.equals("wall")) {
                            sp_specialization.setSelection(3);
                        }


                        if (st_busniess_type.equals("individual")) {
                            tv_individual.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                            tv_individual.setTextColor(Color.WHITE);
                            tv_company.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                            tv_company.setTextColor(getResources().getColor(R.color.textGray));
                        } else if (st_busniess_type.equals("company")) {
                            tv_company.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                            tv_company.setTextColor(Color.WHITE);
                            tv_individual.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                            tv_individual.setTextColor(getResources().getColor(R.color.textGray));
                        }


                        et_name.setText(st_name);
                        et_bio.setText(st_bio);
                        et_mobile2.setText(mobile.substring(0, 3));
                        et_mobile1.setText(mobile.substring(4));
                        et_email.setText(st_email);
                        if (gender.equals("female")) {
                            sp_gender.setSelection(1);

                        } else if (gender.equals("male")) {
                            sp_gender.setSelection(0);
                        }

                        String[] s = st_dob.split("/");
                        tv_dob3.setText(s[0]);
                        tv_dob2.setText(s[1]);
                        tv_dob1.setText(s[2]);
                    }
                });
            }
        });
    }

    private void getGender(String st_gender) {
        this.st_gender = st_gender;
    }

    private void getSpecialization(String st_specialization) {
        this.st_specialization = st_specialization;
    }

    private String getType(String st_type) {
        this.type = st_type;
        return type;
    }
}
