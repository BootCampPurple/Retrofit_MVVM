package pe.com.bootcamp.retrofitmvvm.data.entities.vacation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class VacationPost(
    val from: String,
    val to: String

) : Parcelable



