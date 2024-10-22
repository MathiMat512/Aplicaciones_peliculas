package com.davidchura.sistema1228

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.davidchura.sistema1228.ui.theme.Color2
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme
import org.json.JSONArray

class TermsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val queue = Volley.newRequestQueue(this)
        val url = "http://10.0.2.2/serviciosweb/reparto.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("API Response", response)
                fillArray(response)
            },
            { error ->
                Log.e("API Error", error.toString()) // Agregar este log
            }
        )
        queue.add(stringRequest)
        enableEdgeToEdge()

    }

    private fun fillArray(response: String) {
        val jsonArray = JSONArray(response)
        val arrayList = ArrayList<HashMap<String,String>>()
        for (i in 0 until jsonArray.length()){
            val nombres = jsonArray.getJSONObject(i).getString("nombres")
            val personaje = jsonArray.getJSONObject(i).getString("personaje")
            val edad = jsonArray.getJSONObject(i).getString("edad")
            val pais = jsonArray.getJSONObject(i).getString("pais")
            val hashMap = HashMap<String,String>()
            hashMap["nombres"] = nombres
            hashMap["personaje"] = personaje
            hashMap["edad"] = edad
            hashMap["pais"] = pais
            arrayList.add(hashMap)
        }
        dibujar(arrayList)
    }

    private fun dibujar(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            Sistema1228Theme {
                Column {
                    LazyColumn (
                        content = {
                            items(items = arrayList, itemContent = { actores ->
                                Surface(
                                    border = BorderStroke(1.dp, Color2),
                                    modifier = Modifier
                                        .padding(16.dp) // Espaciado exterior
                                ) {
                                    Column(modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()) { // Espaciado interior
                                        Text(
                                            text = "Nombres: " + actores["nombres"].toString(),
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                        Text(
                                            text = "Personaje: " + actores["personaje"].toString(),
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(text = "Edad: " + actores["edad"].toString() + " / " + "Pais: " + actores["pais"].toString())
                                    }
                                }
                            })
                        }
                    )
                }
            }
        }
    }
}
