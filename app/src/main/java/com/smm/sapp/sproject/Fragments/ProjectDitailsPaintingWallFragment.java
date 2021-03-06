package com.smm.sapp.sproject.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
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
import com.smm.sapp.sproject.Models.ProjectsModels;
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
public class ProjectDitailsPaintingWallFragment extends Fragment {

    private EditText mWallType;
    private ImageView mWallUploadImage;
    private EditText mWallArea;
    private ImageView mWallLikeUploadImage;
    private TextView mWallCity;
    private TextView mWallMap;
    private EditText mWallProjectDietails;
    private TextView mWallAttachment;
    private Button mWallSend;
    ImageView ic_back;
    Spinner sp_city, sp_balance;
    String st_city;

    int st_balance = 1;

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int REQUEST_CODE = 1;
    String s_lat, s_lng;

    int i = 0, j = 0, k = 0;
    Map<String, String> attachMap;
    MySpinnerAdapter adapter2;
    MySpinnerAdapter adapter3;
    Bundle bundle;
    String projectId;
    Boolean flag = false;

    public ProjectDitailsPaintingWallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_painting_wall, container, false);
    }

    private void initView() {
        mWallType = getView().findViewById(R.id.wall_type);
        mWallUploadImage = getView().findViewById(R.id.wall_upload_image);
        mWallLikeUploadImage = getView().findViewById(R.id.wall_like_upload_image);
        mWallCity = getView().findViewById(R.id.wall_city);
        mWallArea = getView().findViewById(R.id.wall_area);
        mWallMap = getView().findViewById(R.id.wall_map);
        mWallProjectDietails = getView().findViewById(R.id.wall_project_dietails);
        mWallAttachment = getView().findViewById(R.id.wall_attachment);
        mWallSend = getView().findViewById(R.id.wall_send);
        sp_city = getView().findViewById(R.id.sp_city);
        sp_balance = getView().findViewById(R.id.sp_chooese_balance);

        mWallMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
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
        setSpinner();

        if (!getArguments().isEmpty()) {
            bundle = getArguments();
            ProjectsModels models = bundle.getParcelable("object");
            flag = bundle.getBoolean("flag", false);
            mWallType.setText(models.getName());
            mWallArea.setText(models.getAddtion_info().getArea());
            sp_city.setSelection(adapter2.getPosition(models.getAddtion_info().getCity()));
            st_city = models.getAddtion_info().getCity();
            Log.e("qqqqqq",st_city);
            sp_balance.setSelection(adapter3.getPosition(ConstantInterFace.array[Integer.parseInt(models.getBalance())]));
            st_balance = Integer.parseInt(models.getBalance());
            mWallProjectDietails.setText(models.getDescr());
            mWallMap.setText("خط الطول = " + models.getAddtion_info().getLng() + "\n" + "خط العرض = " + models.getAddtion_info().getLat());
            s_lat = models.getAddtion_info().getLat();
            s_lng = models.getAddtion_info().getLng();
            projectId = String.valueOf(models.getId());
        }

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });


        mWallSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWallType.getText().toString().matches("") || st_city.matches("") || mWallArea.getText().toString().matches("")
                        || mWallMap.getText().toString().matches("") || mWallProjectDietails.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                } else {
                    sendWallRequest("projectmakewall", "projectupdatewall");
                }

            }
        });

        mWallUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        startActivityForResult(pickPhoto, 2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mWallLikeUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        startActivityForResult(pickPhoto, 3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mWallAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileBrowse();
            }
        });
    }

    private void setSpinner() {
        adapter2 = new MySpinnerAdapter(
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
        adapter3 = new MySpinnerAdapter(
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
                        setBalance(1);
                        break;
                    case 1:
                        setBalance(2);
                        break;
                    case 2:
                        setBalance(3);
                        break;
                    case 3:
                        setBalance(4);
                        break;
                    case 4:
                        setBalance(5);
                        break;
                    case 5:
                        setBalance(6);
                        break;
                    case 6:
                        setBalance(7);
                        break;
                    case 7:
                        setBalance(8);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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


    private void sendWallRequest(String projectmakewall, String projectupdatewall) {
        String url;
        final String success_msg;
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();

        if (flag) {
            url = projectupdatewall;
            map.put("project_id", projectId);
            Log.e("uuuuu", url+""+projectId);
            success_msg = "تم التعديل بنجاح قيد المراجعة من الادارة";
        } else {
            url = projectmakewall;
            Log.e("uuuuu", url);
            success_msg = "تم الاضافة بنجاح قيد المراجعة من الادارة";
        }
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("name", mWallType.getText().toString());
        map.put("descr", mWallProjectDietails.getText().toString());
        map.put("balance", String.valueOf(st_balance));
        map.put("area", mWallArea.getText().toString());
        map.put("city", st_city);
        map.put("lng", s_lng);
        map.put("lat", s_lat);

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/" + url, map, attachMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("failure", "ffff");
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

                Log.e("success", "ssss");

//                String s = response.body().string();
                final JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Toast.makeText(getActivity(), success_msg, Toast.LENGTH_SHORT).show();
                                Log.e("success", "uuuuu");

                            } else {
                                Log.e("success", "ffffff");
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 2);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;

            case 3:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 3);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                double lng = data.getDoubleExtra("lng", 0);
                double lat = data.getDoubleExtra("lat", 0);

                s_lat = String.valueOf(lat);
                s_lng = String.valueOf(lng);

                mWallMap.setText("خط الطول = " + s_lng + "\n" + "خط العرض = " + s_lat);
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

    private void setCity(String st_city) {
        this.st_city = st_city;
    }

    private void setBalance(int st_balance) {
        this.st_balance = st_balance;
    }


}

