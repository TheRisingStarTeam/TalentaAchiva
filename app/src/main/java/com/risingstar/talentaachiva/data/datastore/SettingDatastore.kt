package com.risingstar.talentaachiva.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingDatastore private constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val SET_DARK = booleanPreferencesKey("setdark")
    private val SET_OTHER = stringPreferencesKey("setother")

    fun getDark(): Flow<Boolean> {
        return dataStore.data.map {
            it[SET_DARK] ?: false
        }
    }

    suspend fun changeDark() {
        dataStore.edit {
            it[SET_DARK] = true
        }
    }

    suspend fun changeLight(){
        dataStore.edit{
            it[SET_DARK] = false
        }
    }



    companion object {
        @Volatile
        private var INSTANCE: SettingDatastore? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingDatastore {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingDatastore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}