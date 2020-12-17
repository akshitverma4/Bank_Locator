package com.wednesday.banklocator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wednesday.banklocator.repository.NewsRepository

class BankViewModelFactory(val newsRepository: NewsRepository):ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return BankViewModel(newsRepository) as T
    }
}