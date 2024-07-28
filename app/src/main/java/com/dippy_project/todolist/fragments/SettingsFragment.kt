package com.dippy_project.todolist.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dippy_project.todolist.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_settings, rootKey)
    }
}