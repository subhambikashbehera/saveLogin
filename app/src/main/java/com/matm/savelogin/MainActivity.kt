package com.matm.savelogin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var difference: Long = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val dataX = sharedPreferences.getString("duration", "not logged in").toString()
        textView.text = dataX

        button2.setOnClickListener {
            textView.text = "not logged in"
            sharedPreferences.edit().clear().apply()
        }

        button.setOnClickListener {
            val data = sharedPreferences.getString("duration", "not logged in").toString()
            if (timeDurationCheck(data)) {
                val c = Calendar.getInstance().time
                val formattedDate: String =
                    SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(c)
                textView.text = formattedDate.toString()
                val editor = sharedPreferences.edit()
                editor.putString("duration", formattedDate.toString())
                editor.commit()
                editor.apply()
            } else {
                if (data == "not logged in") {
                    val c = Calendar.getInstance().time
                    val formattedDate: String =
                        SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(c)
                    textView.text = formattedDate.toString()
                    val editor = sharedPreferences.edit()
                    editor.putString("duration", formattedDate.toString())
                    editor.commit()
                    editor.apply()
                    Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "${180 - difference} days to complete 6months ",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }


    }

    private fun timeDurationCheck(date: String): Boolean {
        val c = Calendar.getInstance().time
        val formattedDate: String = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(c)
        val previousDate = if (date == "not logged in") formattedDate else date
        val oldDate = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).parse(previousDate)
        val diff = c.time - oldDate.time
        difference = diff / 1000 / 60 / 60 / 24
        return difference >= 180
    }


}


