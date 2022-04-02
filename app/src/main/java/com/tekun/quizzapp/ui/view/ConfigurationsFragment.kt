package com.tekun.quizzapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import com.tekun.quizzapp.data.sharedpreferences.PreferencesKey
import com.tekun.quizzapp.data.sharedpreferences.PreferencesProvider
import com.tekun.quizzapp.databinding.FragmentConfigurationsBinding


class ConfigurationsFragment : Fragment() {
    private var _binding: FragmentConfigurationsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConfigurationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set status
        val statusMusic = PreferencesProvider.bool(requireContext(), PreferencesKey.TURNSOUND)
        binding.switch1.isChecked = statusMusic
        //set progress
        val progress = PreferencesProvider.int(requireContext(), PreferencesKey.LEVEL_VOLUME)
        if (progress != null) {
            binding.tvProgress.text = "${progress}%"
            binding.seekBar.progress = progress
        }

        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvProgress.text = "${progress}%"
                PreferencesProvider.set(requireContext(), PreferencesKey.LEVEL_VOLUME, progress)
            }
        })

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            PreferencesProvider.set(requireContext(), PreferencesKey.TURNSOUND, isChecked)
        }
    }
}