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
public class ProjectDitailesGraphicsFragment extends Fragment {

    private EditText mGhType;
    private EditText mProjectNameGh;
    private EditText mAboutActivity;
    private ImageView mGhUploadImageLike;
    private TextView mInnovation;
    private TextView mDevelop;
    private TextView mAskForDesign;
    private TextView mNo;
    private TextView mYes;
    private TextView mNewProject;
    private EditText mGhBalance;
    private EditText mProjectDeitailsGh;
    private TextView mGhAttachment;
    private Button mSendGh;

    String savedValue1 = "", savedValue2 = "";

    ImageView ic_back;

    int i = 0;
    Map<String, String> attachMap;

    public ProjectDitailesGraphicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditailes_graphics, container, false);
    }

    private void initView() {
        mGhType = getView().findViewById(R.id.gh_type);
        mProjectNameGh = getView().findViewById(R.id.project_name_gh);
        mAboutActivity = getView().findViewById(R.id.about_activity);
        mGhUploadImageLike = getView().findViewById(R.id.gh_upload_image_like);
        mInnovation = getView().findViewById(R.id.innovation);
        mDevelop = getView().findViewById(R.id.develop);
        mAskForDesign = getView().findViewById(R.id.ask_for_design);
        mNo = getView().findViewById(R.id.no);
        mYes = getView().findViewById(R.id.yes);
        mNewProject = getView().findViewById(R.id.new_project);
        mGhBalance = getView().findViewById(R.id.gh_balance);
        mProjectDeitailsGh = getView().findViewById(R.id.project_deitails_gh);
        mGhAttachment = getView().findViewById(R.id.gh_attachment);
        mSendGh = getView().findViewById(R.id.send_gh);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        attachMap = new HashMap<>();

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        mSendGh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGraphicRequest();
            }
        });

        mGhUploadImageLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
            }
        });

        mGhAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileBrowse();
            }
        });

        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedValue1 = "0";
            }
        });

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedValue1 = "1";
            }
        });

        mInnovation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedValue2 = "innovation";
            }
        });

        mDevelop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedValue2 = "develop";
            }
        });

    }

    private void sendGraphicRequest() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        //map.put("name", mMotionType.getText().toString());
        map.put("name", mProjectNameGh.getText().toString());
        //map.put("about", mAboutActivity.getText().toString());
        map.put("newp", savedValue1);
        map.put("d_type", savedValue2);
        map.put("balance", mGhBalance.getText().toString());
        map.put("descr", mProjectDeitailsGh.getText().toString());

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/projectmakegraphic", map, attachMap, new OkHttpCallback() {
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
