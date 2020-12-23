package com.wednesday.banklocator.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.wednesday.banklocator.R
import com.wednesday.banklocator.model.IfscResponse
import com.wednesday.banklocator.viewmodel.BankViewModel
import kotlinx.android.synthetic.main.partial_banks_list.view.*

class IfscAdapter(var item: ArrayList<IfscResponse>, private val viewModel: BankViewModel) :
    RecyclerView.Adapter<IfscAdapter.bankDetailsViewHolder>(
    ) {
    inner class bankDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bankDetailsViewHolder {
        return bankDetailsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.partial_banks_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: bankDetailsViewHolder, position: Int) {
        val model = item
        holder.itemView.apply {
            bankNameTextView.text = model.BRANCH
            bankAddressTextView.text = model.ADDRESS
            favouritesIconImageView.setOnClickListener {
                val toast = Toast.makeText(context, model.BRANCH+" added to Local Database", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
                viewModel.upsert(model)
            }



            setOnClickListener {
                onItemClickListener?.let { it(model) }
            }
        }
    }


   override fun getItemCount(): Int {
       return item.size
    }

    private var onItemClickListener: ((IfscResponse) -> Unit)? = null
}
