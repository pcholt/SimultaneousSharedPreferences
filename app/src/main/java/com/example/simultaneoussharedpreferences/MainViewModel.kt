package com.example.simultaneoussharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class MainViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {

    private val preferences = buildList {
        repeat(200) {
            add(prefs(context))
        }
    }

    var output by mutableStateOf("")

    private val masterKey
        get() =
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

    fun trigger() {
        preferences.forEachIndexed { index, sharedPreferences ->
            sharedPreferences.edit()
                .putString("key", "r$index")
                .apply()
        }
        output = ""
        preferences.forEach { sharedPreferences ->
            output += sharedPreferences.getString("key", null)
        }
    }

    private fun prefs(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
}