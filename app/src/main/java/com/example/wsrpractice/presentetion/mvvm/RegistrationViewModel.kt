package com.example.wsrpractice.presentetion.mvvm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.App
import com.example.wsrpractice.data.network.model.SIgnInRequestEntity
import com.example.wsrpractice.domain.use_case.user.password.SaveUserPasswordUseCase
import com.example.wsrpractice.domain.use_case.user.sign_in.SendCodeUseCase
import com.example.wsrpractice.domain.use_case.user.sign_in.SignInUseCase
import kotlinx.coroutines.async

class RegistrationViewModel(
    private val sendCodeUseCase: SendCodeUseCase,
    private val signInUseCase: SignInUseCase,
    private val saveUserPasswordUseCase: SaveUserPasswordUseCase
): ViewModel() {

    private var email = ""

    suspend fun textChangedVerifyCode(code:String):Boolean{


        val response = viewModelScope.async{

            val sIgnInRequestEntity = SIgnInRequestEntity(
                email = email,
                code = code
            )
            signInUseCase.execute(
                sIgnInRequestEntity
            )

        }

        return if (response.await().token != null){
            App.INSTANCE.saveToken(response.await().token!!)
            true
        }else{
            false
        }
    }

    suspend fun clickSendEmail(email:String):Boolean{
        this.email = email
        Log.d("serverTest",this.email)
        val response = viewModelScope.async {

            val result = sendCodeUseCase.execute(email)
            result
        }
        Log.d("serverTest",this.email)

        return response.await().message?.isNotEmpty() ?: false
    }



    fun savePassword(password:String):Boolean{
        val result = saveUserPasswordUseCase.execute(password)
        return result
    }

}