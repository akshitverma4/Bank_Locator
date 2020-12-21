package com.wednesday.banklocator.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.wednesday.banklocator.R
import com.wednesday.banklocator.db.FavouritesBankDatabase
import com.wednesday.banklocator.repository.BankDetailsRepository
import com.wednesday.banklocator.viewmodel.BankViewModel
import com.wednesday.banklocator.viewmodel.BankViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: BankViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        val newsRepository = BankDetailsRepository(FavouritesBankDatabase(this))
        val viewModelProviderFactory = BankViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(BankViewModel::class.java)
    }
}
