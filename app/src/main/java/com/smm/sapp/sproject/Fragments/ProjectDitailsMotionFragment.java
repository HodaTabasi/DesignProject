package com.smm.sapp.sproject.Fragments;


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

import com.obsez.android.lib.filechooser.ChooserDialog;
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
public class ProjectDitailsMotionFragment extends Fragment {


    private EditText mMotionType;
    private EditText mProjectName;
    private EditText mProjectTime;
    private EditText mAboutActivity;
    private ImageView mMotionLikeImage;
    private EditText mMotionBalance;
    private EditText mProjectDetiailsMotion;
    private TextView mAttachmentMotion;
    private Button mSendMotion;
    ImageView ic_back;

    int i = 0;
    Map<String, String> attachMap;

    public ProjectDitailsMotionFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_motion, container, false);
    }

    private void initView() {
        mMotionType = getView().findViewById(R.id.motion_type);
        mProjectName = getView().findViewById(R.id.project_name);
        mProjectTime = getView().findViewById(R.id.project_time);
        mAboutActivity = getView().findViewById(R.id.about_activity);
        mMotionLikeImage = getView().findViewById(R.id.motion_like_image);
        mMotionBalance = getView().findViewById(R.id.motion_balance);
        mProjectDetiailsMotion = getView().findViewById(R.id.project_detiails_motion);
        mAttachmentMotion = getView().findViewById(R.id.attachment_motion);
        mSendMotion = getView().findViewById(R.id.send_motion);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        attachMap = new HashMap<>();

        mSendMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMotionType.getText().toString().matches("") || mProjectName.getText().toString().matches("") || mProjectTime.getText().toString().matches("")
                        || mAboutActivity.getText().toString().matches("") || mMotionBalance.getText().toString().matches("") || mProjectDetiailsMotion.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();

                } else {
                    sendMotionRequest();
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

        mMotionLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
            }
        });
        mAttachmentMotion.setOnClickListener(new View.OnClickListener() {
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

    private void sendMotionRequest() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        //map.put("name", mMotionType.getText().toString());
        map.put("name", mProjectName.getText().toString());
        map.put("dur", mProjectTime.getText().toString());
        map.put("about", mAboutActivity.getText().toString());
        map.put("balance", mMotionBalance.getText().toString());
        map.put("descr", mProjectDetiailsMotion.getText().toString());

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/projectmakemoshen", map, attachMap, new OkHttpCallback() {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    String filePath = PathUtil.getPath(getActivity(), selectedImage);
                    Log.e("dd", " " + filePath);
                    attachMap.put("photos[" + (i++) + "]", filePath);
                    Toast.makeText(getContext(), "تم اضافة الصورة فى الخلفية بنجاح", Toast.LENGTH_SHORT).show();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
