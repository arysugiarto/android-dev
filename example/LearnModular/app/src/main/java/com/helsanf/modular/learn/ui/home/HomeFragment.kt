package com.helsanf.modular.learn.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.helsanf.modular.core.data.Resource
import com.helsanf.modular.core.ui.SportAdapter
import com.helsanf.modular.core.ui.ViewModelFactory
import com.helsanf.modular.learn.MyApplication
import com.helsanf.modular.learn.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory


    private val homeViewModel: HomeViewModel by viewModels { factory }
    private var sportAdapter: SportAdapter? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sportAdapter = SportAdapter()
        fetchSportTeam()
    }

    private fun fetchSportTeam() = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
        binding.rvSportHome.apply {
            setHasFixedSize(true)
            adapter = sportAdapter
        }
        homeViewModel.fetchSportData.observe(viewLifecycleOwner, Observer {
            if (it.data != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvSportHome.visibility = View.VISIBLE
                        sportAdapter?.items = it.data!!
                        sportAdapter?.setOnItemClickListener {
                            try {
                                findNavController().navigate(
                                    HomeFragmentDirections.actionToDetail(
                                        it.idTeam
                                    )
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        sportAdapter = null
    }
}