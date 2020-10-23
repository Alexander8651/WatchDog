package com.andromoticaia.watchingdog.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.databinding.ItemBinding

class ItemAdapter :  ListAdapter<Dog, ItemAdapter.ViewHolder>(DogDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(var binding: ItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:Dog){

            binding.dog = item
            binding.executePendingBindings()

            if (item.nickName == ""){
                binding.nickName.visibility = View.INVISIBLE
            }else{
                binding.nickName.visibility = View.VISIBLE
            }
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class DogDiffCallback :DiffUtil.ItemCallback<Dog>(){
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.imageURL == newItem.imageURL
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }

    }


}