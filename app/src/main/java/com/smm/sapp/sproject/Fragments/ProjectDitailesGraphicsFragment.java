package com.smm.sapp.sproject.Fragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.obsez.android.lib.filechooser.ChooserDialog;
import com.smm.sapp.sproject.Adapters.ProjectPhotoAdapter;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
public class ProjectDitailesGraphicsFragment extends Fragment {

    private EditText mGhType;
    private EditText mProjectNameGh;
    private EditText mAboutActivity;
    private ImageView mGhUploadImageLike1;
    private TextView mInnovation;
    private TextView mDevelop;
    private TextView mAskForDesign;
    private TextView mNo;
    private TextView mYes;
    private TextView mNewProject;
    private EditText mProjectDeitailsGh;
    private TextView mGhAttachment;
    private Button mSendGh;
    LinearLayout lien;
    ImageView ic_back;
    private RecyclerView rec_P;

    String savedValue1 = "", savedValue2 = "";
    int i = 0;
    int st_balance = 1;

    private Spinner sp_balance;


    Map<String, String> attachMap;
    ArrayList<Bitmap> bitmaps;
    ArrayList<String> bStrings;
    ProjectPhotoAdapter adapter;

    MySpinnerAdapter adapter3;
    Bundle bundle;
    String projectId;
    Boolean flag = false;


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
        mGhUploadImageLike1 = getView().findViewById(R.id.gh_upload_image_like1);
        mInnovation = getView().findViewById(R.id.innovation);
        mDevelop = getView().findViewById(R.id.develop);
        mAskForDesign = getView().findViewById(R.id.ask_for_design);
        mNo = getView().findViewById(R.id.no);
        mYes = getView().findViewById(R.id.yes);
        mNewProject = getView().findViewById(R.id.new_project);

        mProjectDeitailsGh = getView().findViewById(R.id.project_deitails_gh);
        mGhAttachment = getView().findViewById(R.id.gh_attachment);
        sp_balance = getView().findViewById(R.id.sp_chooese_balance);
        mSendGh = getView().findViewById(R.id.send_gh);
        lien = getView().findViewById(R.id.lien);
        rec_P = getView().findViewById(R.id.rec_P);
        rec_P.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bitmaps = new ArrayList<>();
        bStrings = new ArrayList<>();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        setSpinner();
        adapter = new ProjectPhotoAdapter(getContext(), R.layout.layout_item_photos, bitmaps, bStrings, true);
        rec_P.setAdapter(adapter);

        attachMap = new HashMap<>();

        if (!getArguments().isEmpty()) {
            bundle = getArguments();
            ProjectsModels models = bundle.getParcelable("object");
            flag = bundle.getBoolean("flag", false);
            mGhType.setText(models.getName());
            mProjectNameGh.setText(models.getName());
            mAboutActivity.setText(models.getAddtion_info().getAbout());
            sp_balance.setSelection(adapter3.getPosition(ConstantInterFace.array[Integer.parseInt(models.getBalance())]));
            st_balance = Integer.parseInt(models.getBalance());
            mProjectDeitailsGh.setText(models.getDescr());
            projectId = String.valueOf(models.getId());
            Log.e("eeeee", projectId + "");

            if (models.getAddtion_info().getNewp().equals("1")) {
                savedValue1 = "1";
                mYes.setTextColor(Color.parseColor("#65bafb"));
                mNo.setTextColor(Color.parseColor("#000000"));
            } else {
                savedValue1 = "0";
                mYes.setTextColor(Color.parseColor("#000000"));
                mNo.setTextColor(Color.parseColor("#65bafb"));
            }

            if (models.getAddtion_info().getD_type().equals("develop")) {
                savedValue2 = "develop";
                mDevelop.setTextColor(Color.parseColor("#65bafb"));
                mInnovation.setTextColor(Color.parseColor("#000000"));
            } else {
                savedValue2 = "innovation";
                mInnovation.setTextColor(Color.parseColor("#65bafb"));
                mDevelop.setTextColor(Color.parseColor("#000000"));
            }

        }

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
                if (mGhType.getText().toString().matches("") || mProjectNameGh.getText().toString().matches("") || mAboutActivity.getText().toString().matches("")
                        || mProjectDeitailsGh.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                } else {
                    attachMap.clear();
                    for (String s : bStrings) {
                        Log.e("ddddd", " " + s);
                        attachMap.put("photos[" + (i++) + "]", s);
                    }
                    sendGraphicRequest("projectmakegraphic", "projectupdategraphic");
                }
            }
        });

        mGhUploadImageLike1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                mNo.setTextColor(Color.parseColor("#65bafb"));
                mYes.setTextColor(Color.parseColor("#000000"));

            }
        });

        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedValue1 = "1";
                mYes.setTextColor(Color.parseColor("#65bafb"));
                mNo.setTextColor(Color.parseColor("#000000"));
            }
        });

        mInnovation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedValue2 = "innovation";
                mInnovation.setTextColor(Color.parseColor("#65bafb"));
                mDevelop.setTextColor(Color.parseColor("#000000"));
            }
        });

        mDevelop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedValue2 = "develop";
                mDevelop.setTextColor(Color.parseColor("#65bafb"));
                mInnovation.setTextColor(Color.parseColor("#000000"));
            }
        });

    }

    private void sendGraphicRequest(String make, String update) {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        String url;
        final String success;

        if (flag) {
            url = update;
            map.put("project_id", projectId);
            Log.e("ffgedsfd", url);
            success = "تمت التعديل نجاح قيد المراجعة من الادارة";
        } else {
            url = make;
            Log.e("ffgedsfd", url);
            success = "تمت الاضافة نجاح قيد المراجعة من الادارة";
        }
        map.put("token", ConstantInterFace.USER.getToken());
        //map.put("name", mMotionType.getText().toString());
        map.put("name", mProjectNameGh.getText().toString());
        //map.put("about", mAboutActivity.getText().toString());
        map.put("newp", savedValue1);
        map.put("d_type", savedValue2);
        map.put("balance", String.valueOf(st_balance));
        map.put("descr", mProjectDeitailsGh.getText().toString());

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/" + url, map, attachMap, new OkHttpCallback() {
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
                JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Toast.makeText(getActivity(), success, Toast.LENGTH_SHORT).show();
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

    private void setSpinner() {
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

    private void setBalance(int st_balance) {
        this.st_balance = st_balance;
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
        }
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//                    bitmaps.add(bitmap);
//                    adapter = new ProjectPhotoAdapter(getContext(),R.layout.layout_item_photos,bitmaps,true);
                    rec_P.setAdapter(adapter);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 2) {
            Uri selectedImage = data.getData();
            try {
                String filePath = PathUtil.getPath(getActivity(), selectedImage);
                Log.e("dd", " " + filePath);
                bStrings.add(filePath);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                bitmaps.add(bitmap);
                adapter.notifyDataSetChanged();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
