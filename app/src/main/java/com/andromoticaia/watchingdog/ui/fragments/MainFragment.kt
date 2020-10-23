package com.andromoticaia.watchingdog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.andromoticaia.watchingdog.R
import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.databinding.FragmentMainBinding
import com.andromoticaia.watchingdog.domain.RepositoryImpl
import com.andromoticaia.watchingdog.ui.adapter.ItemAdapter
import com.andromoticaia.watchingdog.viewmodel.VMFactory
import com.andromoticaia.watchingdog.viewmodel.ViewModelMainFragment


class MainFragment : Fragment() {

    val viewmodel by viewModels<ViewModelMainFragment> {VMFactory(RepositoryImpl(DataSource()))  }

    lateinit var binding: FragmentMainBinding

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

        binding = FragmentMainBinding.bind(view)

        itemAdapter = ItemAdapter()

        binding.rvDog.apply {
            adapter = itemAdapter
        }

        binding.getDogs.setOnClickListener {
            binding.pressBotton.visibility = View.INVISIBLE
            viewmodel.getData().observe(viewLifecycleOwner, Observer {
                binding.rvDog.recycledViewPool.clear()

                itemAdapter.submitList(it)
            })
        }


    }

}