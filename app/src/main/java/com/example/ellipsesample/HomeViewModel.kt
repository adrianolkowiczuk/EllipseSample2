package com.example.ellipsesample


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ellipsecounter.repository.CounterRepository
import com.example.ellipsesample.datasource.CounterDataSource
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import com.tomcz.ellipse.common.toNoAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


typealias HomeProcessor = Processor<HomeEvents, HomeState, Any>

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: CounterRepository
) : ViewModel(){

    val processor: HomeProcessor = processor(HomeState()) { event ->
        when(event){
            is HomeEvents.ChangeNumber -> flow{
                repository.saveNumber(event.number)
                val lastNum = repository.getLastNumber()
                emit(HomePartialState.ChangeNumber(lastNum.first() + 1))
            }
        }
    }

}

