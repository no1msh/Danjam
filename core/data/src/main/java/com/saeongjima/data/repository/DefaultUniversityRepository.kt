package com.saeongjima.data.repository

import com.saeongjima.data.api.UniversityService
import com.saeongjima.data.mapper.toModel
import com.saeongjima.data.mapper.toResult
import com.saeongjima.data.model.UniversityResponse
import com.saeongjima.model.Department
import com.saeongjima.model.University
import javax.inject.Inject

class DefaultUniversityRepository @Inject constructor(
    private val universityService: UniversityService
) : UniversityRepository {
    override suspend fun getUniversities(): Result<List<University>> {
        return universityService.getUniversities().toResult { universities ->
            universities.map(UniversityResponse::toModel)
        }
    }

    override suspend fun getDepartments(universityId: Int): Result<List<Department>> {
        return universityService.getDepartments(universityId).toResult { response ->
            response.entries.mapIndexed { index, entry ->
                Department(id = index, name = entry.key, division = entry.value)
            }
        }
    }
}
