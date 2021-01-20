package pe.com.bootcamp.retrofitmvvm.data.entities.discount

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DiscountResponse(
    val discounts: List<Discount>

) : Parcelable



