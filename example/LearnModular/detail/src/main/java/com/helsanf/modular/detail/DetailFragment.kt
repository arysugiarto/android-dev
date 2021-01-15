package com.helsanf.modular.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope

import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.helsanf.modular.core.di.CoreComponent
import com.helsanf.modular.core.di.DaggerCoreComponent
import com.helsanf.modular.core.ui.ViewModelFactory
import com.helsanf.modular.detail.databinding.FragmentDetailBinding
import com.helsanf.modular.detail.di.DaggerDetailComponent
import com.helsanf.modular.detail.di.DetailComponent
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailFragment : Fragment() {
    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var factory: ViewModelFactory

    private val detailViewModel by viewModels<DetailViewModel> {
        factory
    }
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireContext())
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerDetailComponent.factory().create(coreComponent).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view =  binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDetailTeam()
    }

    private fun fetchDetailTeam(){
        detailViewModel.fetchDetailTeam(args.idteam.toString())
        detailViewModel.detailLiveData.observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext())
                .load(it.logoTeam)
                .into(binding.imgLogoClub)
            binding.tvDescClub.text = it.descriptionTeam
            binding.tvNameClub.text = it.titleTeam
            var statusFav = it.isFavorite
            isFavorite(statusFav)
            fabFavorite.setOnClickListener { _ ->
                statusFav = !statusFav
                detailViewModel.updateTeam(it,statusFav)
                isFavorite(statusFav)
            }
        })

    }

    private fun isFavorite(isFavorite : Boolean){
        if(isFavorite){
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_favorite_white))
        }else{
            fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_favorite_black))

        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}