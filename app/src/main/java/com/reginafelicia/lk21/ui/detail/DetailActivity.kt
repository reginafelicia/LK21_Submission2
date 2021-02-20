package com.reginafelicia.lk21.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reginafelicia.lk21.R
import com.reginafelicia.core.di.ViewModelFactory
import com.reginafelicia.lk21.core.di.getApplicationComponent
import com.reginafelicia.core.data.source.database.model.DetailModel
import com.reginafelicia.lk21.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.bottom_sheet_persistent.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.extras?.getInt(DETAIL_MOVIE_KEY)
        val tvId = intent.extras?.getInt(DETAIL_TV_SHOW_KEY)

        setupViewModel()
        if (id != null && id != 0) {
            viewModel.getDetailMovieListData(id)
            viewModel.checkIsFavorite(id)
        } else if (tvId != null && tvId != 0) {
            viewModel.getDetailTvShowsListData(tvId)
            viewModel.checkIsFavorite(tvId)
        }
        ibFavorite.setOnClickListener {
            viewModel.updateFavorite()
        }
    }

    private fun setupViewModel() {
        getApplicationComponent().injectDetail(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        viewModel.run {
            getViewState().observe(this@DetailActivity, Observer {
                render(it)
            })
            isFavorite().observe(this@DetailActivity, Observer {
                if (it) {
                    ibFavorite.setColorFilter(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.colorPrimary
                        )
                    )
                } else {
                    ibFavorite.setColorFilter(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.grey_30
                        )
                    )
                }
            })
        }
    }

    private fun render(viewState: DetailViewState?) {
        when (viewState) {
            is DetailViewState.Loading -> {
                iv_error_detail.visibility = View.GONE
                pb_detail.visibility = View.VISIBLE
            }
            is DetailViewState.Success -> {
                iv_error_detail.visibility = View.GONE
                pb_detail.visibility = View.GONE
                setupView(viewState.movieEntity)
            }
            is DetailViewState.Failed -> {
                iv_error_detail.visibility = View.VISIBLE
                pb_detail.visibility = View.GONE
            }
        }
    }

    private fun setupView(movieEntity: DetailModel) {
        text_bottom_title.text = movieEntity.title
        text_detail_description.text = movieEntity.description
        text_detail_date.text = movieEntity.date
        text_detail_category.text = movieEntity.category
        Glide.with(this)
            .load(IMAGE_BASE_URL + movieEntity.image)
            .centerCrop()
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_baseline_image)
                    .error(R.drawable.ic_baseline_broken_image)
            )
            .into(image_detail)
    }

    companion object {
        const val DETAIL_MOVIE_KEY = "detail_movie_key"
        const val DETAIL_TV_SHOW_KEY = "detail_tv_key"
    }
}