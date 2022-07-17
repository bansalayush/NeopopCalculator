package com.scorpio.neopopcalculator

import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import club.cred.neopop.PopFrameLayout
import com.scorpio.neopopcalculator.databinding.ActivityCalculatorBinding


class MainActivity : AppCompatActivity() {
    var binding: ActivityCalculatorBinding? = null
    var ACTION: Char? = null
    private var val1 = Double.NaN
    private var val2 = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        with(binding!!) {
            button0.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("0")
            }
            button1.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("1")
            }
            button2.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("2")
            }
            button3.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("3")
            }
            button4.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("4")
            }
            button5.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("5")
            }
            button6.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("6")
            }
            button7.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("7")
            }
            button8.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("8")
            }
            button9.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                setNumberInTextView("9")
            }

            buttonDot.setOnClickListener {
                exceedLength()
                setNumberInTextView(".")
            }

            buttonPara1.setOnClickListener {
                arithematicOperation(MODULUS)
            }

            buttonAdd.setOnClickListener {
                arithematicOperation(ADDITION)
            }

            buttonSub.setOnClickListener {
                arithematicOperation(SUBTRACTION)
            }

            buttonDivide.setOnClickListener {
                arithematicOperation(DIVISION)
            }

            buttonMulti.setOnClickListener {
                arithematicOperation(MULTIPLICATION)
            }

            buttonEqual.setOnClickListener {
                binding?.let {
                    if (it.input.text.isNotEmpty()) {
                        operation()
                        ACTION = EQU
                        if (!ifReallyDecimal()) {
                            it.output.setText(val1.toString())
                        } else {
                            it.output.setText(val1.toInt().toString())
                        }
                        it.input.setText(null)
                    } else {
                        it.output.setText("Error")
                    }
                }
            }

            buttonClear.setOnClickListener {
                binding?.let {
                    if (it.input.text.isNotEmpty()) {
                        val name: CharSequence = it.input.text.toString()
                        it.input.text = name.subSequence(0, name.length - 1)
                    } else {
                        val1 = Double.NaN
                        val2 = Double.NaN
                        it.input.text = ""
                        it.output.text = ""
                    }
                }
            }

            buttonClear.setOnLongClickListener {
                val1 = Double.NaN;
                val2 = Double.NaN;
                binding?.let {
                    it.input.setText("")
                    it.output.setText("")
                }
                true
            }
        }

    }

    private fun arithematicOperation(operation: Char) {
        binding?.let {
            if (it.input.text.isNotEmpty()) {
                ACTION = operation
                operation()
                if (!ifReallyDecimal()) {
                    it.output.text = "$val1$operation"
                } else {
                    it.output.text = "${val1.toInt()}$operation"
                }
                it.input.text = null
            } else {
                it.output.text = "Error"
            }
        }
    }

    // Remove error message that is already written there.
    fun ifErrorOnOutput() {
        if (binding?.output?.text!! == "Error") {
            binding!!.output.text = ""
        }
    }

    // Make text small if too many digits.
    private fun exceedLength() {
        binding?.let {
            if (it.input.text.length > 10) {
                it.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            }
        }
    }

    fun setNumberInTextView(number: String) {
        binding?.let {
            it.input.text = "${it.input.text}${number}"
        }
    }

    private fun operation() {
        binding?.let {
            if (!java.lang.Double.isNaN(val1)) {
                if (it.output.text.toString()[0] == '-') {
                    val1 *= -1
                }
                val2 = it.input.text.toString().toDouble()
                when (ACTION) {
                    ADDITION -> val1 += val2
                    SUBTRACTION -> val1 -= val2
                    MULTIPLICATION -> val1 = val1 * val2
                    DIVISION -> val1 = val1 / val2
                    EXTRA -> val1 = -1 * val1
                    MODULUS -> val1 = val1 % val2
                    EQU -> {}
                }
            } else {
                val1 = it.input.text.toString().toDouble()
            }
        }
    }

    // Whether value if a double or not
    private fun ifReallyDecimal(): Boolean {
        return val1 == val1.toInt().toDouble()
    }

    companion object {
        private const val ADDITION = '+'
        private const val SUBTRACTION = '-'
        private const val MULTIPLICATION = '*'
        private const val DIVISION = '/'
        private const val EQU = '='
        private const val EXTRA = '@'
        private const val MODULUS = '%'
    }
}