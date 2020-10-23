package com.andromoticaia.watchingdog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.andromoticaia.watchingdog.AppDatabase
import com.andromoticaia.watchingdog.R
import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.databinding.FragmentFavoritesDogBinding
import com.andromoticaia.watchingdog.domain.Repository
import com.andromoticaia.watchingdog.domain.RepositoryImpl
import com.andromoticaia.watchingdog.ui.adapter.ItemAdapter
import com.andromoticaia.watchingdog.viewmodel.VMFactory
import com.andromoticaia.watchingdog.viewmodel.ViewModelFavoritesFragment

class FavoritesDogFragment : Fragment() {

    //declared and init the viewmodel with viewmodel factory
    val viewmodel by viewModels<ViewModelFavoritesFragment> {VMFactory(RepositoryImpl(DataSource(
        AppDatabase.getDatabase(requireContext())!!)))  }

    //declare the binding
    lateinit var binding:FragmentFavoritesDogBinding

    //declare the favorites adapter
    lateinit var favoritesAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_dog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init the binding
        binding = FragmentFavoritesDogBinding.bind(view)

        //init the adapter
        favoritesAdapter = ItemAdapter()

        //add adapter to recycler view
        binding.rvFavorites.apply {
            adapter = favoritesAdapter
        }

        //observe the data in the favorites table
        viewmodel.favoritesDogs.observe(viewLifecycleOwner, Observer {

            //send the data to the adapter
            favoritesAdapter.submitList(it)
        })
    }

}