package com.selim.trends.data.genres

class GenresResolver {
    val genres: HashMap<Int, String> = HashMap()

    fun getByIds(ids: IntArray) = genres.filterKeys { it in ids }.values.joinToString(", ")

}
