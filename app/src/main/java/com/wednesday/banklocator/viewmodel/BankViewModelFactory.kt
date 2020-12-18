package com.wednesday.banklocator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wednesday.banklocator.repository.BankDetailsRepository

class BankViewModelFactory(val bankDetailsRepository: BankDetailsRepository):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return BankViewModel(bankDetailsRepository) as T
    }
}