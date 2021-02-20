package com.reginafelicia.favorite_movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.reginafelicia.core.di.ViewModelFactory
import com.reginafelicia.favorite_movie.R
import com.reginafelicia.favorite_movie.di.FavoriteAppComponent
import com.reginafelicia.lk21.core.di.getApplicationComponent
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var tabLayoutAdapter: TabLayoutAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        FavoriteAppComponent.getFavoriteComponent(requireContext()).injectFavoriteMovieList(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
    }

    private fun setupRecyclerView() {
        rv_favorite_movie_list.layoutManager = GridLayoutManager(context, 3)
        rv_favorite_movie_list.setHasFixedSize(true)
        tabLayoutAdapter = TabLayoutAdapter(FavoriteDiffUtil())
        rv_favorite_movie_list.adapter = tabLayoutAdapter
        viewModel.favoriteMovieList.observe(viewLifecycleOwner, Observer {
            tabLayoutAdapter.submitList(it)
        })
    }
}