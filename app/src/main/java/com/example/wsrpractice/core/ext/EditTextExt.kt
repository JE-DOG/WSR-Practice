package com.example.wsrpractice.core.ext

import android.util.Patterns
import android.widget.EditText
import java.util.regex.Pattern

fun EditText.checkEmailValidate(email:String): Boolean{

    val validateCustom = Pattern.compile("^" +
            "(?!.*[A-Z])" +
            ".*" +
            "$")

    return validateCustom.matcher(email).matches() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}