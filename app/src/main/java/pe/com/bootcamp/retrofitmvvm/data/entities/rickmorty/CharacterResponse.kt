package pe.com.bootcamp.retrofitmvvm.data.entities.rickmorty

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String

) : Parcelable



