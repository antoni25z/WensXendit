package com.wensolution.wensxendit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.wensolution.wensxendit.databinding.PanduanItemBinding

class PanduanAdapter(val list: List<Panduan>) : RecyclerView.Adapter<PanduanAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanduanAdapter.ViewHolder {
        val binding = PanduanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PanduanAdapter.ViewHolder, position: Int) {
        val panduan = list[position]
        holder.binding.contentTitleTxt.text = panduan.title
        holder.binding.contentTxt.text = panduan.content
        holder.binding.titleTxt.text = panduan.title

        var count = 1
        holder.itemView.setOnClickListener {
            if (count == 1) {
                holder.binding.contentLayout.isVisible = true
                holder.binding.titleLayout.isVisible = false
                count = 0
            } else {
                holder.binding.contentLayout.isVisible = false
                holder.binding.titleLayout.isVisible = true
                count = 1
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: PanduanItemBinding) : RecyclerView.ViewHolder(binding.root)

}