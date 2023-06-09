package com.example.wsrpractice

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import com.example.wsrpractice.data.network.Network
import com.example.wsrpractice.data.storage.StorageDataBase
import com.example.wsrpractice.data.storage.impl.user.UserStorageImp
import com.example.wsrpractice.domain.use_case.user.token.GetUserTokenUseCase
import com.example.wsrpractice.domain.use_case.user.token.SaveUserTokenUseCase
import com.github.terrakok.cicerone.Cicerone

class App: Application() {

    //cicerone
    private val cicerone = Cicerone.create()
    val router = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    lateinit var roomDataBase: StorageDataBase

    private val userStorage by lazy {
        UserStorageImp(this)
    }
    private val getTokenUseCase by lazy {
        GetUserTokenUseCase(userStorage)
    }
    private val saveUserTokenUseCase by lazy {
        SaveUserTokenUseCase(userStorage)
    }
    var token = "Токен не найден"



    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        token = getTokenUseCase.execute()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Network.init()
        roomDataBase = Room.databaseBuilder(
            applicationContext,
            StorageDataBase::class.java,
            "Storage"
        ).build()


        Log.d("UserToken", token)
    }

    fun saveToken(saveToken:String){
        saveUserTokenUseCase.execute(saveToken)

        token = getTokenUseCase.execute()
    }

    companion object{
        lateinit var INSTANCE:App
            private set

    }
}