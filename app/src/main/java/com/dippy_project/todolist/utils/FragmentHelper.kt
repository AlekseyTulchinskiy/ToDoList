package com.dippy_project.todolist.utils

import androidx.appcompat.app.AppCompatActivity
import com.dippy_project.todolist.fragments.BaseFragment
import com.dippy_project.todolist.R

object FragmentHelper {
    var currentFragment: BaseFragment? = null

    fun setFragment(newFragment: BaseFragment, act: AppCompatActivity) {
        val transaction = act.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeHolder, newFragment)
        transaction.commit()
        currentFragment = newFragment
    }
}