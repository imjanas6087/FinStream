package com.example.finstream.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finstream.data.DashBoardDataBO
import com.example.finstream.repository.CoinRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModuleCoinList @Inject constructor(
    private val repository: CoinRepo
) : ViewModel() {

    private val _coinListDetails =
        MutableStateFlow<NetworkResponse<DashBoardDataBO>>(NetworkResponse.Idle)
    val coinListDetails: StateFlow<NetworkResponse<DashBoardDataBO>> =
        _coinListDetails.asStateFlow()

    init {
        getTheCoinDetails()
    }

    fun getTheCoinDetails() {
        viewModelScope.launch {
            _coinListDetails.value = NetworkResponse.Loading

            try {
                val response = repository.getCoinRepo()

                _coinListDetails.value = if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResponse.Success(body)
                    } else {
                        NetworkResponse.Error("Empty response from server")
                    }
                } else {
                    NetworkResponse.Error("${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _coinListDetails.value =
                    NetworkResponse.Error(e.message ?: "Failed to connect")
            }
        }
    }
}

sealed class NetworkResponse<out T> {
    object Loading : NetworkResponse<Nothing>()
    object Idle : NetworkResponse<Nothing>()
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val message: String) : NetworkResponse<Nothing>()
}
