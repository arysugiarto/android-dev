package com.helsanf.modular.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.helsanf.modular.core.di.CoreComponent
import com.helsanf.modular.core.di.DaggerCoreComponent
import com.helsanf.modular.core.domain.model.SportBall
import com.helsanf.modular.core.ui.SportAdapter
import com.helsanf.modular.core.ui.ViewModelFactory
import com.helsanf.modular.favorite.databinding.FragmentFavoriteBinding
import com.helsanf.modular.favorite.R
import com.helsanf.modular.favorite.di.DaggerFavoriteComponent
import com.helsanf.modular.learn.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewmodel: FavoriteViewModel by viewModels {
        factory
    }
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireContext())
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private var favoriteAdapter: SportAdapter? = null
    private val listItems: MutableList<SportBall> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.factory().create(coreComponent).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            favoriteAdapter = SportAdapter()
            fetchFavoriteTeam()
        }
    }

    private fun fetchFavoriteTeam() {
        binding?.tvEmpty?.visibility = View.GONE
        binding?.rvSportFav?.apply {
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
        viewmodel.fetchFavoriteTeam()
        viewmodel.fetchFavoriteTeam.observe(viewLifecycleOwner, {
            listItems.clear()
            listItems.addAll(it)
            if (it.isNotEmpty()) {
                binding?.tvEmpty?.visibility = View.GONE
                binding?.rvSportFav?.visibility = View.VISIBLE
                favoriteAdapter?.items = listItems
                favoriteAdapter?.setOnItemClickListener {
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
            } else {
                tvEmpty.visibility = View.VISIBLE
            }
            favoriteAdapter?.notifyDataSetChanged()

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}