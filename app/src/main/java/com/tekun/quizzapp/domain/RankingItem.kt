package com.tekun.quizzapp.domain

import com.tekun.quizzapp.data.database.entities.RankingEntity

data class RankingItem(
    val name: String,
    val ranking: String
)

fun RankingEntity.toDomain() = RankingItem(name, ranking)
