package com.reginafelicia.lk21.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reginafelicia.lk21.R
import com.reginafelicia.lk21.ui.detail.DetailActivity
import com.reginafelicia.lk21.ui.tvshows.model.TvShowsListModel
import com.reginafelicia.lk21.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.item_movie.view.*


class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    private var listItemTvShows: MutableList<TvShowsListModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return TvShowsViewHolder(view)
    }

    override fun getItemCount(): Int = listItemTvShows.size

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val movie = listItemTvShows[position]
        holder.bind(movie)
    }

    fun setTvShows(tvShows: List<TvShowsListModel>?) {
        if (tvShows == null) return
        listItemTvShows.clear()
        listItemTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    class TvShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShows: TvShowsListModel) {
            with(itemView) {
                text_movie_title.text = tvShows.title
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DETAIL_TV_SHOW_KEY, tvShows.id)
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(IMAGE_BASE_URL + tvShows.image_url)
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