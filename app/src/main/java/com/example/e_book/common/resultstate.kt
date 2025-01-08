package com.example.e_book.common

sealed class resultstate<T> {
    data class Loading<T>(var state: Boolean) : resultstate<T>()
    data class Success<T>(var data: T) : resultstate<T>()
    data class Error<T>(var message: String) : resultstate<T>()



}