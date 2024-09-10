package com.example.daggerdemo

import dagger.Component

@Component(modules = [MemoryCardModule::class, NCBatteryModule::class])
interface SmartPhoneComponent {
    fun getSmartPhone(): SmartPhone
}