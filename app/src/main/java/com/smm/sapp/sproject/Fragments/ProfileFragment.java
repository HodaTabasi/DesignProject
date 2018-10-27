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

import com.google.gson.Gson;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.MySpinnerAdapter;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    String type, busniess_type, myFormat, bu_date_format, st_specialization, st_gender, st_mobile;
    SimpleDateFormat sdf;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    String bu_type, bu_job_type, bu_busniess_type, bu_name, bu_bio, bu_mobile, bu_email, bu_gender, bu_dob;
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

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.spinner_items, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp_specialization.setAdapter(adapter);
        MySpinnerAdapter adapter2 = new MySpinnerAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.spinner_items))
        );
        sp_specialization.setAdapter(adapter2);
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

//        ArrayAdapter<CharSequence> gender_adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.gender_items, android.R.layout.simple_spinner_item);
//        gender_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp_gender.setAdapter(gender_adapter);
        MySpinnerAdapter adapter1 = new MySpinnerAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.gender_items))
        );
        sp_gender.setAdapter(adapter1);
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

                myFormat = "dd/MM/yyyy";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                bu_dob = sdf.format(calendar.getTime());

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

        if (!ConstantInterFace.IS_USER_COMPLETEED) {

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

                    bu_type = "client";
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

                    bu_type = "worker";

                }
            });

        }

        tv_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_company.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_company.setTextColor(getResources().getColor(R.color.white));

                tv_individual.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_individual.setTextColor(getResources().getColor(R.color.textGray));

                bu_busniess_type = "company";
            }
        });

        tv_individual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_individual.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_individual.setTextColor(getResources().getColor(R.color.white));

                tv_company.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_company.setTextColor(getResources().getColor(R.color.textGray));

                bu_busniess_type = "individual";
            }
        });


        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUpdate();
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

        if (ConstantInterFace.IS_USER_COMPLETEED) {
            bu_type = bundle.getString("type");

            if (bu_type.equals("worker")) {
                bu_job_type = bundle.getString("job_type");
                bu_busniess_type = bundle.getString("busniess_type");
                bu_name = bundle.getString("name");
                bu_bio = bundle.getString("bio");
                bu_mobile = bundle.getString("bu_mobile");
                bu_email = bundle.getString("email");
                bu_gender = bundle.getString("bu_gender");
                bu_dob = bundle.getString("dob");
                setWorkerProfileData();

            } else if (bu_type.equals("client")) {
                bu_name = bundle.getString("name");
                bu_mobile = bundle.getString("bu_mobile");
                bu_email = bundle.getString("email");
                bu_gender = bundle.getString("bu_gender");
                bu_dob = bundle.getString("dob");
                setClientProfileData();
            }

        } else {
            bu_mobile = bundle.getString("phone");
            et_mobile2.setText(bu_mobile.substring(0, 3));
            et_mobile1.setText(bu_mobile.substring(3));
            bu_type = "worker";
            tv_worker.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
            tv_worker.setTextColor(Color.WHITE);
            tv_client.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
            tv_client.setTextColor(getResources().getColor(R.color.textGray));
        }
    }

    private void setWorkerProfileData() {

        Log.e("qqqqqq", "qqqqq");


        try {
            if (bu_type.equals("worker")) {
                tv_worker.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_worker.setTextColor(Color.WHITE);
                tv_client.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_client.setTextColor(getResources().getColor(R.color.textGray));

                sp_specialization.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.VISIBLE);
                tv_bio.setVisibility(View.VISIBLE);
                et_bio.setVisibility(View.VISIBLE);

            } else {
                tv_client.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_client.setTextColor(Color.WHITE);
                tv_worker.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_worker.setTextColor(getResources().getColor(R.color.textGray));

                sp_specialization.setVisibility(View.GONE);
                linear2.setVisibility(View.GONE);
                tv_bio.setVisibility(View.GONE);
                et_bio.setVisibility(View.GONE);

            }

            if (bu_job_type.equals("arch")) {
                sp_specialization.setSelection(1);
            } else if (bu_job_type.equals("graphic")) {
                sp_specialization.setSelection(2);
            } else if (bu_job_type.equals("inter")) {
                sp_specialization.setSelection(0);
            } else if (bu_job_type.equals("moshen")) {
                sp_specialization.setSelection(4);
            } else if (bu_job_type.equals("wall")) {
                sp_specialization.setSelection(3);
            }


            if (bu_busniess_type.equals("individual")) {
                tv_individual.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_individual.setTextColor(Color.WHITE);
                tv_company.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_company.setTextColor(getResources().getColor(R.color.textGray));
            } else if (bu_busniess_type.equals("company")) {
                tv_company.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
                tv_company.setTextColor(Color.WHITE);
                tv_individual.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
                tv_individual.setTextColor(getResources().getColor(R.color.textGray));
            }

            et_name.setText(bu_name);
            et_bio.setText(bu_bio);
            et_mobile2.setText(bu_mobile.substring(0, 3));
            et_mobile1.setText(bu_mobile.substring(3));

            et_email.setText(bu_email);
            if (bu_gender.equals("female")) {
                sp_gender.setSelection(1);

            } else if (bu_gender.equals("male")) {
                sp_gender.setSelection(0);
            }

            String[] s = bu_dob.split("/");
            tv_dob3.setText(s[0]);
            tv_dob2.setText(s[1]);
            tv_dob1.setText(s[2]);
        } catch (Exception e) {

        }

    }

    private void setClientProfileData() {
        if (bu_type.equals("worker")) {
            tv_worker.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
            tv_worker.setTextColor(Color.WHITE);
            tv_client.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
            tv_client.setTextColor(getResources().getColor(R.color.textGray));
            sp_specialization.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.VISIBLE);
            tv_bio.setVisibility(View.VISIBLE);
            et_bio.setVisibility(View.VISIBLE);

        } else {
            tv_client.setBackground(getResources().getDrawable(R.drawable.solid_account_shape));
            tv_client.setTextColor(Color.WHITE);
            tv_worker.setBackground(getResources().getDrawable(R.drawable.report_layout_shap));
            tv_worker.setTextColor(getResources().getColor(R.color.textGray));
            sp_specialization.setVisibility(View.GONE);
            linear2.setVisibility(View.GONE);
            tv_bio.setVisibility(View.GONE);
            et_bio.setVisibility(View.GONE);
        }


        et_name.setText(bu_name);
        et_mobile2.setText(bu_mobile.substring(0, 3));
        et_mobile1.setText(bu_mobile.substring(3));
        et_email.setText(bu_email);
        if (bu_gender.equals("female")) {
            sp_gender.setSelection(1);

        } else if (bu_gender.equals("male")) {
            sp_gender.setSelection(0);
        }

        String[] s = bu_dob.split("/");
        tv_dob3.setText(s[0]);
        tv_dob2.setText(s[1]);
        tv_dob1.setText(s[2]);


    }


    private void saveUpdate() {

        Log.e("rrrrr", "tttttt");
        if (et_bio.getVisibility() == View.VISIBLE) {
            if (bu_type.matches("") || st_specialization.matches("") || bu_busniess_type == null
                    || et_name.getText().toString().matches("") || et_bio.getText().toString().matches("")
                    || et_email.getText().toString().matches("") || st_gender.matches("")
                    || bu_dob.matches("")) {

                Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                Log.e("rrrrr", "dfdfdfd");

            } else {
                send();
            }
        } else if (et_bio.getVisibility() == View.GONE) {
            if (bu_type.matches("")
                    || et_name.getText().toString().matches("")
                    || et_email.getText().toString().matches("") || st_gender.matches("")
                    || bu_dob.matches("")) {

                Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                Log.e("rrrrr", "dfdfdfd");

            } else {
                send();
            }
        }

    }

    private void send() {
        MyProgressDialog.showDialog(getContext());

        st_mobile = et_mobile2.getText().toString() + et_mobile1.getText().toString();
        if (ConstantInterFace.IS_USER_COMPLETEED) {
            MyRequest myRequest = new MyRequest();
            Map<String, String> stringMap = new HashMap<>();

            if (bu_type.equals("worker")) {
                stringMap.put("token", ConstantInterFace.USER.getToken());
                stringMap.put("name", et_name.getText().toString());
                stringMap.put("email", et_email.getText().toString());
                stringMap.put("bu_gender", st_gender);
                stringMap.put("job_type", st_specialization);
                stringMap.put("bio", et_bio.getText().toString());
                stringMap.put("dob", bu_dob);
                stringMap.put("busniess_type", bu_busniess_type);
                stringMap.put("phone", st_mobile);
                stringMap.put("type", bu_type);

                myRequest.PostCall("http://smm.smmim.com/waell/public/api/updateProfile", stringMap, new OkHttpCallback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        MyProgressDialog.dismissDialog();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();

                            }
                        });
                        MyProgressDialog.dismissDialog();
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException, JSONException {
                        MyProgressDialog.dismissDialog();
                        final JSONObject jsonObject = new JSONObject(response.body().string());
                        final JSONObject object = jsonObject.getJSONObject("status");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (object.getBoolean("success")) {
                                        Toast.makeText(getContext(), "تم حفظ التعديلات", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });

            } else if (bu_type.equals("client")) {
                stringMap.put("token", ConstantInterFace.USER.getToken());
                stringMap.put("name", et_name.getText().toString());
                stringMap.put("email", et_email.getText().toString());
                stringMap.put("bu_gender", st_gender);
                stringMap.put("dob", bu_dob);
                stringMap.put("phone", st_mobile);
                stringMap.put("type", bu_type);

                myRequest.PostCall("https://smm.smmim.com/waell/public/api/updateProfile", stringMap, new OkHttpCallback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        MyProgressDialog.dismissDialog();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                        MyProgressDialog.dismissDialog();
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException, JSONException {
                        MyProgressDialog.dismissDialog();
                        final JSONObject jsonObject = new JSONObject(response.body().string());
                        final JSONObject object = jsonObject.getJSONObject("status");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (object.getBoolean("success")) {
                                        Toast.makeText(getContext(), "تم حفظ التعديلات", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getActivity(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }


        } else if (!ConstantInterFace.IS_USER_COMPLETEED) {

            MyRequest myRequest = new MyRequest();
            Map<String, String> stringMap = new HashMap<>();

            if (bu_type.equals("worker")) {

                Log.e("wwwww", ConstantInterFace.USER.getToken());
                Log.e("wwwww", et_name.getText().toString());
                Log.e("wwwww", et_email.getText().toString());
                Log.e("wwwww", st_gender);
                Log.e("wwwww", st_specialization);
                Log.e("wwwww", et_bio.getText().toString());
                Log.e("wwwww", bu_dob);
                Log.e("wwwww", bu_busniess_type);
                Log.e("wwwww", st_mobile);
                Log.e("wwwww", bu_type);


                stringMap.put("token", ConstantInterFace.USER.getToken());
                stringMap.put("name", et_name.getText().toString());
                stringMap.put("email", et_email.getText().toString());
                stringMap.put("bu_gender", st_gender);
                stringMap.put("job_type", st_specialization);
                stringMap.put("bio", et_bio.getText().toString());
                stringMap.put("dob", bu_dob);
                stringMap.put("busniess_type", bu_busniess_type);
                stringMap.put("phone", st_mobile);
                stringMap.put("type", bu_type);

                myRequest.PostCall("http://smm.smmim.com/waell/public/api/updateProfile", stringMap, new OkHttpCallback() {
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
                        final JSONObject jsonObject = new JSONObject(response.body().string());
                        final JSONObject object = jsonObject.getJSONObject("status");


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (object.getBoolean("success")) {
                                        Toast.makeText(getContext(), "تم حفظ التعديلات", Toast.LENGTH_SHORT).show();
                                        ConstantInterFace.IS_USER_COMPLETEED = true;

                                    } else {
                                        Toast.makeText(getActivity(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });
            } else if (bu_type.equals("client")) {
                stringMap.put("token", ConstantInterFace.USER.getToken());
                stringMap.put("name", et_name.getText().toString());
                stringMap.put("email", et_email.getText().toString());
                stringMap.put("bu_gender", st_gender);
                stringMap.put("dob", bu_dob);
                stringMap.put("phone", st_mobile);
                stringMap.put("type", bu_type);


                myRequest.PostCall("https://mustafa.smmim.com/waell/public/api/updateProfile", stringMap, new OkHttpCallback() {
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
                        final JSONObject jsonObject = new JSONObject(response.body().string());
                        final JSONObject object = jsonObject.getJSONObject("status");

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (object.getBoolean("success")) {
                                        ConstantInterFace.IS_USER_COMPLETEED = true;
                                        Toast.makeText(getContext(), "تم حفظ التعديلات", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getActivity(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }

        }

    }

    private void getGender(String st_gender) {
        this.st_gender = st_gender;
    }

    private void getSpecialization(String st_specialization) {
        this.st_specialization = st_specialization;
    }
}
