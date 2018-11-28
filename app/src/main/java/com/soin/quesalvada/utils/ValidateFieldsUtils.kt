package com.soin.quesalvada.utils

import android.widget.EditText

object ValidateFieldsUtils {

    fun validateEditTextFieldField(editText: EditText, messageError: String): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.error = messageError
            return false
        }
        return true
    }
}