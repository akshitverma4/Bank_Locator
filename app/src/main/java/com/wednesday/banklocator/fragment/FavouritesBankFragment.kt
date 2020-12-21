package com.wednesday.banklocator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wednesday.banklocator.R
import com.wednesday.banklocator.adapter.DatabaseAdapter
import com.wednesday.banklocator.db.FavouritesBankDatabase
import com.wednesday.banklocator.repository.BankDetailsRepository
import com.wednesday.banklocator.viewmodel.BankViewModel
import com.wednesday.banklocator.viewmodel.BankViewModelFactory
import kotlinx.android.synthetic.main.fragment_favourites_bank.view.*


class FavouritesBankFragment : Fragment() {
    lateinit var viewModel: BankViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favourites_bank, container, false)

        val newsRepository = activity?.let { FavouritesBankDatabase(it) }?.let { BankDetailsRepository(it) }
        val viewModelProviderFactory = newsRepository?.let { BankViewModelFactory(it) }
        viewModel = viewModelProviderFactory?.let {
            ViewModelProvider(
                this,
                it
            ).get(BankViewModel::class.java)
        }!!
        val newsDatabaseAdapter: DatabaseAdapter by lazy {
            DatabaseAdapter(listOf(), viewModel)
        }
        root.favouriteBanksRecyclerView.adapter = newsDatabaseAdapter
        root.favouriteBanksRecyclerView.layoutManager = LinearLayoutManager(activity)


        viewModel.getAllShoppingItems().observe(requireActivity(), Observer {
            newsDatabaseAdapter.item = it
            newsDatabaseAdapter.notifyDataSetChanged()
        })

        return root
    }
}