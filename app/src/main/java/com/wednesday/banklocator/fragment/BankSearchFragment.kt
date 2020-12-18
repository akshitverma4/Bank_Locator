package com.wednesday.banklocator.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wednesday.banklocator.R
import com.wednesday.banklocator.activity.MainActivity
import com.wednesday.banklocator.adapter.IfscAdapter
import com.wednesday.banklocator.model.IfscResponse
import com.wednesday.banklocator.util.Resource
import com.wednesday.banklocator.viewmodel.BankViewModel
import kotlinx.android.synthetic.main.fragment_search_bank.*


class BankSearchFragment : Fragment(R.layout.fragment_search_bank)
{
    lateinit var viewModel:BankViewModel
    private val bankAdapter: IfscAdapter by lazy {
        IfscAdapter(IfscResponse())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel = (activity as MainActivity).viewModel
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
                    response.data?.let {bankResponse ->
                        bankAdapter.resetDataSource(bankResponse)
                        bankDetailsRecyclerView.apply {
                            adapter = bankAdapter
                            layoutManager = LinearLayoutManager(activity)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity,"An error occured: $message", Toast.LENGTH_LONG).show()

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

    private fun setupRecyclerView() {
        bankDetailsRecyclerView.apply {
            adapter = bankAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}


