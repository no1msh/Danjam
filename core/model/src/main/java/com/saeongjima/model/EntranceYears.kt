package com.saeongjima.model

import java.time.LocalDateTime

data object EntranceYears {
    private const val MAX_ENTRANCE_YEARS = 10

    val years: List<String> = List(MAX_ENTRANCE_YEARS) { "${LocalDateTime.now().year - it}" }
}
