package com.example.maryam.sproject.HelperClass;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;


import com.example.maryam.sproject.R;

/**
 * Created by M.S.I on 4/3/2018.
 */

public class MyProgressDialog {
    private static ProgressDialog progressDialog;


    public static void showDialog(Context context){
        progressDialog = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public static void showAuthDialog(Context context){
        progressDialog = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    public static void showReamDataDialog(Context context){
        progressDialog = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
    }

    public static void showAlertDialog(Context context){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context).setMessage("تم اضافة الخطة بنجاح");
        final AlertDialog alert = dialog.create();
        alert.show();

// Hide after some seconds
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 1000);
    }

    public static void dismissDialog(){
        progressDialog.dismiss();
    }

}
