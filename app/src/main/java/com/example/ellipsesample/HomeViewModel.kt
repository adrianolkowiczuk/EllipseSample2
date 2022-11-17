package com.example.ellipsesample


import androidx.lifecycle.ViewModel
import com.example.ellipsesample.repository.CounterRepository
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import com.tomcz.ellipse.common.toNoAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


typealias HomeProcessor = Processor<HomeEvents, HomeState, Any>

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: CounterRepository
) : ViewModel() {

    val processor: HomeProcessor = processor(
        initialState = HomeState(),
        prepare = { repository.observeLastNumber().map { HomePartialState.ChangeNumber(it) } }, onEvent = { event ->
            when (event) {
                is HomeEvents.ChangeNumber -> {
                    val lastNum = repository.observeLastNumber().first()
                    repository.saveNumber(lastNum + 1).toNoAction()
                }
            }
        })
}
