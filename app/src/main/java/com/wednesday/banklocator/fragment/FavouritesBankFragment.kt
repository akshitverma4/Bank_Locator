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
import com.wednesday.banklocator.adapter.IfscAdapter
import com.wednesday.banklocator.db.FavouritesBankDatabase
import com.wednesday.banklocator.model.Ifsc
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

        val bankDetailsRepository = activity?.let { FavouritesBankDatabase(it) }?.let { BankDetailsRepository(it) }
        val viewModelProviderFactory = bankDetailsRepository?.let { BankViewModelFactory(it) }
        viewModel = viewModelProviderFactory?.let {
            ViewModelProvider(
                this,
                it
            ).get(BankViewModel::class.java)
        }!!

        val ifscAdapter: IfscAdapter by lazy {
            IfscAdapter(ArrayList(), viewModel)
        }
        root.favouriteBanksRecyclerView.adapter = ifscAdapter
        root.favouriteBanksRecyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.getAllShoppingItems().observe(requireActivity(), Observer {
            ifscAdapter.item = it as ArrayList<Ifsc>
            ifscAdapter.notifyDataSetChanged()
        })

        return root
    }
}
