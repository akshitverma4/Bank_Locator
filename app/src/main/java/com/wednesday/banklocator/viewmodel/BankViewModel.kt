package com.wednesday.banklocator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wednesday.banklocator.model.Ifsc
import com.wednesday.banklocator.repository.BankDetailsRepository
import com.wednesday.banklocator.util.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class BankViewModel(val bankDetailsRepository: BankDetailsRepository
) : ViewModel() {

val ifscCodes: MutableLiveData<Resource<Ifsc>> = MutableLiveData()

fun getIfscCode(Ifsc: String) = viewModelScope.launch {
    ifscCodes.postValue(Resource.Loading())
    val response = bankDetailsRepository.getIfscCode(Ifsc)
    ifscCodes.postValue(handleIfscCodeResponse(response))

}
fun upsert(item: Ifsc) =
    GlobalScope.launch {
        bankDetailsRepository.upsert(item)
    }

fun delete(item: Ifsc) = GlobalScope.launch {
    bankDetailsRepository.delete(item)
}

fun getAllShoppingItems() =  bankDetailsRepository.getAllBanks()

fun handleIfscCodeResponse(response: Response<Ifsc>): Resource<Ifsc> {
    if (response.isSuccessful) {
        response.body()?.let { resultResponse ->
            return Resource.Success(resultResponse)
        }
    }
    return Resource.Error(response.message())
}
}
