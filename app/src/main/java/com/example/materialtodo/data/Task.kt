package com.example.materialtodo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Task : Parcelable {
    var title: String = ""
    var description: String = ""
    var isCompleted: Boolean = false
    var priority: Priority = Priority.LOW

    override fun toString(): String {
        return "Task(title='$title', description='$description', isCompleted=$isCompleted, priority=$priority)"
    }

    fun copy(
        title: String = this.title,
        description: String = this.description,
        isCompleted: Boolean = this.isCompleted,
        priority: Priority = this.priority
    ): Task {
        val newTask = Task()
        newTask.title = title
        newTask.description = description
        newTask.isCompleted = isCompleted
        newTask.priority = priority
        return newTask
    }
}

enum class Priority(val value: String) {
    LOW("\uD83D\uDFE9"),
    MEDIUM("\uD83D\uDFE8"),
    HIGH("\uD83D\uDFE5")
}