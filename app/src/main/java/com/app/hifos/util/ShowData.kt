package com.app.hifos.util

import android.content.Context
import android.widget.TableLayout
import android.widget.TableRow
import com.app.hifos.todo.TodoList

class ShowData {
    companion object {
        fun showData(
            ct: Context, body: TableLayout, todoList: ArrayList<TodoList>, searchId: String
        ) {
            for (todo in todoList) {
                if (searchId.isNotEmpty() && searchId != todo.userId)
                    continue

                val newRow = TableRow(ct)
                newRow.addView(SetTextView.setText(ct, todo.userId))
                newRow.addView(SetTextView.setText(ct, todo.todoContent))
                newRow.addView(SetTextView.setText(ct, todo.isCompleted))
                newRow.addView(SetTextView.setText(ct, todo.regDate))

                body.addView(newRow)
            }
        }
    }
}