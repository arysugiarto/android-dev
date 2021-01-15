package com.arysugiarto.latihan.coroutine_flow.model

import com.google.gson.annotations.SerializedName

class PlaceResponse (
    @field:SerializedName("features")
    val feature : List<PlacesItem>
    )