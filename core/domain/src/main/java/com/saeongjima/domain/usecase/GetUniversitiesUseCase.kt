package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.UniversityRepository
import com.saeongjima.model.University
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    suspend operator fun invoke(): Result<List<University>> {
        return universityRepository.getUniversities()
    }
}
