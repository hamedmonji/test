package com.example.myapplication.jokesList.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokeModel(
    val id: Long,
    @SerializedName("lang")
    val language: String,
    val category: String,
    val type: JokeType,
    val joke: String?,
    val setup: String?,
    val delivery: String?
) : Parcelable {
    val completeJoke: String
        get() {
            return when (type) {
                JokeType.SINGLE -> joke!!
                else -> "$setup!!, $delivery"
            }
        }

    val title: String
        get() {
            return when (type) {
                JokeType.SINGLE -> joke!!
                else -> setup!!
            }
        }

    enum class JokeType {
        @SerializedName("single")
        SINGLE,

        @SerializedName("twopart")
        TWO_PART
    }
}