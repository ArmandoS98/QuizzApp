package com.tekun.quizzapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tekun.quizzapp.domain.QuizItem
import com.tekun.quizzapp.domain.RankingItem

@Entity(tableName = "ranking_table")
data class RankingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "ranking")
    val ranking: String
)

fun RankingItem.toDatabase() = RankingEntity(name = name, ranking = ranking)
