package com.smm.sapp.sproject.HelperClass;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Models.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by M.S.I on 4/12/2018.
 */

public class SharedPreferencesApp {
    public static SharedPreferencesApp sharedPreferencesApp;
    private SharedPreferences prefs;
    public SharedPreferencesApp(Context context) {
        prefs = context.getSharedPreferences(ConstantInterFace.MY_PREFS_NAME, MODE_PRIVATE);
    }

    public static SharedPreferencesApp getInstance(Context context){
        if(sharedPreferencesApp == null){
            sharedPreferencesApp = new SharedPreferencesApp(context);
        }
        return sharedPreferencesApp;
    }

    public void saveData(String key ,String Data){
       SharedPreferences.Editor editor = prefs.edit();
       editor.putString(key,Data);
        editor.apply();
    }

    public void saveObject(User user) {
        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    public String getStringData(String key){
        return prefs.getString(key,"");
    }


    public void clearData(){
        SharedPreferences.Editor editor =prefs.edit();
        editor.clear();
        editor.apply();
    }
}
