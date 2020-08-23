package com.gitlab.ianadiwibowo.agetominutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDate.setOnClickListener { view ->
            clickDataPicker(view)
        }
    }

    private fun clickDataPicker(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(
                    this,
                    "DatePicker works! $year $month $dayOfMonth",
                    Toast.LENGTH_SHORT
                ).show()
            },
            year,
            month,
            day
        ).show()
    }
}
