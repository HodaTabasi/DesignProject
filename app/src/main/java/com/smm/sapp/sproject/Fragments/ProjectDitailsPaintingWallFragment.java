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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    private EditText mWallCity;
    private TextView mWallMap;
    private EditText mWallBalance;
    private EditText mWallProjectDietails;
    private TextView mWallAttachment;
    private Button mWallSend;
    ImageView ic_back;

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int REQUEST_CODE = 1;
    String s_lat, s_lng;

    int i = 0,j = 0,k = 0;
    Map<String,String> attachMap;

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
        mWallBalance = getView().findViewById(R.id.wall_balance);
        mWallProjectDietails = getView().findViewById(R.id.wall_project_dietails);
        mWallAttachment = getView().findViewById(R.id.wall_attachment);
        mWallSend = getView().findViewById(R.id.wall_send);

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
                sendWallRequest();

            }
        });

        mWallUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 2);//one can be replaced with any action code
            }
        });

        mWallLikeUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 3);//one can be replaced with any action code
            }
        });
        mWallAttachment.setOnClickListener(new View.OnClickListener() {
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
                        attachMap.put("attachs["+(i++)+"]",path);
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


    private void sendWallRequest() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("name", mWallType.getText().toString());
        map.put("city", mWallCity.getText().toString());
        map.put("area", mWallArea.getText().toString());
        map.put("lng", s_lng);
        map.put("lat", s_lat);
        map.put("balance", mWallBalance.getText().toString());
        map.put("descr", mWallProjectDietails.getText().toString());

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/projectmakewall", map,attachMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
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
                Toast.makeText(getActivity(), "no data moved", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    String filePath = PathUtil.getPath(getActivity(), selectedImage);
                    Log.e("dd", " " + filePath);
                    attachMap.put("similars["+(k++)+"]",filePath);
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
                    attachMap.put("photos["+(j++)+"]",filePath);
                    Toast.makeText(getContext(), "تم اضافة الصورة فى الخلفية بنجاح", Toast.LENGTH_SHORT).show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

