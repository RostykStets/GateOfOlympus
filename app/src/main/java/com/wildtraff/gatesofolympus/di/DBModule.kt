package com.wildtraff.gatesofolympus.di

import android.content.Context
import com.wildtraff.gatesofolympus.data.AppDatabase
import com.wildtraff.gatesofolympus.data.dao.GameLevelDao
import com.wildtraff.gatesofolympus.data.repository.implementations.GameLevelRepository
import com.wildtraff.gatesofolympus.data.repository.interfaces.IGameLevelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.buildDatabase(context)
    }

    @Provides
    fun provideGameLevelDao(db: AppDatabase): GameLevelDao {
        return db.gameLevelDao()
    }

    @Provides
    fun provideIGameLevelRepository(gameLevelDao: GameLevelDao): IGameLevelRepository {
        return GameLevelRepository(gameLevelDao)
    }
}