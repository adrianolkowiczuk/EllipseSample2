package com.example.ellipsesample

sealed class HomeEvents {
    data class ChangeNumber(val number: Int): HomeEvents()
}