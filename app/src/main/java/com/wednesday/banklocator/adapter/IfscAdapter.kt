package com.wednesday.banklocator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wednesday.banklocator.R
import com.wednesday.banklocator.model.IfscResponse
import kotlinx.android.synthetic.main.partial_banks_list.view.*

class IfscAdapter(var item: IfscResponse) : RecyclerView.Adapter<IfscAdapter.ArticleViewHolder>(
) {
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.partial_banks_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val model = item
        holder.itemView.apply {
            bankName_tv.text = model.BRANCH
            bankAddress_tv.text = model.ADDRESS
        }
    }

    override fun getItemCount(): Int {
        if (item != null) {
            return 1
        }
        return 0
    }
}