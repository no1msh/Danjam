package com.saeongjima.data.repository

import com.saeongjima.model.University

interface UniversityRepository {
    suspend fun getUniversities(): Result<List<University>>
}
