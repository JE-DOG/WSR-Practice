package com.example.wsrpractice.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wsrpractice.domain.model.PatientCardDomain

@Entity(tableName = "patient_card_table")
data class PatientCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val firstname:String,
    val middlename:String,
    val lastname:String,
    val bith:String,
    val pol:String,
    val image:String? = null
) {
    fun toDomain(): PatientCardDomain{
        return this.run {
            PatientCardDomain(
                id = id,
                firstname = firstname,
                middlename = middlename,
                lastname = lastname,
                bith = bith,
                pol = pol
            )
        }
    }
}