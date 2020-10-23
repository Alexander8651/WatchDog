package com.andromoticaia.watchingdog.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.andromoticaia.watchingdog.AppDatabase
import com.andromoticaia.watchingdog.R
import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.databinding.FragmentMainBinding
import com.andromoticaia.watchingdog.domain.RepositoryImpl
import com.andromoticaia.watchingdog.ui.adapter.ItemAdapter
import com.andromoticaia.watchingdog.viewmodel.VMFactory
import com.andromoticaia.watchingdog.viewmodel.ViewModelMainFragment


class MainFragment : Fragment() {

    //declare and init the viewmodel with the viewmodel factory
    val viewmodel by viewModels<ViewModelMainFragment> {VMFactory(RepositoryImpl(DataSource(
        AppDatabase.getDatabase(requireContext())!!)))  }

    //declare the binding
    lateinit var binding: FragmentMainBinding

    //declare the item adapter
    lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init the binding
        binding = FragmentMainBinding.bind(view)

        //ini the item adapter
        itemAdapter = ItemAdapter()

        //add the adapter to the recycler view
        binding.rvDog.apply {
            adapter = itemAdapter
        }

        //every click get data from api
        binding.getDogs.setOnClickListener {
            binding.pressBotton.visibility = View.INVISIBLE
            viewmodel.getData().observe(viewLifecycleOwner, Observer {

                //clear the position to fix a unbounded exception
                binding.rvDog.recycledViewPool.clear()

                //send the data to the adapter
                itemAdapter.submitList(it)

            })
        }

        // indicates the fragment will have a menu
        setHasOptionsMenu(true)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        //inflate the menu to this fragment
        inflater.inflate(R.menu.mainmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            //when favorites item is pressed navigate to the favorites fragment
            R.id.favorites -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoritesDogFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }


    }

}