package com.reginafelicia.lk21.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.reginafelicia.lk21.R
import com.reginafelicia.core.di.ViewModelFactory
import com.reginafelicia.lk21.core.di.getApplicationComponent
import com.reginafelicia.lk21.ui.tvshows.model.TvShowsListModel
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import javax.inject.Inject

class TvShowsFragment : Fragment() {

    private lateinit var viewModel: TvShowsViewModel
    private lateinit var tvShowsAdapter: TvShowsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
        viewModel.getTvShows()
    }

    private fun setupViewModel() {
        getApplicationComponent().injectTvShowsList(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[TvShowsViewModel::class.java]
        viewModel.run {
            getViewState().observe(viewLifecycleOwner, Observer {
                setupView(it)
            })
        }
    }

    private fun setupRecyclerView() {
        rv_tv_show_list.layoutManager = GridLayoutManager(context, 3)
        tvShowsAdapter = TvShowsAdapter()
        rv_tv_show_list.setHasFixedSize(true)
        rv_tv_show_list.adapter = tvShowsAdapter
    }

    private fun setupData(tvShowList: List<TvShowsListModel>) {
        tvShowsAdapter.setTvShows(tvShowList)
    }

    private fun setupView(viewState: TvShowsViewState?) {
        when (viewState) {
            is TvShowsViewState.Loading -> {
                pb_list_tvShows.visibility = View.VISIBLE
                iv_error_tvShows.visibility = View.GONE
            }
            is TvShowsViewState.Success -> {
                pb_list_tvShows.visibility = View.GONE
                iv_error_tvShows.visibility = View.GONE
                setupData(viewState.tvShowsList)
            }
            is TvShowsViewState.Failed -> {
                pb_list_tvShows.visibility = View.GONE
                iv_error_tvShows.visibility = View.VISIBLE
            }
        }
    }
}