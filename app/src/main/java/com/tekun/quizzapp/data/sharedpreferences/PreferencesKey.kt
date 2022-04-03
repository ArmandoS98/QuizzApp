package com.tekun.quizzapp.data.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

enum class PreferencesKey(val value: String) {
    TURNSOUND("turn_sound"),
    LEVEL_VOLUME("level_volume"),
    LAST_WINNER("last_winner"),
    FIRST_TIME("com.aesc.proyectofinaldesarrollomovil")
}

object PreferencesProvider {
    fun set(context: Context, key: PreferencesKey, value: String) {
        val editor = prefs(context).edit()
        editor.putString(key.value, value).apply()
    }

    fun string(context: Context, key: PreferencesKey): String? {
        return prefs(context).getString(key.value, null)
    }

    fun int(context: Context, key: PreferencesKey): Int? {
        return prefs(context).getInt(key.value, 50)
    }
    fun intWinner(context: Context, key: PreferencesKey): Int? {
        return prefs(context).getInt(key.value, 0)
    }

    fun set(context: Context, key: PreferencesKey, value: Boolean) {
        val editor = prefs(context).edit()
        editor.putBoolean(key.value, value).apply()
    }

    fun set(context: Context, key: PreferencesKey, value: Int) {
        val editor = prefs(context).edit()
        editor.putInt(key.value, value).apply()
    }

    fun bool(context: Context, key: PreferencesKey): Boolean {
        return prefs(context).getBoolean(key.value, true)
    }

    fun remove(context: Context, key: PreferencesKey) {
        val editor = prefs(context).edit()
        editor.remove(key.value).apply()
    }

    // Elimina las SharedPreferences del dominio app
    fun clear(context: Context) {
        val editor = prefs(context).edit()
        editor.clear().apply()
    }

    // Private
    private fun prefs(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}
