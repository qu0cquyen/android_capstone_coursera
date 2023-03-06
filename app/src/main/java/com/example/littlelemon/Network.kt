package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkdata (
    @SerialName("menu")
    val itemNetwork : List<MenuItemNetwork>,
)

@Serializable
class MenuItemNetwork(
    @SerialName("id")
    val id : Int,
    @SerialName("title")
    val title : String,
    @SerialName("description")
    val description : String,
    @SerialName("price")
    val price: Double,
    @SerialName("image")
    val image : String,
    @SerialName("category")
    val category : String,
)