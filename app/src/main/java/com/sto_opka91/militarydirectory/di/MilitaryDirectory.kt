package com.sto_opka91.militarydirectory.di

import android.app.Application
import com.yandex.mobile.ads.common.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MilitaryDirectory: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this){

        }
    }

}