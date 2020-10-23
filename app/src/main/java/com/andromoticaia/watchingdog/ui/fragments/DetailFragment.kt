package com.andromoticaia.watchingdog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andromoticaia.watchingdog.AppDatabase
import com.andromoticaia.watchingdog.R
import com.andromoticaia.watchingdog.arquitecturepatern.MessageFactory
import com.andromoticaia.watchingdog.arquitecturepatern.MessageFactory.Companion.TYPE_DONT_NICKNAME_WRITTEN
import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.databinding.FragmentDetailBinding
import com.andromoticaia.watchingdog.domain.RepositoryImpl
import com.andromoticaia.watchingdog.viewmodel.VMFactory
import com.andromoticaia.watchingdog.viewmodel.ViewModelDetailFragment
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    lateinit var dog:Dog
    lateinit var binding: FragmentDetailBinding

    val messageFactory = MessageFactory()

    val viewmodel by viewModels<ViewModelDetailFragment> { VMFactory(RepositoryImpl(DataSource(
        AppDatabase.getDatabase(requireContext())!!))) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            dog = it.getParcelable("dog")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init the binding
        binding = FragmentDetailBinding.bind(view)

        putImage()

        addNickName()


    }

    fun putImage(){
        Glide.with(requireContext())
            .load(dog.imageURL)
            .into(binding.imageView)
    }

    fun addNickName(){
        val nickName = binding.addnickname.text

        binding.button.setOnClickListener {
            if (nickName.isNullOrEmpty()){
                messageFactory.getDialog(requireContext(),TYPE_DONT_NICKNAME_WRITTEN).show()
            }else{
                dog = Dog(imageURL = dog.imageURL, isFavorite = true, nickName = nickName.toString())
                addDogToFavorite(dog)
            }
        }

    }

    fun addDogToFavorite(dog:Dog){

        viewmodel.addDogToFavorites(dog)
        Toast.makeText(requireContext(), "Just added a new dog to favorites", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()

    }


}