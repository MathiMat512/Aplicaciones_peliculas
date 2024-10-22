package com.davidchura.sistema1228

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
// Volley para solicitudes HTTP
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.sistema1228.content.Peliculas
import com.davidchura.sistema1228.ui.theme.Color2
// JSON para manejo de respuesta JSON
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Buscar : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Sistema1228Theme {
                // Estado que manejará los datos cargados
                val actorListState = remember { mutableStateOf(emptyList<HashMap<String, String>>()) }
                val actorList = remember { actorListState }

                // Llamada a la API para obtener los datos
                LaunchedEffect(Unit) {
                    val queue = Volley.newRequestQueue(this@Buscar)
                    val url = "http://10.0.2.2/serviciosweb/genero.php"

                    val stringRequest = StringRequest(
                        Request.Method.GET, url,
                        { response ->
                            Log.d("API Response", response)
                            val jsonArray = JSONArray(response)
                            val arrayList = ArrayList<HashMap<String, String>>()

                            for (i in 0 until jsonArray.length()) {
                                val idgenero = jsonArray.getJSONObject(i).getString("idgenero")
                                val genero = jsonArray.getJSONObject(i).getString("genero")
                                val cantidad = jsonArray.getJSONObject(i).getString("cantidad")
                                val hashMap = HashMap<String, String>()
                                hashMap["idgenero"] = idgenero
                                hashMap["genero"] = genero
                                hashMap["cantidad"] = cantidad
                                arrayList.add(hashMap)
                            }
                            actorListState.value = arrayList // Actualiza el estado con los datos obtenidos
                        },
                        { error ->
                            Log.e("API Error", error.toString())
                        }
                    )
                    queue.add(stringRequest)
                }

                // Contenido de la UI
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Buscar por género",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { startActivity(Intent(this@Buscar, HomeActivity::class.java)) },
                                    icon = {
                                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                    },
                                    label = { Text("Home") }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { startActivity(Intent(this@Buscar, Favoritos::class.java)) },
                                    icon = {
                                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                                    },
                                    label = { Text("Favoritos") }
                                )
                                NavigationBarItem(
                                    selected = true,
                                    onClick = { /* Estamos en Buscar */ },
                                    icon = {
                                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                                    },
                                    label = { Text("Buscar") }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { startActivity(Intent(this@Buscar, MiPerfil::class.java)) },
                                    icon = {
                                        Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                                    },
                                    label = { Text("Mi Perfil") }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    DibujarTabla(actorList.value, innerPadding)
                    // Aquí dibujamos la tabla con los datos cargados de la API
                    Box(modifier = Modifier.padding(innerPadding)) {
                    }
                }
            }
        }
    }

    @Composable
    private fun DibujarTabla(
        arrayList: List<HashMap<String, String>>,
        innerPadding: PaddingValues // Añades innerPadding como parámetro
    ) {
        Column(Modifier.padding(innerPadding)) {
            LazyColumn {
                items(arrayList) { category ->
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize()
                                .clickable {
                                    selectPeliculas(category)
                                }
                        ) {
                            Row {
                                Column {
                                    Text(
                                        text = category["idgenero"].toString(),
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(
                                        text = category["genero"].toString(),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "Cantidad de peliculas: " + category["cantidad"].toString(),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun selectPeliculas(category: HashMap<String, String>) {
        val bundle = Bundle().apply {
            //Asi se encapsulan los datos para enviarlos por intent
            putString("idgenero", category["idgenero"].toString())
            putString("genero", category["genero"].toString())
        }
        val intent = Intent(this, Peliculas::class.java)
        intent.putExtras(bundle) //Así se envían los datos por intent
        startActivity(intent)
    }
}