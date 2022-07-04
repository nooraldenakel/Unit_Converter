import com.example.unit_convertor.ConverterUtil


class MapperConverter {
    private val area = mapOf(
        "Square Kilometre" to 1e-6,
        "Square Metre" to 1.0,
        "Square Mile" to 3.861e-7,
        "Square Yard" to 1.19599,
        "Square Foot" to 10.7639,
        "Square Inch" to 1550.0,
        "Hectare" to 1e-4,
    )

    private val digitalStorage = mapOf(
        "Bit" to 8.0,
        "Kilobit" to 0.008,
        "Megabit" to 8e-6,
        "Gigabit" to 8e-9,
        "Terabit" to 8e-12,
        "Byte" to 1.0,
        "Kilobyte" to 0.001,
        "Megabyte" to 1e-6,
        "Gigabyte" to 1e-9,
        "Terabyte" to 1e-12,

        )

    private val length = mapOf(
        "Kilometre" to 0.001,
        "Metre" to 1.0,
        "Centimetre" to 100.0,
        "Millimetre" to 1000.0,
        "Micrometre" to 1e+6,
        "Nanometre" to 1e+9,
        "Mile" to 0.000621371,
        "Yard" to 1.09361,
        "Foot" to 3.28084,
        "Inch" to 0.0254
    )

    private val speed = mapOf(
        "Miles per hour" to 1.0,
        "Foot per second" to 1.4667,
        "Metre per second" to 0.44704,
        "Kilometre per hour" to 1.60934,
        "Knot" to 0.868976,
    )

    private val weight = mapOf(
        "Kilogram" to 0.001,
        "Gram" to 1.0,
        "Milligram" to 1000.0,
        "US ton" to 1.1023e-6,
        "Pound" to 0.00220462,
        "Ounce" to 0.035274,
    )

    fun convert(
        converterType: String,
        convertFrom: String,
        convertTo: String,
        amount: Double
    ): Double = when (converterType) {
        ConverterUtil.MainConverter.area -> amount * area[convertTo]!! / area[convertFrom]!!
        ConverterUtil.MainConverter.digital_Storage -> amount * digitalStorage[convertTo]!! / digitalStorage[convertFrom]!!
        ConverterUtil.MainConverter.length -> amount * length[convertTo]!! / length[convertFrom]!!
        ConverterUtil.MainConverter.speed -> amount * speed[convertTo]!! / speed[convertFrom]!!
        ConverterUtil.MainConverter.weight -> amount * weight[convertTo]!! / weight[convertFrom]!!
        ConverterUtil.MainConverter.temperature -> {
            val celsius = mapOf(
                "Celsius" to amount * 1.0,
                "Fahrenheit" to (amount * 9 / 5) + 32,
                "Kelvin" to amount + 273.15
            )
            val fahrenheit = mapOf(
                "Celsius" to (amount - 32) * 5 / 9,
                "Fahrenheit" to amount * 1.0,
                "Kelvin" to ((amount - 32) * 5 / 9) + 273.15
            )
            val kelvin = mapOf(
                "Celsius" to amount - 273.15,
                "Fahrenheit" to ((amount - 273.15) * 9 / 5) + 32,
                "Kelvin" to amount * 1.0
            )
            when (convertFrom) {
                "Celsius" -> celsius[convertTo]!!
                "Fahrenheit" -> fahrenheit[convertTo]!!
                "Kelvin" -> kelvin[convertTo]!!
                else -> 0.0
            }
        }
        else -> 0.0
    }


    fun getConverterValue(
        converterType: String
    ): List<String> = when (converterType) {
        ConverterUtil.MainConverter.area -> area.keys
        ConverterUtil.MainConverter.digital_Storage -> digitalStorage.keys
        ConverterUtil.MainConverter.length -> length.keys
        ConverterUtil.MainConverter.speed -> speed.keys
        ConverterUtil.MainConverter.weight -> weight.keys
        ConverterUtil.MainConverter.temperature -> setOf("Celsius", "Fahrenheit", "Kelvin")
        else -> setOf()
    }.toList()

}