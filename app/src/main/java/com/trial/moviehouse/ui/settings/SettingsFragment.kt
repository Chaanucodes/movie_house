package com.trial.moviehouse.ui.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.trial.moviehouse.MainViewModel
import com.trial.moviehouse.R
import com.trial.moviehouse.data.db.LocalPreferences
import com.trial.moviehouse.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel : SettingsViewModel by viewModels()
    private val mainViewModel : MainViewModel by viewModels()
    private var binding : FragmentSettingsBinding? = null
    private var isDarkMode :Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageDarkThemeDrawable()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding?.btnChangeTheme?.setOnClickListener {_->
            viewLifecycleOwner.lifecycleScope.launch {
                isDarkMode?.let {
                    if (it) {
                        mainViewModel.saveThemePreferences(AppCompatDelegate.MODE_NIGHT_NO)
                    } else {
                        mainViewModel.saveThemePreferences(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }


            }
        }
    }

    private fun manageDarkThemeDrawable() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.isDarkMode.collectLatest {
                isDarkMode = it == AppCompatDelegate.MODE_NIGHT_YES
                if (isDarkMode!!) {
                    binding?.btnChangeTheme?.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_dark_mode, null),
                        null
                    )
                } else {
                    binding?.btnChangeTheme?.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_light_mode, null),
                        null
                    )
                }
            }
        }
    }

}