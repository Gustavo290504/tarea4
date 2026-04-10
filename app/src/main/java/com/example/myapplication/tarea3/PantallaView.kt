package com.example.myapplication.tarea3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.R

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "input") {
        composable("input") {
            InputScreen(onNavigate = { year ->
                navController.navigate("resultado/$year")
            })
        }
        composable(
            route = "resultado/{year}",
            arguments = listOf(navArgument("year") { type = NavType.IntType })
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getInt("year") ?: 0
            ResultadoScreen(year,
                onBack = { navController.popBackStack() })
        }
    }
}

@Composable
fun InputScreen(onNavigate: (Int) -> Unit) {
    var yearText by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val year = yearText.toIntOrNull() ?: 0
                onNavigate(year)
            }) {
                Text("Ir")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            TextField(
                value = yearText,
                onValueChange = { yearText = it },
                label = { Text("Ingresa tu año de nacimiento") }
            )
        }
    }
}

@Composable
fun ResultadoScreen(edad: Int, onBack: () -> Boolean) {

    val anioactual = 2026
    val age = anioactual - edad

    val (message, imageRes) = when {
        age <= 14 -> "Menor de edad" to R.drawable.nino
        age == 15 -> "Mayor de edad en Indonesia pero no en México" to R.drawable.joven
        age == 16 -> "Mayor de edad en Cuba pero no en México" to R.drawable.joven
        age == 17 -> "Mayor de edad en Corea del Norte pero no en México" to R.drawable.joven
        age == 18 -> "Mayor de edad en México y gran parte de Latinoamérica" to R.drawable.joven
        age == 19 -> "Mayor de edad en Corea del Sur" to R.drawable.joven
        age == 20 -> "Mayor de edad en Japón" to R.drawable.joven
        age == 21 -> "Mayor de edad en USA y posiblemente en todo el mundo" to R.drawable.joven
        age == 60 -> "Eres de la tercera edad" to R.drawable.anciano
        age >= 65 -> "Ya te puedes jubilar" to R.drawable.anciano
        else -> "Edad: $age años" to R.drawable.joven
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Edad: $age años", style = MaterialTheme.typography.headlineSmall)
                Text(text = message, style = MaterialTheme.typography.bodyMedium)

                Spacer(Modifier.height(16.dp))
                Button(onClick = { onBack() }) {
                    Text("regresar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable

fun pantallaview(){
    Navegacion()
}

