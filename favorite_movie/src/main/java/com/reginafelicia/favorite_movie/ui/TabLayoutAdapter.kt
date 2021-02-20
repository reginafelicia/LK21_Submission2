package com.reginafelicia.favorite_movie.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.reginafelicia.core.data.source.database.model.DetailModel
import com.reginafelicia.core.data.source.database.model.Type
import com.reginafelicia.favorite_movie.R
import com.reginafelicia.core.R as coreR
import com.reginafelicia.lk21.ui.detail.DetailActivity
import com.reginafelicia.lk21.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.item_movie.view.*

class TabLayoutAdapter(diffCallback: DiffUtil.ItemCallback<DetailModel>) :
    PagedListAdapter<DetailModel, TabLayoutAdapter.TabLayoutViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabLayoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return TabLayoutViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: TabLayoutViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class TabLayoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: DetailModel) {
            with(itemView) {
                text_movie_title.text = movie.title
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        if (movie.type == Type.MOVIE.name) {
                            putExtra(DetailActivity.DETAIL_MOVIE_KEY, movie.id)
                        } else {
                            putExtra(DetailActivity.DETAIL_TV_SHOW_KEY, movie.id)
                        }
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(IMAGE_BASE_URL + movie.image)
                    .fitCenter()
                    .apply(
                        RequestOptions.placeholderOf(coreR.drawable.ic_baseline_image)
                            .error(coreR.drawable.ic_baseline_broken_image)
                    )
                    .into(image_movie)
            }
        }
    }
}