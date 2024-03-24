import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.calculator.R
import com.example.calculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this // Set the lifecycle owner for LiveData

        binding.input.text = "" // Initialize input field

        // Button click listeners
        binding.buttonClear.setOnClickListener {
            clearInputAndOutput()
        }
        binding.buttonBracket.setOnClickListener {
            addToInputText("(")
        }
        binding.buttonBracketR.setOnClickListener {
            addToInputText(")")
        }
        binding.buttonClear.setOnClickListener {
            removeLastInput()
        }
        binding.button0.setOnClickListener {
            addToInputText("0")
        }
        binding.button1.setOnClickListener {
            addToInputText("1")
        }
        binding.button2.setOnClickListener {
            addToInputText("2")
        }
        binding.button3.setOnClickListener {
            addToInputText("3")
        }
        binding.button4.setOnClickListener {
            addToInputText("4")
        }
        binding.button5.setOnClickListener {
            addToInputText("5")
        }
        binding.button6.setOnClickListener {
            addToInputText("6")
        }
        binding.button7.setOnClickListener {
            addToInputText("7")
        }
        binding.button8.setOnClickListener {
            addToInputText("8")
        }
        binding.button9.setOnClickListener {
            addToInputText("9")
        }
        binding.buttonDot.setOnClickListener {
            addToInputText(".")
        }
        binding.buttonDivision.setOnClickListener {
            addToInputText("÷") // ALT + 0247
        }
        binding.buttonMultiplication.setOnClickListener {
            addToInputText("×") // ALT + 0215
        }
        binding.buttonSubtraction.setOnClickListener {
            addToInputText("-")
        }
        binding.buttonAddition.setOnClickListener {
            addToInputText("+")
        }
        binding.buttonEquals.setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String) {
        binding.input.text = binding.input.text.toString() + buttonValue
    }

    private fun removeLastInput() {
        val currentInput = binding.input.text.toString()
        if (currentInput.isNotEmpty()) {
            binding.input.text = currentInput.dropLast(1)
        }
    }

    private fun clearInputAndOutput() {
        binding.input.text = ""
        binding.output.text = ""
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = evaluateExpression(expression)
            if (result.isNaN()) {
                // Show Error Message
                binding.output.text = ""
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                val formattedResult = DecimalFormat("0.######").format(result)
                binding.output.text = formattedResult
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            // Show Error Message
            binding.output.text = ""
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }

    // Function to evaluate the expression
    private fun evaluateExpression(expression: String): Double {
        // Implement your expression evaluation logic here
        // For example, you can use a library or write your own code
        // This is just a placeholder
        return expression.toDouble() // Placeholder implementation
    }
}
