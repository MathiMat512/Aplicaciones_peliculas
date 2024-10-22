package com.davidchura.sistema1228.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.sistema1228.Buscar
import com.davidchura.sistema1228.Favoritos
import com.davidchura.sistema1228.HomeActivity
import com.davidchura.sistema1228.MiPerfil
import com.davidchura.sistema1228.content.ui.theme.Sistema1228Theme
import org.json.JSONArray
import org.json.JSONException

class Peliculas : ComponentActivity() {
    var nombre = ""
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null) {
            val idgenero = bundle.getString("idgenero")
            nombre = bundle.getString("nombre").orEmpty() // evita usar !! y previene un valor nulo
            readService(idgenero)
        } else {
            Log.e("Error", "Bundle is null")
        }
        enableEdgeToEdge()
    }

    private fun readService(idgenero: String?) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://10.0.2.2/serviciosweb/peliculas.php?idgenero=$idgenero"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("API Response", response)
                fillArray(response)
            },
            {  })
        queue.add(stringRequest)
    }
    private fun fillArray(response: String) {
        try {
            val jsonArray = JSONArray(response)
            val arrayList = ArrayList<HashMap<String,String>>()
            for (i in 0 until jsonArray.length()) {
                val id = jsonArray.getJSONObject(i).getString("id")
                val pelicula = jsonArray.getJSONObject(i).getString("pelicula")
                val genero = jsonArray.getJSONObject(i).getString("genero")
                val año = jsonArray.getJSONObject(i).getString("año")
                val duracion = jsonArray.getJSONObject(i).getString("duracion")
                val hashMap = HashMap<String,String>()
                hashMap["id"] = id
                hashMap["pelicula"] = pelicula
                hashMap["genero"] = genero
                hashMap["año"] = año
                hashMap["duracion"] = duracion
                arrayList.add(hashMap)
            }
            dibujar(arrayList)
        } catch (e: JSONException) {
            Log.e("JSON Error", "Error parsing JSON: ${e.message}")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun dibujar(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            com.davidchura.sistema1228.ui.theme.Sistema1228Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(1),
                            contentPadding = PaddingValues(8.dp), // Padding alrededor de la cuadrícula
                            horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaciado horizontal entre columnas
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(items = arrayList) { peliculas ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp),
                                    elevation = CardDefaults.cardElevation(4.dp), // Sombra de 4dp
                                    colors = CardDefaults.cardColors(containerColor = Color.White) // Color de fondo blanco
                                ) {
                                    val id = peliculas["id"]?.toFloatOrNull() ?: 0f
                                    Text(text = peliculas["pelicula"].toString())
                                    Text(text = "Genero: " + peliculas["genero"].toString())
                                    Text(text = "Año: " + peliculas["año"].toString())
                                    Text(text = "Duracion: " + peliculas["duracion"].toString())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
