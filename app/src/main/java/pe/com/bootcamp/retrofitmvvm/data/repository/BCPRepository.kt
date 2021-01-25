package pe.com.bootcamp.retrofitmvvm.data.repository

import pe.com.bootcamp.retrofitmvvm.data.entities.GenericResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.DiscountResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.rickmorty.CharacterResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.vacation.VacationPost
import pe.com.bootcamp.retrofitmvvm.data.remote.BCPRemoteDataSource
import pe.com.bootcamp.retrofitmvvm.data.remote.Result
import javax.inject.Inject

class BCPRepository @Inject constructor(
    private val remoteDataSource: BCPRemoteDataSource
) {

    suspend fun dashboardBCP(): Result<DashboardResponse> = remoteDataSource.dashboardBCP()

    suspend fun discountBCP(): Result<DiscountResponse> = remoteDataSource.discountBCP()

    suspend fun saveVacation(post: VacationPost): Result<GenericResponse> =
        remoteDataSource.saveVacation(post)

    suspend fun getCharacterRickMorty(id: String): Result<CharacterResponse> =
        remoteDataSource.getCharacterRickMorty(id = id )



}


