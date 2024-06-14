package com.saeongjima.data.api

import com.saeongjima.data.model.UniversityResponse
import retrofit2.http.GET

interface UniversityService {
    @GET("/api/school")
    suspend fun getUniversities(): Result<List<UniversityResponse>>
}
