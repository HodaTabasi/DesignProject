package com.smm.sapp.sproject.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.obsez.android.lib.filechooser.ChooserDialog;
import com.smm.sapp.sproject.Adapters.MyMessageDetailAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.PathUtil;
import com.smm.sapp.sproject.Models.MessageDetails;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageDitailsFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout mSwipeRefreshLayout;
    String userId;
    private View view;
    private RecyclerView mMessageDetails;
    private ImageView mOther;
    private EditText mMessageEx;
    private ImageView mSendMessg;
    private List<MessageDetails> details;
    MyMessageDetailAdapter adapter;
    ImageView ic_back;
    private String filePath, fileName;
    TextView attchs_name;
    int current_page, total_pages, flag;
    int w,h;

    public MessageDitailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_ditails, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        Bundle bundle = getArguments();
        userId = bundle.getString("userId");
        Log.e("ff", userId);
        initView(getView());

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                getAConversationRequest(1);
            }
        });

        ic_back = getView().findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeRefreshLayout.setRefreshing(true);
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.send_messg:
                sendNewMessageRequest();
                break;
            case R.id.other:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
                break;
        }
    }

    private void fileBrowse() {
        new ChooserDialog().with(getContext())
                .withFilter(false, false, "jpg", "jpeg", "png", "gif", "pdf", "docx", "xlsx", "txt")
                .withStartFile(Environment.getExternalStorageDirectory().getPath())
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        Toast.makeText(getContext(), "FOLDER: " + path, Toast.LENGTH_SHORT).show();
                        filePath = path;
                        fileName = pathFile.getName();
                        attchs_name.setText(pathFile.getName());
                        attchs_name.setVisibility(View.VISIBLE);
                    }
                })
                .build()
                .show();
    }

    private void getAConversationRequest(int current) {
        MyRequest myRequest = new MyRequest();
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("user_id", userId);
        stringMap.put("i_current_page", String.valueOf(current));

        myRequest.PostCall("http://smm.smmim.com/waell/public/api/getAconversation", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");
                final JSONObject paginationObj = object.getJSONObject("pagination");


                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                TypeToken<List<MessageDetails>> token = new TypeToken<List<MessageDetails>>() {};
                                current_page = Integer.valueOf(paginationObj.getString("i_current_page"));
                                total_pages = Integer.valueOf(paginationObj.getString("i_total_pages"));
                                if (current_page <= 1){
                                    details = gson.fromJson(object.getJSONArray("msgs").toString(), token.getType());
                                    adapter = new MyMessageDetailAdapter(getContext(), details);
                                    mMessageDetails.setAdapter(adapter);
                                }else {
                                    List<MessageDetails> messageDetails = gson.fromJson(object.getJSONArray("msgs").toString(), token.getType());
                                    details.addAll(messageDetails);
                                    adapter.notifyDataSetChanged();
                                }

                                mSwipeRefreshLayout.setRefreshing(false);



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void initView(View view) {
        mMessageDetails = view.findViewById(R.id.message_details);
        mMessageDetails.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        mOther = view.findViewById(R.id.other);
        mMessageEx = view.findViewById(R.id.message_ex);
        mSendMessg = view.findViewById(R.id.send_messg);
        attchs_name = view.findViewById(R.id.attchs_name);

        mSendMessg.setOnClickListener(this);
        mOther.setOnClickListener(this);

        mSwipeRefreshLayout = getView().findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

    }

    private void sendNewMessageRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("msg", mMessageEx.getText().toString());
        stringMap.put("seconed_id", userId);
        stringMap.put("width", String.valueOf(w));
        stringMap.put("height", String.valueOf(h));

        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/sendmsg", stringMap, filePath, "file_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("115558676", e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                String s = response.body().string();
                final JSONObject object = new JSONObject(s);
                final JSONObject object1 = object.getJSONObject("status");
                Log.e("ddf", s);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                MessageDetails messageDetails = gson.fromJson(object.getJSONObject("msg").toString(), MessageDetails.class);
                                details.add(0, messageDetails);
                                adapter.notifyDataSetChanged();
                                mMessageEx.setText("");
                                attchs_name.setVisibility(View.GONE);
                            } else {
                                JSONObject object2 = object1.getJSONObject("error");
                                JSONArray object3 = object2.getJSONArray("msg");
                                if (object3.get(0).equals("The msg field is required.")) {
                                    Toast.makeText(getContext(), "يجب ارسال نص مع المرفقات", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(getContext(), "لم يتم الارسال", Toast.LENGTH_SHORT).show();
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
    public void onRefresh() {
        if (total_pages == current_page && current_page != 1){
            mSwipeRefreshLayout.setRefreshing(false);
        }else {
            getAConversationRequest(current_page + 1);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    filePath = PathUtil.getPath(getActivity(), selectedImage);
                    //fileName = pathFile.getName();
                    attchs_name.setText(filePath);
                    attchs_name.setVisibility(View.VISIBLE);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    w = bitmap.getWidth();
                    h = bitmap.getHeight();

                   // adapter.notifyDataSetChanged();
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
}
