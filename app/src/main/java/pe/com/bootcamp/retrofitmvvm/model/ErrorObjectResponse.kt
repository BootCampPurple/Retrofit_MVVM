package pe.com.bootcamp.retrofitmvvm.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ErrorObjectResponse(
    val exceptionCategory: String,
    val exceptionCode: String,
    val exceptionMessage: String,
    val exceptionDetail: String,
    val exceptionSeverity: String

) : Parcelable



