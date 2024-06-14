package com.saeongjima.data.repository

import com.saeongjima.model.Department
import com.saeongjima.model.University

interface UniversityRepository {
    suspend fun getUniversities(): Result<List<University>>
    suspend fun getDepartments(universityId: Int): Result<List<Department>>
}
