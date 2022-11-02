package com.example.ellipsesample

import com.tomcz.ellipse.PartialState

sealed interface HomePartialState: PartialState<HomeState> {

    class ChangeNumber(private val num: Int) : HomePartialState {
        override fun reduce(oldState: HomeState): HomeState {
            return oldState.copy(currNumber = num)
        }
    }

}