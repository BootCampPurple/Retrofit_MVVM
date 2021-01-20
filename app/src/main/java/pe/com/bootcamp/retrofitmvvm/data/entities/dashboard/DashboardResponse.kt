package pe.com.bootcamp.retrofitmvvm.data.entities.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DashboardResponse(
    val employee: Employee,
    val vacation: Vacation
) : Parcelable



