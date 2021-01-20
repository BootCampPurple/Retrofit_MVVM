package pe.com.bootcamp.retrofitmvvm.data.entities.discount

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Discount(
    val title: String,
    val description: String,
    val discount: String,
    val discountImage: String

) : Parcelable



