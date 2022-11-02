package com.example.ellipsesample.datasource

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.*
import javax.inject.Inject



class CounterDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ICounterDataSource{
    override suspend fun readLastNumber(): Flow<Int> = dataStore.data.map {
        it[COUNTER] ?: 0
    }

//    val latestNumber: Flow<Int> = flow {
//        while(true) {
//            val latestNews = dataStore.data.first()[COUNTER] ?: 0
//            emit(latestNews)
//        }
//    }

    override suspend fun saveNumber(number: Int) {
        dataStore.edit {
            it[COUNTER] = number
        }
    }
        companion object PreferencesKeys {
        val COUNTER = intPreferencesKey("counter")
    }
}


interface ICounterDataSource {
    suspend fun readLastNumber(): Flow<Int>
    suspend fun saveNumber(number: Int)
}


