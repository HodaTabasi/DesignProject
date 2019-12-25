package com.smm.sapp.sproject.HelperClass;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by iSaleem on 8/3/17.
 */

public class FragmentsUtil {

    public static void addFragment(FragmentActivity activity, int layout, Fragment fragment){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void addFragment(FragmentActivity activity, int layout, Fragment fragment , boolean addToBackStack){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(layout,fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void replaceFragment(FragmentActivity activity, int layout, Fragment fragment){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layout,fragment);
        transaction.commit();

    }

    public static void replaceFragment(FragmentActivity activity, int layout, Fragment fragment , boolean addToBackStack){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layout,fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }
    public static void removeFragment(FragmentActivity activity, Fragment fragment){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();

    }

}
