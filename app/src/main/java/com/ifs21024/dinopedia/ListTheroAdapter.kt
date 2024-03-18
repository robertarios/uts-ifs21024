package com.ifs21024.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21024.dinopedia.databinding.ItemTheroDinoBinding

 class ListTheroAdapter(private val listThero: ArrayList<Thero>) :
    RecyclerView.Adapter<ListTheroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemTheroDinoBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val thero = listThero[position]
        holder.binding.ivItemThero.setImageResource(thero.image)
        holder.binding.tvItemThero.text = thero.name
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listThero[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listThero.size

    class ListViewHolder(var binding: ItemTheroDinoBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Thero)
    }
}