package com.reginafelicia.lk21.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reginafelicia.lk21.R
import com.reginafelicia.lk21.ui.detail.DetailActivity
import com.reginafelicia.lk21.ui.detail.DetailActivity.Companion.DETAIL_MOVIE_KEY
import com.reginafelicia.lk21.ui.movies.model.MovieListModel
import com.reginafelicia.lk21.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var listItemMovie: MutableList<MovieListModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = listItemMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listItemMovie[position]
        holder.bind(movie)
    }

    fun setMovie(movie: List<MovieListModel>?) {
        if (movie == null) return
        listItemMovie.clear()
        listItemMovie.addAll(movie)
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieListModel) {
            with(itemView) {
                text_movie_title.text = movie.title
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DETAIL_MOVIE_KEY, movie.id)
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(IMAGE_BASE_URL + movie.image_url)
                    .fitCenter()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_image)
                            .error(R.drawable.ic_baseline_broken_image)
                    )
                    .into(image_movie)
            }
        }
    }
}