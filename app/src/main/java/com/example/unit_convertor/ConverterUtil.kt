package com.example.unit_convertor

object ConverterUtil {
    object MainConverter {
        const val area = "Area"
        const val digital_Storage = "Digital Storage"
        const val length = "Length"
        const val speed = "Speed"
        const val temperature = "Temperature"
        const val weight = "Weight"
    }


    val CONVERTER_TYPES = listOf(
        MainConverter.area,
        MainConverter.digital_Storage,
        MainConverter.length,
        MainConverter.speed,
        MainConverter.temperature,
        MainConverter.weight,
    )
}