package com.example.wsrpractice.presentetion.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsrpractice.data.network.model.UserPatientCardNetwork
import com.example.wsrpractice.domain.use_case.user.patient_card.CreatePatientCardUseCase
import com.example.wsrpractice.domain.use_case.user.patient_card.SetAvatarUseCase
import com.example.wsrpractice.domain.use_case.user.patient_card.UpdatePatientCardUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val createPatientCardUseCase: CreatePatientCardUseCase,
    private val updatePatientCardUseCase: UpdatePatientCardUseCase,
    private val avatarUseCase: SetAvatarUseCase
):ViewModel() {

    private val liveDataPatientCard = MutableLiveData<UserPatientCardNetwork?>()
    val _liveDataPatientCard = liveDataPatientCard
    private val liveDataServerAnswer = MutableLiveData<Boolean>()
    val _liveDataServerAnswer = liveDataServerAnswer

    init {
        viewModelScope.launch {
            getPatientCard()
        }
    }

    suspend fun getPatientCard(){
        val patientCard = UserPatientCardNetwork(
            69696969,
            "Gigachad",
            "Gigachadov",
            "Gigachadovich",
            "01.12.1969",
            "Мужской",
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.discogs.com%2Fru%2Fartist%2F4159781-Tekashi-69&psig=AOvVaw1H4n_lsLy-anOAztybldQC&ust=1678948735783000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCKiWu8ip3f0CFQAAAAAdAAAAABAE"
        )
        liveDataPatientCard.postValue(patientCard)
    }

    suspend fun savePatientCard(patientCard: UserPatientCardNetwork, newUser:Boolean){
        if (newUser) {
            val response = createPatientCardUseCase.execute(patientCard)
            Log.d("ServerPatientTest", response.toString())

        }else {
            val response = updatePatientCardUseCase.execute(userPatientCardNetwork = patientCard)
            Log.d("ServerPatientTest", response.toString())
        }
    }

    suspend fun setAvatar(image:String){
        val response = avatarUseCase.execute(image = image)
        Log.d("ServerPatientTest",response.toString())

    }

}