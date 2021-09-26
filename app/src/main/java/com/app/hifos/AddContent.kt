package com.app.hifos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.hifos.databinding.ActivityAddContentBinding
import kf.hifos.edu.todo.TodoList
import java.text.SimpleDateFormat
import java.util.*

class AddContent : AppCompatActivity() {
    lateinit var viewBinding: ActivityAddContentBinding

    var userId = ""
    var todoList = arrayListOf<TodoList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setFinishOnTouchOutside(false)
        viewBinding = ActivityAddContentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (intent.getStringExtra("USERID")?.isNotEmpty() == true) {
            userId = intent.getStringExtra("USERID").toString()
        }

        viewBinding.btnOk.setOnClickListener(onClickListener)
        viewBinding.btnCancel.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            viewBinding.btnOk.id -> executeAdd()
            viewBinding.btnCancel.id -> closeAdd()
        }
    }

    private fun executeAdd() {
        if (viewBinding.editNewContent.text.isEmpty()) {
            Toast.makeText(this, "추가할 내용을 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }

        val newContent = viewBinding.editNewContent.text.toString()

        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ko", "KR"))
        val stringTime = dateFormat.format(date)

        todoList.add(TodoList(userId, newContent, "N", stringTime))
        val returnIntent = Intent()
        returnIntent.putExtra("USERID", userId)
        returnIntent.putExtra("NEWCONTENT", newContent)
        returnIntent.putExtra("REGDATE", stringTime)

        setResult(RESULT_OK, returnIntent)
        finish()
    }

    private fun closeAdd() {
        setResult(RESULT_CANCELED)
        finish()
    }
}