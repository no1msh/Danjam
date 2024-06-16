package com.saeongjima.model.account

import com.saeongjima.model.University
import java.io.File

data class SignUpInformation(
    val id: Id,
    val password: Password,
    val gender: Boolean,
    val nickname: Nickname,
    val email: Email,
    val birth: String,
    val university: University,
    val entryYear: String,
    val major: String,
    val authImgFile: File,
    val residentImgFile: File,
)
