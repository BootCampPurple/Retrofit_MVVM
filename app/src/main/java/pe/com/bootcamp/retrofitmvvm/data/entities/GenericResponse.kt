package pe.com.bootcamp.retrofitmvvm.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GenericResponse(
    val message: String

) : Parcelable



