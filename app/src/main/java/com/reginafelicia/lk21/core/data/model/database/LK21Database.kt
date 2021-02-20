package com.reginafelicia.lk21.core.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reginafelicia.core.data.source.database.LK21Dao
import com.reginafelicia.core.data.source.database.model.DetailModel

@Database(entities = [DetailModel::class], version = 3)
abstract class LK21Database : RoomDatabase() {
    abstract fun lk21Dao(): LK21Dao
}