package com.insecureshop.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object Prefs {

    lateinit var sharedpreferences: SharedPreferences
    var prefs : Prefs? = null

    fun getInstance(context: Context): Prefs {
        if (prefs == null) {
            val masterKeyAlias = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            sharedpreferences = EncryptedSharedPreferences.create(
                context,
                "Prefs",
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            prefs = this
        }
        return prefs!!
    }

    var data: String?
        get() = sharedpreferences.getString("data","")
        set(value) {
            sharedpreferences.edit().putString("data", value).apply()
        }

    var username: String?
        get() = sharedpreferences.getString("username","")
        set(value) {
            sharedpreferences.edit().putString("username", value).apply()
        }

    var password: String?
        get() = sharedpreferences.getString("password","")
        set(value) {
            sharedpreferences.edit().putString("password", value).apply()
        }

    var productList: String?
        get() = sharedpreferences.getString("productList","")
        set(value) {
            sharedpreferences.edit().putString("productList", value).apply()
        }


    fun clearAll(){
        sharedpreferences.edit().clear().apply()
    }
}