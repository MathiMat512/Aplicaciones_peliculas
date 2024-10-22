package com.davidchura.sistema1228.content

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1228.HomeActivity
import com.davidchura.sistema1228.R
import com.davidchura.sistema1228.TermsActivity
import com.davidchura.sistema1228.content.ui.theme.Sistema1228Theme

class HombreGris : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.davidchura.sistema1228.ui.theme.Sistema1228Theme {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                ){
                    Box {
                        Box(
                            modifier = Modifier.height(300.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hombregris2),
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            IconButton(
                                onClick = { startActivity(Intent(this@HombreGris, HomeActivity::class.java)) },
                                modifier = Modifier.align(Alignment.TopStart)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.regresar),
                                    tint = Color.White,
                                    contentDescription = "Back",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .height(300.dp)
                                .matchParentSize() // Ajusta el tamaño a la imagen
                        ) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Black
                                            ),
                                            startY = 150f
                                        )
                                    )
                            )
                            Text(
                                text = "El hombre Gris",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.BottomStart) // Alineación inferior izquierda para el texto
                                    .padding(16.dp)
                            )
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@HombreGris, TermsActivity::class.java))
                        }) {
                            Text("Ver Ahora")
                        }
                        IconButton(onClick = { /* Acción para descargar */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.descargar),
                                contentDescription = "Download",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(onClick = { /* Acción para guardar */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.marcador),
                                contentDescription = "Bookmark",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(onClick = { /* Acción para compartir */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.compartir),
                                contentDescription = "Share",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { /* Acción */ },
                            shape = RoundedCornerShape(50), // Borde redondeado
                            modifier = Modifier.height(35.dp)
                        ) {
                            Text(text = "2022 | Accion | Suspenso | USA")
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = dimensionResource(id = R.dimen.size_3)),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "El Hombre Gris",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text("En 2003, Donald Fitzroy, alto funcionario de la CIA, visita a un prisionero llamado Courtland Gentry. Ocho años antes, Courtland era un menor condenado por matar a su padre abusivo para proteger a su hermano. Fitzroy le ofrece al hombre su libertad a cambio de trabajar como asesino en el programa Sierra de la CIA.\n")

                        Text(
                            text = "PG-13 | 2h 09m\n",
                            fontSize = 20.sp, // Tamaño del texto
                            fontWeight = FontWeight.Bold,

                            )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = {
                                startActivity(Intent(this@HombreGris, TermsActivity::class.java))
                            }) {
                                Text("Reparto")
                            }
                            Button(onClick = {
                                startActivity(Intent(this@HombreGris, HomeActivity::class.java))
                            }) {
                                Text(text = stringResource(id = R.string.home))
                            }
                        }//Row
                    }//Column
                }
            }
        }
    }
}
