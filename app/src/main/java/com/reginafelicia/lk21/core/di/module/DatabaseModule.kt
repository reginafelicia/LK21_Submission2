package com.reginafelicia.lk21.core.di.module

import android.content.Context
import androidx.room.Room
import com.reginafelicia.core.data.source.database.LK21Dao
import com.reginafelicia.lk21.BuildConfig
import com.reginafelicia.lk21.core.data.model.database.LK21Database
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
open class DatabaseModule {

    @Provides
    open fun provideDatabase(applicationContext: Context): LK21Database {
        val builder = Room.databaseBuilder(
            applicationContext,
            LK21Database::class.java,
            "lk21"
        )
        val factory = SupportFactory(SQLiteDatabase.getBytes(BuildConfig.DATABASE_PASS_PHRASE.toCharArray()))
        builder.openHelperFactory(factory)
       return builder.build()
    }

    @Provides
    open fun provideMovieDao(database: LK21Database): LK21Dao {
        return database.lk21Dao()
    }
}