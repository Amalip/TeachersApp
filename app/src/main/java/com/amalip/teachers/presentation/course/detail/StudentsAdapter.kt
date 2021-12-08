package com.amalip.teachers.presentation.course.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amalip.teachers.databinding.UserRowBinding
import com.amalip.teachers.domain.model.User

/**
 * Created by Amalip on 12/6/2021.
 */

@SuppressLint("NotifyDataSetChanged")
class StudentsAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<User> = mutableListOf()
    private lateinit var listener: (user: User) -> Unit

    fun addData(list: List<User>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    fun setListener(listener: (User) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderItem(
        UserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderItem).bind(list[position], listener)

    override fun getItemCount() = list.size

    class ViewHolderItem(private val binding: UserRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: User, listener: (user: User) -> Unit) {
            binding.apply {
                user = data
                mcStudent.setOnClickListener { listener(data) }
            }
        }
    }

}