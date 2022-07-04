package com.example.unit_convertor

import MapperConverter
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.unit_convertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var result: Float = 0.0f
    private lateinit var mapperConverter: MapperConverter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        mapperConverter = MapperConverter()

        dropDownMenuConvertType()
    }

    private fun getConverterValues(converterType: String):
            List<String> = mapperConverter.getConverterValue(converterType = converterType)


    private fun calculateResult(
        converterType: String,
        convertFrom: String,
        convertTo: String,
        amount: Double = 0.0
    ) {
        result = mapperConverter.convert(
            converterType = converterType,
            convertFrom = convertFrom,
            convertTo = convertTo,
            amount = amount
        ).toBigDecimal().toFloat()
    }

    private fun dropDownMenuConvertType() {
        ArrayAdapter(
            applicationContext.applicationContext,
            R.layout.drobdown_item,
            ConverterUtil.CONVERTER_TYPES
        ).also {
            binding.autoCompleteTextView.apply {
                setAdapter(it)
                setOnItemClickListener { _, _, i, _ ->
                    dropDownSubType(ConverterUtil.CONVERTER_TYPES[i], mapperConverter)
                }
            }
        }
    }

    private fun dropDownSubType(convertType: String, mapperConverter: MapperConverter) {
        ArrayAdapter(
            applicationContext.applicationContext,
            R.layout.drobdown_item,
            mapperConverter.getConverterValue(convertType)
        ).also { adapter ->
            binding.autoCompleteTextView1.apply {
                setAdapter(adapter)
                setOnItemClickListener { _, _, i, _ ->
                    dropDownValueConverted(convertType, i, mapperConverter)
                }
            }
        }
    }

    private fun dropDownValueConverted(convertType: String, convertSubType: Int, mapperConverter: MapperConverter) {
        ArrayAdapter(
            applicationContext.applicationContext,
            R.layout.drobdown_item,
            mapperConverter.getConverterValue(convertType)
        ).also {adapter->
            binding.autoCompleteTextView2.apply {
                setAdapter(adapter)
                setOnItemClickListener { _, _, i, _ ->
                    binding.btConvert.setOnClickListener {
                        calculateResult(
                            converterType = convertType,
                            convertFrom = getConverterValues(convertType)[convertSubType],
                            convertTo = getConverterValues(convertType)[i],
                            amount = binding.edValue.text.toString().toDouble()
                        )
                        binding.tvValueConverted.text = result.toString()
                        binding.tvTypeConverted.text = getConverterValues(convertType)[i]
                    }
                }
            }
        }
    }


}