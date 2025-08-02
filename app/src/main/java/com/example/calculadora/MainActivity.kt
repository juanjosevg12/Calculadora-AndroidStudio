package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    lateinit var txt_historial: TextView
    lateinit var txt_resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        txt_resultado = findViewById(R.id.txt_resultado)
        txt_historial = findViewById(R.id.txt_historial)
    }

    var numero_uno: Float = 0F
    var numero_dos: Float = 0F
    var resultado: Float = 0F
    var historial = ""
    var operacion = ""

    fun clickBoton(v: View) {
        val boton = v as Button
        val textoBoton = boton.text.toString()

        if (textoBoton == "+" || textoBoton == "-" || textoBoton == "X" || textoBoton == "%" || textoBoton == "/" ||
            textoBoton == "cos" || textoBoton == "sin" || textoBoton == "tan" || textoBoton == "log") {

            operacion = textoBoton
            numero_uno = txt_resultado.text.toString().toFloatOrNull() ?: 0F

            txt_historial.text = when (textoBoton) {
                "cos" -> "cos($numero_uno)"
                "sin" -> "sin($numero_uno)"
                "tan" -> "tan($numero_uno)"
                "log" -> "log base ? de $numero_uno"
                else -> "$numero_uno $operacion"
            }

            txt_resultado.text = ""

        } else if (textoBoton == "CE") {
            txt_resultado.text = "0"
        } else if (textoBoton == "C") {
            txt_resultado.text = "0"
            txt_historial.text = ""
        } else if (textoBoton == "+/-") {
            val numeroActual = txt_resultado.text.toString().toFloatOrNull()
            if (numeroActual != null) {
                txt_resultado.text = "${numeroActual * -1}"
            }
        } else if (textoBoton == "=") {
            numero_dos = txt_resultado.text.toString().toFloatOrNull() ?: 0F
            when (operacion) {
                "+" -> {
                    resultado = numero_uno + numero_dos
                    historial = "$numero_uno $operacion $numero_dos"
                }
                "-" -> {
                    resultado = numero_uno - numero_dos
                    historial = "$numero_uno $operacion $numero_dos"
                }
                "X" -> {
                    resultado = numero_uno * numero_dos
                    historial = "$numero_uno $operacion $numero_dos"
                }
                "/" -> {
                    if (numero_dos != 0F) {
                        resultado = numero_uno / numero_dos
                        historial = "$numero_uno $operacion $numero_dos"
                    } else {
                        resultado = 0F
                        historial = "Error: no se puede dividir por 0"
                    }
                }
                "%" -> {
                    resultado = numero_uno % numero_dos
                    historial = "$numero_uno $operacion $numero_dos"
                }
                "cos" -> {
                    resultado = cos(Math.toRadians(numero_uno.toDouble())).toFloat()
                    historial = "cos($numero_uno)"
                }
                "sin" -> {
                    resultado = sin(Math.toRadians(numero_uno.toDouble())).toFloat()
                    historial = "sin($numero_uno)"
                }
                "tan" -> {
                    resultado = tan(Math.toRadians(numero_uno.toDouble())).toFloat()
                    historial = "tan($numero_uno)"
                }
                "log" -> {
                    if (numero_uno > 0 && numero_dos > 0 && numero_dos != 1F) {
                        resultado = (ln(numero_uno.toDouble()) / ln(numero_dos.toDouble())).toFloat()
                        historial = "log base $numero_dos de $numero_uno"
                    } else {
                        resultado = 0F
                        historial = "Error: base o número inválido"
                    }
                }
            }

            txt_resultado.text = "$resultado"
            txt_historial.text = historial

        } else {
            if (txt_resultado.text.toString() == "0") {
                txt_resultado.text = textoBoton
            } else {
                txt_resultado.text = "${txt_resultado.text}$textoBoton"
            }
        }
    }
}
