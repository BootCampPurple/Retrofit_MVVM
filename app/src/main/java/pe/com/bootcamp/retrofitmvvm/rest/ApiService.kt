package pe.com.bootcamp.retrofitmvvm.rest

import pe.com.bootcamp.retrofitmvvm.data.entities.GenericResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.DiscountResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.rickmorty.CharacterResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.vacation.VacationPost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    //https://www.mockable.io/a/#/space/demo4049540/rest

    @GET("dashboardBCP")
    suspend fun dashboardBCP(): Response<DashboardResponse>

    @GET("discountBCP")
    suspend fun discountBCP(): Response<DiscountResponse>


    //https://rickandmortyapi.com/api/character/4
    @GET("https://rickandmortyapi.com/api/character/{id}")
    suspend fun getCharacterRickMorty(@Path("id") id: String): Response<CharacterResponse>


    @POST("http://l7qve.mocklab.io/saveVacation")
    suspend fun saveVacation(@Body post: VacationPost): Response<GenericResponse>


}