package pe.com.bootcamp.retrofitmvvm.data.remote

import pe.com.bootcamp.retrofitmvvm.rest.ApiService


import javax.inject.Inject

class BCPRemoteDataSource @Inject constructor(
    private val service: ApiService
) : BaseDataSource() {

    suspend fun dashboardBCP() =
        safeApiCall { service.dashboardBCP() }

    suspend fun discountBCP() =
        safeApiCall { service.discountBCP() }


}


