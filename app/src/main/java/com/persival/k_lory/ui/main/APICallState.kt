package com.persival.k_lory.ui.main

sealed class APICallState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : APICallState<T>(data)
    class Loading<T>(data: T? = null) : APICallState<T>(data)
    class Error<T>(message: String, data: T? = null) : APICallState<T>(data, message)
}
