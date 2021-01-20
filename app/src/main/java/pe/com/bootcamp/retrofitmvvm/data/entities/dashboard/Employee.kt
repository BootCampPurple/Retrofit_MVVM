package pe.com.bootcamp.retrofitmvvm.data.entities.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Employee(
    val documentNumber: String,
    val fullName: String,
    val email: String,
    val sex: String,
    val management: String

) : Parcelable



