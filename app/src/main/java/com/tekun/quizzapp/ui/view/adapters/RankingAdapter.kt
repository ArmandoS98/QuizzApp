package com.tekun.quizzapp.ui.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tekun.quizzapp.databinding.ItemRankingBinding
import com.tekun.quizzapp.domain.RankingItem

class RankingAdapter(val context: Context?) :
    RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    private var rankingList: List<RankingItem> = listOf()

    fun setRanking(rankingList: List<RankingItem>) {
        this.rankingList = rankingList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRankingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = rankingList[position].name
        holder.binding.tvScore.text = rankingList[position].ranking
    }

    override fun getItemCount() = rankingList.size

    inner class ViewHolder(val binding: ItemRankingBinding) : RecyclerView.ViewHolder(binding.root)

}
