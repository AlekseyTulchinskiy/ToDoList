package com.dippy_project.todolist.activities

import android.app.Application
import com.dippy_project.todolist.db.MainDataBase

class MainApp : Application() {
    val database by lazy { MainDataBase.getDataBase(this) }
}