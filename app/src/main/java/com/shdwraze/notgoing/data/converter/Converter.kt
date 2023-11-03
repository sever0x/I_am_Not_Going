package com.shdwraze.notgoing.data.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class Converter {

    @TypeConverter
    fun fromLocalDate(value: LocalDate): String {
        return value.toString()
    }

    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value)
    }
}