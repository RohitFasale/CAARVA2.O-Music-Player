package com.example.caarvamusicplayer2o.models

data class SongModel(
    val id : String,
    val title : String,
    val subtitle : String,
    val url : String,
    val coverUrl : String,
)
{
    constructor() : this("","","","","")
}
