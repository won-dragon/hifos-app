package com.app.hifos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.hifos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnAll.setOnClickListener(onClickListener)
        viewBinding.btnUser.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            viewBinding.btnAll.id -> executeAll()
            viewBinding.btnUser.id -> executeUser()
        }
    }

    private fun executeAll() {
        removeFragment()

        val newTransaction = supportFragmentManager.beginTransaction()
        newTransaction.add(viewBinding.frameLayout.id, AllResult())
        newTransaction.addToBackStack(null)
        newTransaction.commit()
    }

    private fun executeUser() {
        removeFragment()

        val newTransaction = supportFragmentManager.beginTransaction()
        newTransaction.add(R.id.frameLayout, UserResult())
        newTransaction.addToBackStack(null)
        newTransaction.commit()
    }

    private fun removeFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val frameLayout = supportFragmentManager.findFragmentById(viewBinding.frameLayout.id)
        if (frameLayout != null)
            transaction.remove(frameLayout)
        transaction.commit()
    }
}