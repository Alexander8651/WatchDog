package com.andromoticaia.watchingdog.ui.adapter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andromoticaia.watchingdog.AppDatabase
import com.andromoticaia.watchingdog.R
import com.andromoticaia.watchingdog.arquitecturepatern.MessageFactory
import com.andromoticaia.watchingdog.arquitecturepatern.MessageFactory.Companion.TYPE_DELETE_DOG_FROMFAVORITES
import com.andromoticaia.watchingdog.data.DataSource
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

            val messageFactory = MessageFactory()

            binding.dog = item
            binding.executePendingBindings()

            if (item.nickName == ""){
                binding.nickName.visibility = View.INVISIBLE
            }else{
                binding.nickName.visibility = View.VISIBLE
                binding.nickName.text = item.nickName
            }

            itemView.setOnClickListener{

                if (!item.isFavorite){
                    val dog = Bundle()
                    dog.putParcelable("dog", item)
                    it.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, dog)
                }else if (item.isFavorite){

                    messageFactory.getDialog(itemView.context,TYPE_DELETE_DOG_FROMFAVORITES
                        )
                        .setMessage("¿Do you want delete the dog ${item.nickName}?" )
                        .setPositiveButton("Accept"){ dialog, wich ->
                        DataSource(AppDatabase.getDatabase(itemView.context)!!).deleteDog(item)

                    }.show()

                }


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