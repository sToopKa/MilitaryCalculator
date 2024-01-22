package com.sto_opka91.militarydirectory.di.di_vt4_2023

import android.content.Context
import androidx.room.Room
import com.sto_opka91.militarydirectory.data.repository_vt4.ItemVT4Repository
import com.sto_opka91.militarydirectory.data.repository_vt4.ItemVT4repositoryImpl
import com.sto_opka91.militarydirectory.database.vt4.DaoVT4
import com.sto_opka91.militarydirectory.database.vt4.VT4Database
import com.sto_opka91.militarydirectory.domain.ItemVT4Interactor
import com.sto_opka91.militarydirectory.ui.vt4.VT4ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiVT4 {
    @Provides
    fun provideItemVT4Repository(): ItemVT4Repository {
        return ItemVT4repositoryImpl()
    }

    @Provides
    fun provideItemVT4Interactor(repository: ItemVT4Repository, dbRepository : DaoVT4): ItemVT4Interactor {
        return ItemVT4Interactor(repository, dbRepository)
    }

    @Provides
    fun provideItemVT4ViewModel(itemVT4Interactor: ItemVT4Interactor): VT4ViewModel {
        return VT4ViewModel(itemVT4Interactor)
    }

    @Provides
    @Singleton
    fun provideVT4RoomDatabase(@ApplicationContext context: Context)=
        Room.databaseBuilder(
            context,
            VT4Database::class.java,
            "bd_vt4_balls"
        ).createFromAsset("vt4_bd").build()

    @Provides
    fun provideYourDao(databaseVT4: VT4Database):  DaoVT4{
        return databaseVT4.daoVT4()
    }

}