package com.example.myapplication.tarea4

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.R

data class Categoria(
    val nombre: String,
    val imagen: Int
)

data class Producto(
    val nombre: String,
    val precio: Double,
    val imagen: Int,
    val envioGratis: Boolean,
    val descuento: String? = null
)

@Composable
fun PantallaViews() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {

        composable("menu") {
            Column(Modifier.fillMaxSize()) {
                Text(
                    "Seleccione una Categoría",
                    Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                CarruselCat(categorias = listaCategorias) { categoria ->
                    navController.navigate("detalle/${categoria.nombre}")
                }
            }
        }

        composable(
            route = "detalle/{catNombre}",
            arguments = listOf(navArgument("catNombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreSeleccionado = backStackEntry.arguments?.getString("catNombre") ?: ""

            val mostrarProd = when (nombreSeleccionado) {
                "Accesorios para coche" -> listaCoches
                "Productos de deporte" -> listaDeportes
                "Productos para el hogar" -> listaHogar
                "Importados de Usa" -> listaUsa
                else -> emptyList()
            }

            Column(Modifier.fillMaxSize()) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Regresar")
                }

                Text(
                    text = "Resultados de $nombreSeleccionado",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                if (mostrarProd.isNotEmpty()) {
                    GridProductos(productos = mostrarProd)
                } else {
                    Text("No hay productos disponibles", Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun CarruselCat(categorias: List<Categoria>, onCategoriaClick: (Categoria) -> Unit) {
    LazyRow(
        Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categorias) { categoria ->
            Column(
                Modifier
                    .width(120.dp)
                    .clickable { onCategoriaClick(categoria) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(categoria.imagen),
                    contentDescription = categoria.nombre,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(
                    text = categoria.nombre,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
fun GridProductos(productos: List<Producto>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(productos) { producto ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(producto.imagen),
                    contentDescription = null,
                    Modifier.size(90.dp)
                )
                Text(producto.nombre, maxLines = 1, textAlign = TextAlign.Center, fontSize = 12.sp)
                Text("$${producto.precio}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MostrarPantallaPreview() {
    PantallaViews()
}