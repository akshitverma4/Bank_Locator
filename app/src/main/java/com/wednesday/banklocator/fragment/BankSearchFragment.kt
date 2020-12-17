package com.wednesday.banklocator.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wednesday.banklocator.R
import com.wednesday.banklocator.activity.MainActivity
import com.wednesday.banklocator.adapter.IfscAdapter
import com.wednesday.banklocator.util.Resource
import com.wednesday.banklocator.viewmodel.BankViewModel
import kotlinx.android.synthetic.main.fragment_search_bank.*


class BankSearchFragment : Fragment(R.layout.fragment_search_bank) {
    lateinit var viewModel: BankViewModel
    lateinit var newsIfscAdapter: IfscAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setupRecyclerView()
        viewModel = (activity as MainActivity).viewModel
        searchButton.setOnClickListener {
            if (etSearch2.toString().isNotEmpty()) {
                viewModel.getIfscCode(etSearch2.text.toString())
            }
        }
        viewModel.ifscCodes.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsIfscAdapter = IfscAdapter(newsResponse)
                        rvSearchNews.apply {
                            adapter = newsIfscAdapter
                            layoutManager = LinearLayoutManager(activity)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("BAnkSearchFragment", "An error occured: $message")
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


