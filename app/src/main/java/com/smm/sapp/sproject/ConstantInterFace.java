package com.smm.sapp.sproject;

import android.widget.TextView;

import com.smm.sapp.sproject.Models.NotificationsModels;
import com.smm.sapp.sproject.Models.User;

import java.util.ArrayList;

public class ConstantInterFace {
    public static final String MY_PREFS_NAME = "notificationData";
    public static User USER;
    public static Boolean IS_REGISTER = false;
    public static TextView tv_msgs, tv_projects, tv_home, tv_portfolio, tv_profile;
    public static Boolean IS_WORK_FAVORITE = false;


    public static Boolean IS_ABOUT_OPENED = false;
    public static Boolean IS_QUES_OPENED = false;
    public static Boolean IS_RIGHTS_OPENED = false;
    public static Boolean IS_CONDITIONS_OPENED = false;
    public static Boolean IS_CALLUS_OPENED = false;

    public static Boolean IS_PROPOSAL_OPENED = false;

    public static Boolean IS_USER_COMPLETEED = false;


    public static final String CHANNEL_ID = "my_channel_01";
    public static final String CHANNEL_NAME = "Simplified Coding Notification";
    public static final String CHANNEL_DESCRIPTION = "www.simplifiedcoding.ne";

    public static int NOTIFICATION_NUMBER = 0;
    public static ArrayList<NotificationsModels> notificationsModels = null;

    public static int DELEIVER_PROJECT = 0;
    public static int DELEIVER_CLIENT_PROJECT = 0;
    public static String[] array = new String[]{"","500-1000", "1000-2000", "2000-3000", "3000-4000", "4000-5000", "5000-6500", "6500-8000", "اكثر من 8000"};

}
