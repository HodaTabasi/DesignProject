package com.smm.sapp.sproject.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.obsez.android.lib.filechooser.ChooserDialog;
import com.smm.sapp.sproject.Activities.MapActivity;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.PathUtil;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.MySpinnerAdapter;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailsInterFragment extends Fragment {

    private EditText mInType;
    private TextView mChooeseStyle;
    private EditText mDesignColor;
    private ImageView mUploadImage;
    private EditText mArea2;
    private ImageView mUploadLikeImage;
    private TextView mCity;
    private TextView mMap;
    private EditText mBalance;
    private EditText mProjectDetailes;
    private TextView mAttachmentIn;
    private Button mSendIn;
    private Spinner sp_chooese_style, sp_city, sp_balance;
    String st_style, st_city, st_balance;

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int REQUEST_CODE = 1;
    String s_lat, s_lng;
    ImageView ic_back;

    int i = 0, j = 0, k = 0;
    Map<String, String> attachMap;


    public ProjectDetailsInterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_inter, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        attachMap = new HashMap<>();

        if (isServicesOk()) {
            initView();
        }

        mSendIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInType.getText().toString().matches("") || st_style.matches("") || mDesignColor.getText().toString().matches("")
                        || mArea2.getText().toString().matches("") || st_city.matches("") || mMap.getText().toString().matches("")
                        || st_balance.matches("") || mProjectDetailes.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();

                } else {
                    sendInterDesignRequest();
                }
            }
        });

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        setSpinners();


    }

    private void setSpinners() {

        MySpinnerAdapter adapter1 = new MySpinnerAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.spinner_styles))
        );
        sp_chooese_style.setAdapter(adapter1);
        sp_chooese_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        setStyle("مودرن");
                        break;
                    case 1:
                        setStyle("كلاسيك");
                        break;
                    case 2:
                        setStyle("نيو كلاسيك");
                        break;
                    case 3:
                        setStyle("صناعي");
                        break;
                    case 4:
                        setStyle("بوهيمي");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        MySpinnerAdapter adapter2 = new MySpinnerAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.spinner_cities))
        );
        sp_city.setAdapter(adapter2);
        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        setCity("الرياض");
                        break;
                    case 1:
                        setCity("مكة المكرمة");
                        break;
                    case 2:
                        setCity("المدينة المنورة");
                        break;
                    case 3:
                        setCity("جدة");
                        break;
                    case 4:
                        setCity("سلطانة");
                        break;
                    case 5:
                        setCity("الدمام");
                        break;
                    case 6:
                        setCity("تبوك");
                        break;
                    case 7:
                        setCity("الطائف");
                        break;
                    case 8:
                        setCity("بريدة");
                        break;
                    case 9:
                        setCity("خميس مشيط");
                        break;
                    case 10:
                        setCity("الهفوف");
                        break;
                    case 11:
                        setCity("المبرز");
                        break;
                    case 12:
                        setCity("حفر الباطن");
                        break;
                    case 13:
                        setCity("حائل");
                        break;
                    case 14:
                        setCity("نجران");
                        break;
                    case 15:
                        setCity("الجبيل");
                        break;
                    case 16:
                        setCity("ابها");
                        break;
                    case 17:
                        setCity("ينبع");
                        break;
                    case 18:
                        setCity("الخبر");
                        break;
                    case 19:
                        setCity("عنيزة");
                        break;
                    case 20:
                        setCity("عرعر");
                        break;
                    case 21:
                        setCity("سكاكا");
                        break;
                    case 22:
                        setCity("جيزان");
                        break;
                    case 23:
                        setCity("القريات");
                        break;
                    case 24:
                        setCity("الباحة");
                        break;
                    case 25:
                        setCity("باقى");
                        break;
                    case 26:
                        setCity("القصيم");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        MySpinnerAdapter adapter3 = new MySpinnerAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.spinner_balance))
        );
        sp_balance.setAdapter(adapter3);
        sp_balance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        setBalance("500-1000");
                        break;
                    case 1:
                        setBalance("1000-2000");
                        break;
                    case 2:
                        setBalance("3000-3000");
                        break;
                    case 3:
                        setBalance("3000-4000");
                        break;
                    case 4:
                        setBalance("4000-5000");
                        break;
                    case 5:
                        setBalance("5000-6500");
                        break;
                    case 6:
                        setBalance("6500-8000");
                        break;
                    case 7:
                        setBalance("اكثر من 8000");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public boolean isServicesOk() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
        if (available == ConnectionResult.SUCCESS) {
            //every thing is fine and the user can make map request
            return true;

        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //error occur but we can resolve it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(getActivity(), "you can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void sendInterDesignRequest() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("name", mInType.getText().toString());
        map.put("style", st_style);
        map.put("colors", mDesignColor.getText().toString());
        map.put("city", st_city);
        map.put("area", mArea2.getText().toString());
        map.put("lng", s_lng);
        map.put("lat", s_lat);
        map.put("balance", st_balance);
        map.put("descr", mProjectDetailes.getText().toString());

        Log.e("qqqqq", st_city+""+st_balance+""+st_style);

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/projectmakeinter", map, attachMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "فشل", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Toast.makeText(getActivity(), "تم اضافة مشروع بنجاح", Toast.LENGTH_SHORT).show();
                                Log.e("fdd", object.getString("message"));

                            } else {
                                Toast.makeText(getActivity(), "" + object.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    private void initView() {
        mInType = getView().findViewById(R.id.in_type);
        mChooeseStyle = getView().findViewById(R.id.chooese_style);
        mDesignColor = getView().findViewById(R.id.design_color);
        mUploadImage = getView().findViewById(R.id.upload_image);
        mArea2 = getView().findViewById(R.id.area2);
        mUploadLikeImage = getView().findViewById(R.id.upload_like_image);
        mCity = getView().findViewById(R.id.city);
        mMap = getView().findViewById(R.id.map);
        mProjectDetailes = getView().findViewById(R.id.project_detailes);
        mAttachmentIn = getView().findViewById(R.id.attachment_in);
        mSendIn = getView().findViewById(R.id.send_in);
        sp_chooese_style = getView().findViewById(R.id.sp_chooese_style);
        sp_city = getView().findViewById(R.id.sp_city);
        sp_balance = getView().findViewById(R.id.sp_chooese_balance);

        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 2);//one can be replaced with any action code
            }
        });

        mUploadLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 3);//one can be replaced with any action code
            }
        });
        mAttachmentIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileBrowse();
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
                        Toast.makeText(getContext(), "FOLDER: " + path, Toast.LENGTH_SHORT).show();
                        attachMap.put("attachs[" + (i++) + "]", path);
                        Toast.makeText(getContext(), "تم اضافة الملف في المرفقات بنجاح", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                double lng = data.getDoubleExtra("lng", 0);
                double lat = data.getDoubleExtra("lat", 0);

                s_lat = String.valueOf(lat);
                s_lng = String.valueOf(lng);

                mMap.setText("خط الطول = " + s_lng + "\n" + "خط العرض = " + s_lat);
                Log.d("lat", s_lat);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "لا يوجد بيانات", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    String filePath = PathUtil.getPath(getActivity(), selectedImage);
                    Log.e("dd", " " + filePath);
                    attachMap.put("similars[" + (k++) + "]", filePath);
                    Toast.makeText(getContext(), "تم اضافة الصورة فى الخلفية بنجاح", Toast.LENGTH_SHORT).show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    String filePath = PathUtil.getPath(getActivity(), selectedImage);
                    Log.e("dd", " " + filePath);
                    attachMap.put("photos[" + (j++) + "]", filePath);
                    Toast.makeText(getContext(), "تم اضافة الصورة فى الخلفية بنجاح", Toast.LENGTH_SHORT).show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void setStyle(String st_style) {
        this.st_style = st_style;
    }

    private void setCity(String st_city) {
        this.st_city = st_city;
    }

    private void setBalance(String st_balance) {
        this.st_balance = st_balance;
    }
}
