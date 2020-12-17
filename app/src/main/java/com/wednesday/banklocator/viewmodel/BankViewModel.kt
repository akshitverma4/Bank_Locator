package com.wednesday.banklocator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wednesday.banklocator.model.IfscResponse
import com.wednesday.banklocator.repository.NewsRepository
import com.wednesday.banklocator.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class BankViewModel(val newsRepository: NewsRepository
) : ViewModel() {
    val ifscCodes: MutableLiveData<Resource<IfscResponse>> = MutableLiveData()

    fun getIfscCode(IFSC: String) = viewModelScope.launch {
        ifscCodes.postValue(Resource.Loading())
        val response = newsRepository.getIfscCode(IFSC)
        ifscCodes.postValue(handleIfscCodeResponse(response))

    }

    fun handleIfscCodeResponse(response: Response<IfscResponse>): Resource<IfscResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}