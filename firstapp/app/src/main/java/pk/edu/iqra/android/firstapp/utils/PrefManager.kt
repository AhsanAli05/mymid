package pk.edu.iqra.android.firstapp.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context:Context) {
    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences("app_pref",Context.MODE_PRIVATE)
    }

    fun getData(key:String):String{
        return preferences.getString(key,"")?:""
    }

    fun saveData(key:String, value:String){
        preferences.edit().putString(key,value).apply()
    }

    fun containData(key:String) = preferences.contains(key)

    fun clearData(){
        preferences.edit().clear().apply()
    }
}