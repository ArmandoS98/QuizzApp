package com.tekun.quizzapp.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tekun.quizzapp.R
import com.tekun.quizzapp.databinding.FragmentAboutBinding


class AboutFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgGithub.setOnClickListener(this)
        binding.imgLinkedin.setOnClickListener(this)
    }

    private fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgGithub -> {
                openBrowser("https://github.com/ArmandoS98")
            }
            R.id.imgLinkedin -> {
                openBrowser("https://www.linkedin.com/in/armando-santos-456740189/")
            }
        }
    }
}