package com.wednesday.banklocator.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.wednesday.banklocator.R
import com.wednesday.banklocator.model.Ifsc
import com.wednesday.banklocator.viewmodel.BankViewModel
import kotlinx.android.synthetic.main.partial_banks_list.view.*

class IfscAdapter(var item: ArrayList<Ifsc>, private val viewModel: BankViewModel) :
    RecyclerView.Adapter<IfscAdapter.bankDetailsViewHolder>(
    ) {
    inner class bankDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private var onItemClickListener: ((Ifsc) -> Unit)? = null
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
        val model = item[position]
        holder.itemView.apply {
            bankNameTextView.text = model.BANK
            bankAddressTextView.text = model.ADDRESS
            favouritesIconImageView.setOnClickListener {
                //val toast = Toast.makeText(context, model.BRANCH+" added to Local Database", Toast.LENGTH_LONG)
                //toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                //toast.show()
                //viewModel.upsert(model)
                onItemClickListener?.let { it(model) }
            }




        }
    }

    fun setOnItemClickListener(listener: (Ifsc) -> Unit) {
        onItemClickListener = listener
    }



    override fun getItemCount(): Int {
        return item.size
    }


}
