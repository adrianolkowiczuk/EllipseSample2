package com.example.ellipsecounter.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.ellipsesample.datasource.CounterDataSource
import com.example.ellipsesample.datasource.ICounterDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CounterRepository @Inject constructor(
    private val counterDataSource: CounterDataSource
) {
    suspend fun getLastNumber(): Flow<Int> {
        return counterDataSource.readLastNumber()
    }

    suspend fun saveNumber(number: Int){
        counterDataSource.saveNumber(number)
    }

}


//class CounterRepository (
//    private val counterDataSource: ICounterDataSource,
//    private val ioDispatcher: CoroutineDispatcher,
//) {
//    suspend fun getLastNumber(): Flow<Int?> =
//        withContext(ioDispatcher) {
//            counterDataSource.readLastNumber()
//        }
//
//    suspend fun saveNumber(number: Int){
//        withContext(ioDispatcher){
//            counterDataSource.saveNumber(number)
//        }
//    }
//}