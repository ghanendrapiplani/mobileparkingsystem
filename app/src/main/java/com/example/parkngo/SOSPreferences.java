package com.example.parkngo;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SOSPreferences {

    private static final String DBNAME = "Demo";
    private static SharedPreferences sharedPreferences;
    private Context context;
    private static SharedPreferences.Editor spEditor;
    private final String LOCATN = "location";
    private final String LOCLAT = "lat";
    private final String LOCLONG = "long";
    private final String USRUID = "user uid";

    private static SOSPreferences instance;

    public static SOSPreferences getInstance(Context context){
        if(instance == null){
            instance = new SOSPreferences(context);
        }
        return instance;
    }

    public SOSPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(DBNAME,Context.MODE_PRIVATE);
    }

    public void setLocatn(String fontsizevalue) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(LOCATN, fontsizevalue);
        spEditor.commit();
    }
    public void setLocatnlat(String fontsizevalue) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(LOCLAT, fontsizevalue);
        spEditor.commit();
    }
    public void setLocatnlong(String fontsizevalue) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(LOCLONG, fontsizevalue);
        spEditor.commit();
    }
    public void setUsrUid(String fontsizevalue) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(USRUID, fontsizevalue);
        spEditor.commit();
    }
    public String UserUid(){
        return sharedPreferences.getString(LOCLAT,"");
    }
    public String LocatnLat(){
        return sharedPreferences.getString(LOCLAT,"");
    }
    public String LocatnLong(){
        return sharedPreferences.getString(LOCLONG,"");
    }

    public String Locatn(){
        return sharedPreferences.getString(LOCATN,"");
    }
}

