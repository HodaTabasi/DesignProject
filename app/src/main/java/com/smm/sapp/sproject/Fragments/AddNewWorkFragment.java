package com.smm.sapp.sproject.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.PathUtil;
//import com.smm.sapp.sproject.Manifest;
import com.smm.sapp.sproject.Models.PWorks;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.MySpinnerAdapter;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


public class AddNewWorkFragment extends Fragment {
    TextView tv_save, tv_photo, title, tv_update, tv_date;
    ImageView ic_back, work_img;
    EditText et_title, et_bio, et_link;
    Spinner sp_specialization;
    String filePath, st_specialization;
    Bundle bundle;
    PWorks models;


    public AddNewWorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_work, container, false);
        ic_back = view.findViewById(R.id.ic_back);
        tv_save = view.findViewById(R.id.tv_save);
        et_title = view.findViewById(R.id.et_title);
        et_bio = view.findViewById(R.id.et_bio);
        tv_date = view.findViewById(R.id.tv_date);
        tv_photo = view.findViewById(R.id.tv_photo);
        et_link = view.findViewById(R.id.et_link);
        work_img = view.findViewById(R.id.work_img);
        title = view.findViewById(R.id.title1);
        sp_specialization = view.findViewById(R.id.sp_specialization);
        tv_update = view.findViewById(R.id.tv_update);

        setSpinner();

        return view;
    }

    private void setSpinner() {
        MySpinnerAdapter adapter1 = new MySpinnerAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.spinner_items))
        );
        sp_specialization.setAdapter(adapter1);
        sp_specialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        getSpecialization("inter");
                        break;
                    case 1:
                        getSpecialization("arch");
                        break;
                    case 2:
                        getSpecialization("graphic");
                        break;
                    case 3:
                        getSpecialization("wall");
                        break;
                    case 4:
                        getSpecialization("moshen");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getSpecialization(String st_specialization) {
        this.st_specialization = st_specialization;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        //update action
        bundle = getArguments();
        if (bundle != null && bundle.containsKey("work")) {
            tv_save.setVisibility(View.GONE);
            tv_update.setVisibility(View.VISIBLE);
            getData(bundle);
        }

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        startActivityForResult(pickPhoto, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();

                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE");
                        SimpleDateFormat simpledateformat1 = new SimpleDateFormat("MM");
                        Date date = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()-1);
//                        String dayOfWeek = simpledateformat.format(date);
                        String month = simpledateformat1.format(date);
                        int d = datePicker.getDayOfMonth();
                        int y = datePicker.getYear();
                        tv_date.setText(d+"/"+month+"/"+y);
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

                pickerDialog.show();
            }
        });

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_title.getText().toString().matches("")
                        || et_bio.getText().toString().matches("")
                        || tv_date.getText().toString().matches("")
                        || et_link.getText().toString().matches("")
                        || work_img.getDrawable() == null
                        || st_specialization.equals("")) {
                    Toast.makeText(getActivity(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                } else {
                    Log.e("dddd","dfadaw1");
                    sendRequest();
                }
            }
        });
    }

    private void sendRequest() {
        Log.e("dddd","dfadaw");
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        String s_title = et_title.getText().toString();
        String s_bio = et_bio.getText().toString();
        String s_date = tv_date.getText().toString();
        String s_link = et_link.getText().toString();

        map.put("token", ConstantInterFace.USER.getToken());
        map.put("name", s_title);
        map.put("descr", s_bio);
        map.put("mdate", s_date);
        map.put("work_link", s_link);
        map.put("type", st_specialization);
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/pwork", map, filePath, "photo_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();

                final JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject statusobj = jsonObject.getJSONObject("status");
                String success = statusobj.getString("success");
                final String message = statusobj.getString("message");


                Log.e("pppp",filePath);
                if (success.equals("true")) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            et_title.setText("");
                            et_bio.setText("");
                            et_link.setText("");
                            tv_date.setText("");
                            work_img.setImageResource(0);
                            Toast.makeText(getActivity(), "تم الاضافة بنجاح", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "لم يتم الاضافة", Toast.LENGTH_LONG).show();
                            }
                    });
                }


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 1);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    filePath = PathUtil.getPath(getActivity(), selectedImage);
                    File imgFile = new File(filePath);
                    if (imgFile.exists()) {
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        work_img.setImageBitmap(myBitmap);
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void getData(Bundle bundle) {
        models = bundle.getParcelable("work");
        title.setText("تعديل العمل");
        et_title.setText(models.getName());
        et_bio.setText(models.getDescr());
        tv_date.setText(models.getMdate());
        et_link.setText(models.getWork_link());

        if (models.getType().equals("inter")) {
            sp_specialization.setSelection(0);
        } else if (models.getType().equals("arch")) {
            sp_specialization.setSelection(1);
        } else if (models.getType().equals("graphic")) {
            sp_specialization.setSelection(2);
        } else if (models.getType().equals("wall")) {
            sp_specialization.setSelection(3);
        } else if (models.getType().equals("moshen")) {
            sp_specialization.setSelection(4);
        }

        Picasso.get().load(models.getPhoto_link()).into(work_img);

        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_title.getText().toString().equals(models.getName())
                        && et_bio.getText().toString().equals(models.getDescr())
                        && tv_date.getText().toString().equals(models.getMdate())
                        && et_link.getText().toString().equals(models.getWork_link())
//                        && filePath.equals(models.getPhoto_link())
                        && sp_specialization.getSelectedItem().toString().equals(models.getType())) {
                    Toast.makeText(getContext(), "لا يوجد تعديلات جديدة", Toast.LENGTH_LONG).show();

                } else {

                    MyProgressDialog.showDialog(getContext());
                    MyRequest myRequest = new MyRequest();
                    Map<String, String> map = new HashMap<>();
                    String s_title = et_title.getText().toString();
                    String s_bio = et_bio.getText().toString();
                    String s_date = tv_date.getText().toString();
                    String s_link = et_link.getText().toString();
                    map.put("token", ConstantInterFace.USER.getToken());
                    map.put("name", s_title);
                    map.put("descr", s_bio);
                    map.put("mdate", s_date);
                    map.put("work_link", s_link);
                    map.put("type", st_specialization);
                    map.put("pwork_id", String.valueOf(models.getId()));

                    myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/updateapwork", map, filePath, "photo_link", new OkHttpCallback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            MyProgressDialog.dismissDialog();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException, JSONException {
                            MyProgressDialog.dismissDialog();

                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONObject statusobj = jsonObject.getJSONObject("status");
                            String success = statusobj.getString("success");

                            if (success.equals("true")) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        et_title.setText("");
                                        et_bio.setText("");
                                        et_link.setText("");
                                        tv_date.setText("");
                                        work_img.setImageResource(0);
                                        Toast.makeText(getActivity(), "تم التعديل بنجاح", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "لم يتم التعديل", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }


                        }
                    });
                }
            }
        });

    }

}
