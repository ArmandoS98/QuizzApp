package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.database.entities.RankingEntity

data class RankingItem(
    val id: Int,
    val name: String,
    val ranking: Int
)

fun RankingEntity.toDomain() = RankingItem(id, name, ranking)
