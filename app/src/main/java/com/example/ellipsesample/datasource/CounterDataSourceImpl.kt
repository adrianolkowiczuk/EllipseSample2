package com.example.ellipsesample.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CounterDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : CounterDataSource {
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


interface CounterDataSource {
    suspend fun readLastNumber(): Flow<Int>
    suspend fun saveNumber(number: Int)
}


