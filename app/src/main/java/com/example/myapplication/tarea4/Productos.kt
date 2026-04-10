package com.example.myapplication.tarea4

import com.example.myapplication.R


val listaCategorias = listOf(
    Categoria("Accesorios para coche", R.drawable.cat_coche),
    Categoria("Productos de deporte", R.drawable.cat_deportes),
    Categoria("Productos para el hogar", R.drawable.cat_hogar),
    Categoria("Importados de Usa", R.drawable.cat_usa)
)

val listaCoches = listOf(
    Producto("Casco moto", 1200.0, R.drawable.casco_moto, true),
    Producto("Cubrevolante", 1200.0, R.drawable.cubrevolante, false),
    Producto("Cubreasientos", 1200.0, R.drawable.cubreasientos, false),
    Producto("Cargador auto", 1200.0, R.drawable.cargador_auto, false),
    Producto("Aromatizante", 1200.0, R.drawable.aeromatizante, false)
)

val listaDeportes = listOf(
    Producto("Balón Futbol", 350.0, R.drawable.balon_fut, true),
    Producto("Guantes", 350.0, R.drawable.guantes_fut, true),
    Producto("Bate Beisbol", 350.0, R.drawable.bate_beisbol, true),
    Producto("Botella", 350.0, R.drawable.botella, true),
    Producto("Balón Basket", 350.0, R.drawable.balon_basket, true)
)

val listaHogar = listOf(
    Producto("Sofa", 5000.0, R.drawable.sofa, false),
    Producto("Lámpara", 500.0, R.drawable.lampara, false),
    Producto("Comedor", 8000.0, R.drawable.mesa_comedor, false),
    Producto("Silla", 1200.0, R.drawable.silla, false),
    Producto("Cuadro", 1500.0, R.drawable.cuadro, false)
)

val listaUsa = listOf(
    Producto("Laptop", 15000.0, R.drawable.laptop, false),
    Producto("Celular", 8000.0, R.drawable.celular, false),
    Producto("TV", 12000.0, R.drawable.television, false),
    Producto("Cargador", 600.0, R.drawable.cargador_usa, false),
    Producto("Tablet", 7000.0, R.drawable.tablet, false)
)
