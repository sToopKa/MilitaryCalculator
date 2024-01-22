package com.sto_opka91.militarydirectory.di.di_fp_2023

import android.content.Context
import androidx.room.Room
import com.sto_opka91.militarydirectory.data.repository_fp2023.ItemRepository
import com.sto_opka91.militarydirectory.data.repository_fp2023.ItemRepositoryImpl
import com.sto_opka91.militarydirectory.database.DaoFP_2023_db
import com.sto_opka91.militarydirectory.database.DatabaseFP2023
import com.sto_opka91.militarydirectory.domain.ItemInteractor
import com.sto_opka91.militarydirectory.ui.home.HomeViewModel
import com.sto_opka91.militarydirectory.ui.single_lesson.SingleLessonViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DependencyInjection {
    @Provides
    fun provideItemRepository(): ItemRepository {
        return ItemRepositoryImpl()
    }

    @Provides
    fun provideItemInteractor(repository: ItemRepository, dbRepository : DaoFP_2023_db): ItemInteractor {
        return ItemInteractor(repository, dbRepository)
    }

    @Provides
    fun provideItemViewModel(itemInteractor: ItemInteractor): HomeViewModel {
        return HomeViewModel(itemInteractor)
    }
    @Provides
    fun provideSingleLessonViewModel(itemInteractor: ItemInteractor): SingleLessonViewModel {
        return SingleLessonViewModel(itemInteractor)
    }

    @Provides
    @Singleton
    fun provideYourRoomDatabase(@ApplicationContext context: Context)=
        Room.databaseBuilder(
            context,
            DatabaseFP2023::class.java,
            "bd_fp_2023_balls"
        ).createFromAsset("bd_fp_2023_2_balls").fallbackToDestructiveMigration().build()

    @Provides
    fun provideYourDao(databaseFP2023: DatabaseFP2023): DaoFP_2023_db {
        return databaseFP2023.daoFP_2023_db()
    }
}