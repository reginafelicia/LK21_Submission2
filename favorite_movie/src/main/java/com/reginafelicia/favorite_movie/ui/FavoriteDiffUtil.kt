package com.reginafelicia.favorite_movie.ui

import androidx.recyclerview.widget.DiffUtil
import com.reginafelicia.core.data.source.database.model.DetailModel

class FavoriteDiffUtil : DiffUtil.ItemCallback<DetailModel>() {
    override fun areItemsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
        return (oldItem == newItem)
    }
}