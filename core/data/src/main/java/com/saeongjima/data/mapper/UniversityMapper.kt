package com.saeongjima.data.mapper

import com.saeongjima.data.model.UniversityResponse
import com.saeongjima.model.University

fun UniversityResponse.toModel(): University = University(
    id = id,
    name = korName,
)
