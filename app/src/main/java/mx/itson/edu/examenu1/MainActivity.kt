package mx.itson.edu.examenu1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Locale
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etC: EditText
    private lateinit var etF: EditText
    private lateinit var etK: EditText

    private lateinit var btnGC: Button
    private lateinit var btnGF: Button
    private lateinit var btnGK: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etC = findViewById(R.id.etC)
        etF = findViewById(R.id.etF)
        etK = findViewById(R.id.etK)

        btnGC = findViewById(R.id.btnGC)
        btnGF = findViewById(R.id.btnGF)
        btnGK = findViewById(R.id.btnGK)

        btnGC.setOnClickListener { convertFromCelsius() }
        btnGF.setOnClickListener { convertFromFahrenheit() }
        btnGK.setOnClickListener { convertFromKelvin() }
    }

    private fun convertFromCelsius() {
        val c = readDecimal(etC) ?: return
        val f = (c * 1.8) + 32.0
        val k = c + 273.15

        setDecimal(etF, f)
        setDecimal(etK, k)
    }

    private fun convertFromFahrenheit() {
        val f = readDecimal(etF) ?: return
        val c = (f - 32.0) / 1.8
        val k = (5.0 * (f - 32.0) / 9.0) + 273.15

        setDecimal(etC, c)
        setDecimal(etK, k)
    }

    private fun convertFromKelvin() {
        val k = readDecimal(etK) ?: return
        val c = k - 273.15
        val f = (9.0 * (k - 273.15) / 5.0) + 32.0

        setDecimal(etC, c)
        setDecimal(etF, f)
    }

    private fun readDecimal(editText: EditText): Double? {
        val raw = editText.text?.toString()?.trim().orEmpty()
        if (raw.isEmpty()) {
            toast("Ingresa un valor decimal (ej. 56.80)")
            editText.requestFocus()
            return null
        }

        val normalized = raw.replace(',', '.')
        val value = normalized.toDoubleOrNull()
        if (value == null) {
            toast("Valor inválido. Usa formato decimal (ej. 56.80)")
            editText.requestFocus()
            return null
        }
        return value
    }

    private fun setDecimal(editText: EditText, value: Double) {
        editText.setText(String.format(Locale.US, "%.2f", value))
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
