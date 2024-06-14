package com.saeongjima.data.api

import com.saeongjima.data.model.UniversityResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UniversityService {
    @GET("/api/school")
    suspend fun getUniversities(): Result<List<UniversityResponse>>

    @GET("/api/school/{schoolId}/major")
    suspend fun getDepartments(
        @Path("schoolId")
        schoolId: Int
    ): Result<Map<String, String>>
}
