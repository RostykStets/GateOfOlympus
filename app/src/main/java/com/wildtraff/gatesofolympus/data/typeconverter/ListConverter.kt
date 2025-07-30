package com.wildtraff.gatesofolympus.data.typeconverter

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun fromBooleanList(value: List<Boolean>): String {
        return value.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun toBooleanList(value: String): List<Boolean> {
        return value.split(",").map { it.toBoolean() }
    }
}