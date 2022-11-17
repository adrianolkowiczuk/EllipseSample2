package com.example.ellipsesample.repository

import com.example.ellipsesample.datasource.CounterDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterRepository @Inject constructor(
    private val counterDataSource: CounterDataSourceImpl
) {
    suspend fun observeLastNumber(): Flow<Int> {
        return counterDataSource.readLastNumber()
    }

    suspend fun saveNumber(number: Int) {
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