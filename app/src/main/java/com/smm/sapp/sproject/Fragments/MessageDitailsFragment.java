package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.obsez.android.lib.filechooser.ChooserDialog;
import com.smm.sapp.sproject.Adapters.MyMessageAdapter;
import com.smm.sapp.sproject.Adapters.MyMessageDetailAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.MessageDetails;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageDitailsFragment extends Fragment implements View.OnClickListener{

    String userId;
    private View view;
    private RecyclerView mMessageDetails;
    private ImageView mOther;
    private EditText mMessageEx;
    private ImageView mSendMessg;
    private List<MessageDetails> details;
    MyMessageDetailAdapter adapter;
    ImageView ic_back ;
    private String filePath ,fileName;
    TextView attchs_name;

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
        Log.e("ff",userId);
        initView(getView());
        getAConversationRequest();

        ic_back = getView().findViewById(R.id.ic_back);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.send_messg:
                sendNewMessageRequest();
                break;
            case R.id.other:
                fileBrowse();
                break;
        }
    }

    private void fileBrowse() {
        new ChooserDialog().with(getContext())
                .withFilter(false, false, "jpg", "jpeg", "png","gif", "pdf", "docx", "xlsx","txt")
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

    private void getAConversationRequest(){
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("user_id",userId);
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/getAconversation", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")){
                                Gson gson = new Gson();
                                TypeToken<List<MessageDetails>> token = new TypeToken<List<MessageDetails>>() {};
                                details = gson.fromJson(object.getJSONArray("msgs").toString(),token.getType());
                                adapter = new MyMessageDetailAdapter(getContext(),details);
                                mMessageDetails.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void initView(View view){
        mMessageDetails = view.findViewById(R.id.message_details);
        mMessageDetails.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
        mOther = view.findViewById(R.id.other);
        mMessageEx = view.findViewById(R.id.message_ex);
        mSendMessg = view.findViewById(R.id.send_messg);
        attchs_name = view.findViewById(R.id.attchs_name);

        mSendMessg.setOnClickListener(this);
        mOther.setOnClickListener(this);


    }
    private void sendNewMessageRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("token",ConstantInterFace.USER.getToken());
        stringMap.put("msg",mMessageEx.getText().toString());
        stringMap.put("seconed_id",userId);
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/sendmsg", stringMap, filePath, "file_link", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("115558676",e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                String s = response.body().string();
                final JSONObject object = new JSONObject(s);
                final JSONObject object1 = object.getJSONObject("status");
                Log.e("ddf",s);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")){
                                Gson gson = new Gson();
                                MessageDetails messageDetails = gson.fromJson(object.getJSONObject("msg").toString(),MessageDetails.class);
                                details.add(0,messageDetails);
                                adapter.notifyDataSetChanged();
                                mMessageEx.setText("");
                                attchs_name.setVisibility(View.GONE);
                                Log.e("115558676",object.toString());
                            }else {
                                Toast.makeText(getContext(), "لم يتم الارسال", Toast.LENGTH_SHORT).show();
                                Log.e("115558676",object.toString());
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
