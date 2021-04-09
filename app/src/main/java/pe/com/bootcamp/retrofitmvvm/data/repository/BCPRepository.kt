package pe.com.bootcamp.retrofitmvvm.data.repository

import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.DiscountResponse
import pe.com.bootcamp.retrofitmvvm.data.remote.BCPRemoteDataSource
import pe.com.bootcamp.retrofitmvvm.data.remote.Result
import javax.inject.Inject

class BCPRepository @Inject constructor(
    private val remoteDataSource: BCPRemoteDataSource
) {


    suspend fun dashboardBCP(): Result<DashboardResponse> = remoteDataSource.dashboardBCP()


}


