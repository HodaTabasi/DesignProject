package com.example.maryam.sproject.Fragments;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.maryam.sproject.MyRequest;
import com.example.maryam.sproject.OkHttpCallback;
import com.example.maryam.sproject.R;

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
    String type, busniess_type, myFormat, date_format, st_specialization, st_gender;
    SimpleDateFormat sdf;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;

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
//                        st_gender = "male";
                        Log.e("f", sp_gender.getId() + " ");
                        getGender("male");
                        break;
                    case 1:
//                        st_gender = "female";
                        Log.e("f1", sp_gender.getId() + " ");
                        getGender("female");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Log.e("qqqqq", "" + st_gender);
//        Log.e("wwwww", "" + st_specialization);

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

                Log.v("ooo", calendar.get(Calendar.YEAR) + "");
            }
        });


        type = "worker";
        busniess_type = "";

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
                busniess_type = "";
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


                MyRequest myRequest = new MyRequest();
                Map<String, String> stringMap = new HashMap<>();
                if (type.equals("worker")) {
                    stringMap.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2ODUxOTA0LCJleHAiOjQ4MDgxNzYwNDU5MzI1NzY3" +
                            "MDQsIm5iZiI6MTUzNjg1MTkwNCwianRpIjoiWWFsQUt0cmxDOFFhc3MxNiJ9.ti8XrIO453w789YLVLu-EqVmZZm3GXVC6O9KNwloSsI");
                    stringMap.put("name", et_name.getText().toString());
                    stringMap.put("email", et_email.getText().toString());
                    //stringMap.put("gender", et_gender.getText().toString());
                    //stringMap.put("job_type", sp_specialization.getText().toString());
                    stringMap.put("bio", et_bio.getText().toString());
                    stringMap.put("dob", date_format);
                    stringMap.put("busniess_type", busniess_type);
                    stringMap.put("type", type);

                    myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/updateProfile", stringMap, new OkHttpCallback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            Log.v("f1", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException, JSONException {

                            Log.v("r1", response.body().string());

                        }
                    });

                    Log.v("rrrrr", type + busniess_type);

                } else {
                    stringMap.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2ODUxOTA0LCJleHAiOjQ4MDgxNzYwNDU5MzI1NzY3MDQsIm5iZiI6MTUzNjg1MTkwNCwian" +
                            "RpIjoiWWFsQUt0cmxDOFFhc3MxNiJ9.ti8XrIO453w789YLVLu-EqVmZZm3GXVC6O9KNwloSsI");
                    stringMap.put("name", et_name.getText().toString());
                    stringMap.put("email", et_email.getText().toString());
                    //stringMap.put("gender", et_gender.getText().toString());
                    //stringMap.put("job_type", et_specialization.getText().toString());
                    stringMap.put("dob", date_format);
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

                    Log.v("rrrrr", type);
                }

            }
        });

        return view;
    }

    private void getGender(String st_gender) {
        this.st_gender = st_gender;
        Log.v("rrrrre", st_gender);
    }



    private void getSpecialization(String st_specialization) {
        this.st_specialization = st_specialization;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
    }
}
