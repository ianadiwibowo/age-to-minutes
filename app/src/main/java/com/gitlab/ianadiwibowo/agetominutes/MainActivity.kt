package com.gitlab.ianadiwibowo.agetominutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDate.setOnClickListener { _ ->
            clickSelectDate()
        }
    }

    private fun clickSelectDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                setDatePicker(selectedDay, selectedMonth, selectedYear)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = Date().time
        datePickerDialog.show()
    }

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    private fun setDatePicker(selectedDay: Int, selectedMonth: Int, selectedYear: Int) {
        val (birthDate, birthDateInMinutes) = getDate(selectedDay, selectedMonth, selectedYear)

        updateSelectedDateText(birthDate)
        updateAgeInMinutesText(currentDateInMinutes() - birthDateInMinutes)
    }

    private fun getDate(selectedDay: Int, selectedMonth: Int, selectedYear: Int): Pair<Date, Long> {
        val date = getSelectedDate(selectedDay, selectedMonth, selectedYear)
        val dateInMinutes = convertToMinute(date)
        return Pair(date, dateInMinutes)
    }

    private fun getSelectedDate(selectedDay: Int, selectedMonth: Int, selectedYear: Int): Date {
        val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        return dateFormat.parse(selectedDate) as Date
    }

    private fun convertToMinute(date: Date?): Long {
        return date!!.time / 60_000
    }

    private fun currentDateInMinutes(): Long {
        val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
        return convertToMinute(currentDate)
    }

    private fun updateSelectedDateText(birthDate: Date) {
        tvSelectedDate.text = birthDate.toString()
    }

    private fun updateAgeInMinutesText(ageInMinutes: Long) {
        tvAgeInMinutes.text = ageInMinutes.toString()
    }
}
