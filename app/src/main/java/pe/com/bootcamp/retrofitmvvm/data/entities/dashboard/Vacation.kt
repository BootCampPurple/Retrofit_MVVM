package pe.com.bootcamp.retrofitmvvm.data.entities.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Vacation(
    val pendientes: Int,
    val ganados: Int

) : Parcelable



