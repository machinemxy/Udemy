package com.example.daggerdemo

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MemoryCardModule::class, NCBatteryModule::class])
interface SmartPhoneComponent {
    fun inject(mainActivity: MainActivity)
}