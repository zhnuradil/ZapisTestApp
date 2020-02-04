package kz.nura.zapistestapp.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager

import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.adapters.CatalogListAdapter
import kz.nura.zapistestapp.databinding.FragmentHomeBinding
import kz.nura.zapistestapp.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val adapter = CatalogListAdapter()
        binding.catalogList.adapter = adapter
        binding.catalogList.layoutManager = LinearLayoutManager(context)

        viewModel.salons.observe(viewLifecycleOwner, Observer { salons ->
            Log.d("###", "salons: ${salons.toString()}")
            adapter.submitList(salons)
        })
        return binding.root
    }

}