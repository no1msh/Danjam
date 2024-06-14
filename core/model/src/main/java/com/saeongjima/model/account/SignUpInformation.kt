package com.saeongjima.model.account

import com.saeongjima.model.University

data class SignUpInformation(
    val id: Id,
    val password: Password,
    val gender: Int,
    val nickname: Nickname,
    val email: Email,
    val birth: String,
    val university: University,
    val entryYear: Int,
    val major: String,
)
