package com.tekun.quizzapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tekun.quizzapp.R
import com.tekun.quizzapp.data.model.QuizzesModel
import com.tekun.quizzapp.databinding.FragmentQuizzBinding
import com.tekun.quizzapp.databinding.FragmentRankingBinding
import com.tekun.quizzapp.domain.RankingItem
import com.tekun.quizzapp.ui.extensions.loadByInternet
import com.tekun.quizzapp.ui.view.adapters.MenuAdapter
import com.tekun.quizzapp.ui.view.adapters.RankingAdapter
import com.tekun.quizzapp.ui.viewmodel.RankingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : Fragment() {
    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!
    private val rankingViewModel: RankingViewModel by viewModels()
    lateinit var adapter: RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rankingViewModel.onCreate()

        rankingViewModel.rankingModel.observe(viewLifecycleOwner) { ranking ->
            if (ranking.isEmpty()) {
                binding.tvRanking.visibility = VISIBLE
            } else {
                binding.tvRanking.visibility = GONE
                RankingAdapter(context).setRanking(ranking)
                recyclerInit(ranking)
            }
        }
    }

    private fun recyclerInit(temp: List<RankingItem>) {
        adapter = RankingAdapter(context)
        adapter.setRanking(temp)
        binding.reciclerviewRankings.layoutManager =
            LinearLayoutManager(context)

        binding.reciclerviewRankings.adapter = adapter
    }
}