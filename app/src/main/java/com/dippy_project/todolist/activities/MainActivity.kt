package com.dippy_project.todolist.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.dippy_project.todolist.R
import com.dippy_project.todolist.databinding.ActivityMainBinding
import com.dippy_project.todolist.fragments.ListFragment
import com.dippy_project.todolist.fragments.NoteFragment
import com.dippy_project.todolist.utils.FragmentHelper

class MainActivity : AppCompatActivity() {
    private lateinit var defPref: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var currentTheme: String
    private var preFragment = R.id.note

    override fun onCreate(savedInstanceState: Bundle?) {
        defPref = PreferenceManager.getDefaultSharedPreferences(this)
        setTheme(getSelectedTheme())
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        currentTheme = defPref.getString("topic_key", "dark").toString()
        setContentView(binding.root)
        setBottomNavListener()
    }

    private fun getSelectedTheme(): Int {
        return if (defPref.getString("topic_key", "dark") == "dark") {
            R.style.Theme_CheckListDarkMain
        } else {
            R.style.Theme_CheckListWhiteMain
        }
    }

    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                }
                R.id.note -> {
                    preFragment = PRE_FRAG_NOTE
                    FragmentHelper.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.item -> {
                    preFragment = PRE_FRAG_ToDoList
                    FragmentHelper.setFragment(ListFragment.newInstance(), this)
                }
                R.id.new_note -> {
                    FragmentHelper.currentFragment?.onClickNew()
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bNav.selectedItemId = preFragment
        if (currentTheme != defPref.getString("topic_key", "dark")) recreate()
    }

    companion object {
        const val PRE_FRAG_NOTE = R.id.note
        const val PRE_FRAG_ToDoList = R.id.item
    }
}