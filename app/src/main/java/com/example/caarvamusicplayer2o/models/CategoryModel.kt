package com.example.caarvamusicplayer2o.models

data class CategoryModel(
    val coverUrl : String,
    val name : String,
    var songs : List<String>
)
{
    constructor():this("","", listOf())
}
