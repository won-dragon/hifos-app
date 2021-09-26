package com.app.hifos

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.app.hifos.databinding.FragmentUserResultBinding
import com.app.hifos.todo.TodoList
import com.app.hifos.util.SetTextView
import com.app.hifos.util.ShowData
import org.json.JSONArray
import java.util.*


class UserResult : Fragment() {
    private var _binding: FragmentUserResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var ct: Context
    private lateinit var todoList: ArrayList<TodoList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todoList = setListData()

        if (container != null)
            ct = container.context

        _binding = FragmentUserResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener(onClickListener)
        binding.btnAdd.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            binding.btnSearch.id -> searchData()
            binding.btnAdd.id -> executeAdd()
        }
    }

    private fun searchData() {
        if (binding.editUserName.text.isEmpty()) {
            Toast.makeText(ct, "ID룰 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }

        val userName = binding.editUserName.text.toString()

        binding.todoList.removeAllViews()

        setHeader()
        ShowData.showData(ct, binding.todoList, todoList, userName)
    }

    private fun executeAdd() {
        if (binding.editUserName.text.isEmpty()) {
            Toast.makeText(ct, "ID를 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }

        val nextIntent = Intent(ct, AddContent::class.java)
        nextIntent.putExtra("USERID", binding.editUserName.text.toString())

        resultLauncher.launch(nextIntent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    val userId = it.getStringExtra("USERID").toString()
                    val newContent = it.getStringExtra("NEWCONTENT").toString()
                    val regDate = it.getStringExtra("REGDATE").toString()

                    todoList.add(TodoList(userId, newContent, "N", regDate))

                    binding.editUserName.setText(userId)
                }
                searchData()
            }
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