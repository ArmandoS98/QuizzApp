package com.tekun.quizzapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tekun.quizzapp.databinding.FragmentQuizzesBinding
import com.tekun.quizzapp.model.QuizzesModel
import com.tekun.quizzapp.view.adapters.MenuAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [QuizzesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizzesFragment : Fragment() {
    private var _binding: FragmentQuizzesBinding? = null
    private val binding get() = _binding
    lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizzesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = listOf(
            QuizzesModel(
                "Horizon Zero Down",
                "",
                "",
                10,
                "https://opentdb.com/api.php?amount=20&category=15&difficulty=easy&type=multiple"
            ), QuizzesModel(
                "Horizon Zero Down",
                "",
                "",
                10,
                "https://opentdb.com/api.php?amount=20&category=15&difficulty=easy&type=multiple"
            )
        )
        MenuAdapter(context).setMenu(temp)

        //Metodo el recyclerview
        recyclerInit(temp)
    }

    private fun recyclerInit(temp: List<QuizzesModel>) {
        adapter = MenuAdapter(context)
        adapter.setMenu(temp)
        binding?.recMenu?.layoutManager =
            LinearLayoutManager(context/*, RecyclerView.HORIZONTAL, false*/)

        binding?.recMenu?.adapter = adapter
    }
}