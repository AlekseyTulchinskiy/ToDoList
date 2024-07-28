package com.dippy_project.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dippy_project.todolist.entities.LibraryItem
import com.dippy_project.todolist.entities.NoteItem
import com.dippy_project.todolist.entities.ShoppingListItem
import com.dippy_project.todolist.entities.ShoppingListName

@Database(
    entities = [LibraryItem::class, NoteItem::class, ShoppingListItem::class, ShoppingListName::class],
    version = 1,
    exportSchema = true
)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MainDataBase? = null

        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "shopping_list.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}