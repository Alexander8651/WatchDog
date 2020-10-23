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

    //declare the dog
    lateinit var dog:Dog

    //declare the binding
    lateinit var binding: FragmentDetailBinding

    val messageFactory = MessageFactory()

    //declare and init the viewmodel with the vmfactory
    val viewmodel by viewModels<ViewModelDetailFragment> { VMFactory(RepositoryImpl(DataSource(
        AppDatabase.getDatabase(requireContext())!!))) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get the parcelable from adapter
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

        //call functions
        putImage()
        addNickName()


    }

    //put image in the view
    fun putImage(){
        Glide.with(requireContext())
            .load(dog.imageURL)
            .into(binding.imageView)
    }

    // verify and add the nickname
    fun addNickName(){
        val nickName = binding.addnickname.text

        binding.button.setOnClickListener {

            if (nickName.isNullOrEmpty()){

                //show dialog if the edittext is nullorenpty
                messageFactory.getDialog(requireContext(),TYPE_DONT_NICKNAME_WRITTEN).show()
            }else{

                //create the new dog
                dog = Dog(imageURL = dog.imageURL, isFavorite = true, nickName = nickName.toString())

                addDogToFavorite(dog)
            }
        }

    }

    //send the dog to favorites
    fun addDogToFavorite(dog:Dog){

        // send dog to viewmodel
        viewmodel.addDogToFavorites(dog)

        //show toast
        Toast.makeText(requireContext(), "Just added a new dog to favorites", Toast.LENGTH_SHORT).show()

        //navigate up the view to th wmain
        findNavController().navigateUp()

    }


}