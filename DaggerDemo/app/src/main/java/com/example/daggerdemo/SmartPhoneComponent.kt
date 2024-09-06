package com.example.daggerdemo

import dagger.Component

@Component
interface SmartPhoneComponent {
    fun getSmartPhone(): SmartPhone
}