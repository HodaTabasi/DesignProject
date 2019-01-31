package com.smm.sapp.sproject.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.Activities.WebViewActivity;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.PathUtil;
import com.smm.sapp.sproject.HelperClass.SharedPreferencesApp;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShippingBalanceFragment extends Fragment implements View.OnClickListener {

    /**
     * تحويل بنكي
     */
    private TextView mBankShTransfer;
    /**
     * بطاقة ائتمانية
     */
    private TextView mCreditShCard;
    /**
     * التحويل البنكي
     */
    private TextView mTitle;
    /**
     * ارسال
     */
    private TextView mSendBank;
    /**
     * ارسال
     */
    TextView tv, tv1;
    ImageButton send_bank1;
    private TextView mSendBank1;
    RelativeLayout one, two;
    TextView addPhotoShp, transferDateSh;
    EditText userBankNameShp, bankNumberShp, bankNameShp, balance, et_amount;
    ImageView ic_back;
    String filePath, st_amount;

    public ShippingBalanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shipping_balance, container, false);
    }

    private void initView() {
        mBankShTransfer = getView().findViewById(R.id.bank_sh_transfer);
        mCreditShCard = getView().findViewById(R.id.credit_sh_card);
        mSendBank = getView().findViewById(R.id.send_bank);
        mSendBank1 = getView().findViewById(R.id.send_bank1);
        userBankNameShp = getView().findViewById(R.id.user_bank_name_shp);
        bankNameShp = getView().findViewById(R.id.bank_name_shp);
        bankNumberShp = getView().findViewById(R.id.bank_number_shp);
        balance = getView().findViewById(R.id.balance_shp);
        transferDateSh = getView().findViewById(R.id.transfer_date_sh);
        addPhotoShp = getView().findViewById(R.id.add_photo_shp);
        one = getView().findViewById(R.id.sh_one);
        two = getView().findViewById(R.id.sh_two);
        et_amount = getView().findViewById(R.id.et_amount);
    }

    private void addListeners() {
        mBankShTransfer.setOnClickListener(this);
        mCreditShCard.setOnClickListener(this);
        mSendBank.setOnClickListener(this);
        mSendBank1.setOnClickListener(this);
        addPhotoShp.setOnClickListener(this);
        transferDateSh.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        addListeners();
        transferDateSh.setEnabled(true);

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
        switch (id) {
            case R.id.bank_sh_transfer:
                mBankShTransfer.setBackgroundResource(R.drawable.dark_blue_shap);
                mCreditShCard.setBackgroundResource(R.drawable.gray_shap);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.GONE);
                break;
            case R.id.credit_sh_card:
                mBankShTransfer.setBackgroundResource(R.drawable.gray_shap);
                mCreditShCard.setBackgroundResource(R.drawable.dark_blue_shap);
                one.setVisibility(View.GONE);
                two.setVisibility(View.VISIBLE);
                break;
            case R.id.send_bank:
                if (userBankNameShp.getText().toString().matches("")
                        || bankNumberShp.getText().toString().matches("")
                        || bankNameShp.getText().toString().matches("")
                        || balance.getText().toString().matches("")
                        || transferDateSh.getText().toString().matches("")
                        || addPhotoShp.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                    break;

                } else {
                    sendCardBank();
                    break;
                }

            case R.id.send_bank1:
                sendShippingRequest();
                break;
            case R.id.add_photo_shp:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
                break;
            case R.id.transfer_date_sh:
                Calendar c = Calendar.getInstance();

                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE");
                        SimpleDateFormat simpledateformat1 = new SimpleDateFormat("MM");
                        Date date = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth() - 1);
//                        String dayOfWeek = simpledateformat.format(date);
                        String month = simpledateformat1.format(date);
                        int d = datePicker.getDayOfMonth();
                        int y = datePicker.getYear();
                        transferDateSh.setText(d + "/" + month + "/" + y);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                pickerDialog.show();
                break;
        }
    }

    private void sendShippingRequest() {
        MyProgressDialog.showDialog(getContext());
        st_amount = et_amount.getText().toString();
        MyRequest myRequest = new MyRequest();
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("amount", st_amount);
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/sendpayment", stringMap, new OkHttpCallback() {
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
                final JSONObject statusObj = jsonObject.getJSONObject("status");
                final String urlString = jsonObject.getString("url");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (statusObj.getBoolean("success")) {

                                Intent intent = new Intent(getContext(), WebViewActivity.class);
                                intent.putExtra("url", urlString);
                                startActivity(intent);

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

    private void sendCardBank() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("name", userBankNameShp.getText().toString());
        stringMap.put("acc_number", bankNumberShp.getText().toString());
        stringMap.put("bank_name", bankNameShp.getText().toString());
        stringMap.put("total", balance.getText().toString());
        stringMap.put("date", transferDateSh.getText().toString());
        myRequest.PostCallWithAttachment("http://smm.smmim.com/waell/public/api/chargemycredit", stringMap, filePath, "photo_link", new OkHttpCallback() {
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
                Log.e("cdsfsdkfheofj",jsonObject.toString() + " بليبسب");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                MyProgressDialog.DoneDialog(getContext(), " المبلغ  " + balance.getText().toString(), "تم شحن حسابك ");
                            } else {
                                Toast.makeText(getContext(), "حصل خطا ما ", Toast.LENGTH_SHORT).show();
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
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                try {
                    filePath = PathUtil.getPath(getActivity(), selectedImage);
                    String[] separated = filePath.split("/");
                    addPhotoShp.setText(separated[separated.length - 1]);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
