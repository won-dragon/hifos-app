package com.app.hifos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import androidx.fragment.app.Fragment
import com.app.hifos.databinding.FragmentAllResultBinding
import com.app.hifos.util.SetTextView
import com.app.hifos.util.ShowData
import kf.hifos.edu.todo.TodoList
import org.json.JSONArray


class AllResult : Fragment() {
    private var _binding: FragmentAllResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var ct: Context
    private lateinit var todoList: ArrayList<TodoList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        todoList = setListData()

        if (container != null)
            ct = container.context

        _binding = FragmentAllResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHeader()
        ShowData.showData(ct, binding.todoList, todoList, "")
    }

    private fun setHeader() {
        val newRow = TableRow(ct)
        newRow.addView(SetTextView.setText(ct, "ID"))
        newRow.addView(SetTextView.setText(ct, "메세지"))
        newRow.addView(SetTextView.setText(ct, "완료"))
        newRow.addView(SetTextView.setText(ct, "등록시간"))

        binding.todoList.addView(newRow)
    }

    private fun setListData(): ArrayList<TodoList> {
        val jsonList = JSONArray(testList)
        val todoList = arrayListOf<TodoList>()

        for (count in 0 until jsonList.length()) {
            val name = jsonList.getJSONObject(count).optString("USERID")
            val todoContent = jsonList.getJSONObject(count).optString("TODOCONTENT")
            val isCompleted = jsonList.getJSONObject(count).optString("ISCOMPLETED")
            val regDate = jsonList.getJSONObject(count).optString("REGDATE")

            val todo = TodoList(name, todoContent, isCompleted, regDate)
            todoList.add(todo)
        }

        return todoList
    }

    private val testList = """
        [
            {
                "USERID" : "KIM",
                "TODOCONTENT" : "할일 마ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㄶ음",
                "ISCOMPLETED" : "Y",
                "REGDATE" : "2020-10-10"
            },
            {
                "USERID" : "HONG",
                "TODOCONTENT" : "할일 마ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㄶ음",
                "ISCOMPLETED" : "Y",
                "REGDATE" : "2020-10-10"
            },
            {
                "USERID" : "HWANG",
                "TODOCONTENT" : "할일 마ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㄶ음",
                "ISCOMPLETED" : "Y",
                "REGDATE" : "2020-10-10"
            }
        ]
    """.trimIndent()
}