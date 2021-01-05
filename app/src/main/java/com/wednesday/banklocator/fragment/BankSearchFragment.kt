package com.wednesday.banklocator.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wednesday.banklocator.R
import com.wednesday.banklocator.adapter.IfscAdapter
import com.wednesday.banklocator.db.FavouritesBankDatabase
import com.wednesday.banklocator.model.IfscResponse
import com.wednesday.banklocator.repository.BankDetailsRepository
import com.wednesday.banklocator.util.Resource
import com.wednesday.banklocator.viewmodel.BankViewModel
import com.wednesday.banklocator.viewmodel.BankViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_bank.*


class BankSearchFragment : Fragment(R.layout.fragment_search_bank)
{
    lateinit var viewModel:BankViewModel
    val list:ArrayList<IfscResponse> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
  
        val bankDetailsRepository = activity?.let { FavouritesBankDatabase(it) }?.let { BankDetailsRepository(it) }
        val viewModelProviderFactory = bankDetailsRepository?.let { BankViewModelFactory(it) }
        viewModel = viewModelProviderFactory?.let {
            ViewModelProvider(this,
                it
            ).get(BankViewModel::class.java)
        }!!
        val ifscAdapter: IfscAdapter by lazy {
            IfscAdapter(ArrayList(),viewModel)
        }
        bankDetailsRecyclerView.adapter = ifscAdapter
        bankDetailsRecyclerView.layoutManager = LinearLayoutManager(activity)
        ifscAdapter.setOnItemClickListener {
         viewModel.upsert(it)
        searchButton.setOnClickListener {
            if(bankSearch_text_field.toString().isNotEmpty())
            {
                viewModel.getIfscCode(bankSearch_text_field.text.toString())
            }
        }
        viewModel.ifscCodes.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { Response ->
                        list.clear()
                        list.add(newsResponse)
                        bankDetailsRecyclerView.apply {
                            adapter = IfscAdapter
                            layoutManager = LinearLayoutManager(activity)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("BankSearchFragment", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }
    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

   


}


