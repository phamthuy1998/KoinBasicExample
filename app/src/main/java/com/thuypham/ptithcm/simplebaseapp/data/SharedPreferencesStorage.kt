package com.thuypham.ptithcm.simplebaseapp.data

import android.content.Context

class SharedPreferencesStorage constructor(context: Context) : IStorage {

    private val sharedPreferences = context.getSharedPreferences(
        APP_PREF,
        Context.MODE_PRIVATE
    )

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String, def: String?) = sharedPreferences.getString(key, def)

    override fun setBoolean(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    override fun getBoolean(key: String, def: Boolean) = sharedPreferences.getBoolean(key, def)

    override fun setInt(key: String, value: Int) {
        with(sharedPreferences.edit()) {
            putInt(key, value)
            apply()
        }
    }

    override fun getInt(key: String, def: Int) = sharedPreferences.getInt(key, def)

    override fun setLong(key: String, value: Long) {
        with(sharedPreferences.edit()) {
            putLong(key, value)
            apply()
        }
    }

    override fun getLong(key: String, def: Long) = sharedPreferences.getLong(key, def)

    override fun setStringSet(key: String, set: Set<String>) {
        with(sharedPreferences.edit()) {
            putStringSet(key, set)
            apply()
        }
    }

    override fun getStringSet(key: String) =
        sharedPreferences.getStringSet(key, emptySet()) as Set<String>

    companion object {
        const val APP_PREF = "invoice_maker_pref"
        const val PREF_UID = "pref_uid"
        const val PREF_RUN_FIRST_TIME = "pref_run_first_time"
        const val PREF_CHAT_RESTORE_ID  = "pref_chat_id"
        const val PREF_PROTOCOL_LIGHT_CONNECTION = "pref_protocol_light_connection"
        // DEFINE PREFERENCE PARAMS
    }
}

interface IStorage {
    fun setString(key: String, value: String)
    fun getString(key: String, def: String? = null): String?
    fun setBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, def: Boolean): Boolean
    fun setInt(key: String, value: Int)
    fun getInt(key: String, def: Int = -1): Int
    fun setLong(key: String, value: Long)
    fun getLong(key: String, def: Long = -1L): Long
    fun setStringSet(key: String, set: Set<String>)
    fun getStringSet(key: String): Set<String>
}