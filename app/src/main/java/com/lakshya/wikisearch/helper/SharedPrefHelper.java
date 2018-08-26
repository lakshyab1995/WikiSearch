package com.lakshya.wikisearch.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {

    /**
     * SharedPreference file name to store the preferences
     */
    private static final String PREFS_NAME = "wiki_list";

    private Context mContext;

    public static SharedPrefHelper with(Context context){
        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper();
        sharedPrefHelper.mContext = context;
        return sharedPrefHelper;
    }

    /**
     * To add a String value against the key to the {@link SharedPreferences}
     *
     * @param key
     * @param value
     */
    public void add(String key, String value){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * To remove a particular key which is added to the {@link SharedPreferences}
     *
     * @param key
     */
    public void remove(String key){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * To get the preference value for a key
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String get(String key, String defaultValue){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    public void clear(){
        SharedPreferences.Editor editor = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }


}
