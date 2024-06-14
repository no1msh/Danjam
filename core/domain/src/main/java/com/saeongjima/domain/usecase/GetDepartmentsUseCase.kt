package com.saeongjima.domain.usecase

import com.saeongjima.data.repository.UniversityRepository
import com.saeongjima.model.Department
import javax.inject.Inject

class GetDepartmentsUseCase @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    suspend operator fun invoke(universityId: Int): Result<List<Department>> {
        return universityRepository.getDepartments(universityId)
    }
}
