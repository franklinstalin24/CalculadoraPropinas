package com.example.calculadorapropinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadorapropinas.ui.theme.CalculadoraPropinasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraPropinasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Contenedor principal de la aplicación
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Calculadora()
                    }
                }
            }
        }
    }
}

@Composable
fun Calculadora() {

    // Variables de estado para los campos de texto
    var valorCuenta by remember { mutableStateOf("") } // Almacena el valor de la cuenta ingresado por el usuario
    var porcentajePropina by remember { mutableStateOf("") } // Almacena el porcentaje de propina ingresado por el usuario
    var resultado by remember { mutableStateOf("Resultado: $0.00") } // Almacena el resultado del cálculo de la propina

    // Estructura de la interfaz de usuario
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de la aplicación
        Text(text = "Calculadora de Propinas", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        // Campo de texto para valor de la cuenta
        TextField(
            value = valorCuenta,
            onValueChange = { valorCuenta = it }, // Actualiza el valor de la cuenta cada vez que el usuario ingresa un nuevo valor
            label = { Text("Valor de la cuenta") }, // Etiqueta para el campo de texto
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp)) // Espacio entre los campos de texto

        // Campo de texto para porcentaje de propina
        TextField(
            value = porcentajePropina,
            onValueChange = { porcentajePropina = it }, // Actualiza el porcentaje de propina cada vez que el usuario ingresa un nuevo valor
            label = { Text("Porcentaje de propina (%)") }, // Etiqueta para el campo de texto
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp)) // Espacio antes del botón de cálculo

        // Botón para ejecutar el cálculo
        Button(
            onClick = {
                val cuenta = valorCuenta.toDoubleOrNull() ?: 0.0 // Convierte el valor de la cuenta a Double
                val porcentaje = porcentajePropina.toDoubleOrNull() ?: 0.0 // Convierte el porcentaje de propina a Double
                val propina = cuenta * (porcentaje / 100) // Calcula la propina multiplicando el valor de la cuenta por el porcentaje dividido entre 100 val total = cuenta + propina // Calcula el total sumando el valor de la cuenta y la propina
                resultado = "Resultado: $${"%.2f".format(propina)}" // Actualiza el resultado con el valor de la propina formateado a dos decimales
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }
        Spacer(modifier = Modifier.height(20.dp)) // Espacio antes de mostrar el resultado

        // Texto donde se muestra el resultado
        Text(text = resultado, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    CalculadoraPropinasTheme {
        Calculadora()
    }
}