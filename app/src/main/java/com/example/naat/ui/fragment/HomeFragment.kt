package com.example.naat.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.naat.R
import com.example.naat.data.adapter.HomeAdapter
import com.example.naat.data.adapter.IHomeListener
import com.example.naat.databinding.FragmentHomeBinding
import com.example.naat.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), IHomeListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.binding = FragmentHomeBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        this.viewModel.onCreate()
        this.viewModel.mldListCompany.observe(viewLifecycleOwner, {
            this.binding.rvCompany.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = HomeAdapter(
                    it,
                    this@HomeFragment
                )
            }
        })
    }


    override fun onClickRechargeView() {
        this.requireView().findNavController().navigate(R.id.to_telephonicRechargeFragment)
    }
}