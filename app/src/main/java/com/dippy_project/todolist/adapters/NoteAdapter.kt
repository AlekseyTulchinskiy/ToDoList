package com.dippy_project.todolist.adapters

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dippy_project.todolist.R
import com.dippy_project.todolist.databinding.TemplateNoteItemBinding
import com.dippy_project.todolist.entities.NoteItem
import com.dippy_project.todolist.entities.ShoppingListName
import com.dippy_project.todolist.utils.HtmlManager
import com.dippy_project.todolist.utils.TimeHelper

class NoteAdapter(private val listener: Listener, private val defPref: SharedPreferences) : ListAdapter<NoteItem, NoteAdapter.NoteHolder>(MyComparator()) {
    class NoteHolder(view: View) : ViewHolder(view) {
        private val binding = TemplateNoteItemBinding.bind(view)

        fun setData(note: NoteItem, listener: Listener, defPref: SharedPreferences) = with(binding) {
            tvTitle.text = note.title
            tvDescription.text = HtmlManager.getFromHtml(note.content)!!.trim()
            tvTime.text = TimeHelper.getTimeFormat(note.time, defPref)
            imDelete.setOnClickListener {
                listener.deleteItem(note.id!!)
            }
            itemView.setOnClickListener {
                listener.onClickItem(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.template_note_item, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.setData(getItem(position), listener, defPref)
    }

    class MyComparator(): DiffUtil.ItemCallback<NoteItem>() {
        override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun deleteItem(id: Int)
        fun onClickItem(note: NoteItem)
    }
}