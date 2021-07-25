package com.example.sunba

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sunba.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.setOnClickListener { loginfunc() }

    }

    var password = ""
    private fun loginfunc() {
        password = binding.pw.text.toString()

        if (password == "sunba") {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }else{
            binding.passwordResult.text = getString(R.string.password_result, "비밀번호 오류!!")
        }

    }

    fun onLog() {
        Log.d(TAG, "MainActivity-onLog" + password)
    }
}

