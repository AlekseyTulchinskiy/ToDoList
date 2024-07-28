package com.dippy_project.todolist.fragments

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun onClickNew()
}