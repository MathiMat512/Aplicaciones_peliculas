package com.davidchura.sistema1228

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1228.content.BladeRunner
import com.davidchura.sistema1228.content.HombreGris
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sistema1228Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = Color.Black,
                            ),
                            title = {
                                Text("Inicio",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,)
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@HomeActivity,HomeActivity::class.java))},
                                    icon = {
                                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                    },
                                    label = { Text("Inicio",
                                        fontSize = 15.sp,) }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@HomeActivity,Favoritos::class.java))},
                                    icon = {
                                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                                    },
                                    label = { Text("Favoritos",
                                        fontSize = 15.sp,) }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@HomeActivity,Buscar::class.java))},
                                    icon = {
                                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                                    },
                                    label = { Text("Buscar",
                                        fontSize = 15.sp,) }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@HomeActivity,MiPerfil::class.java))},
                                    icon = {
                                        Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                                    },
                                    label = { Text("Mi perfil",
                                        fontSize = 15.sp,) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding) // Mover padding a este nivel
                    ) {
                        // Primer Column
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Para ti",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(4) { index ->
                                    val context = LocalContext.current // Get context
                                    Image(
                                        painter = painterResource(id = when (index) {
                                            0 -> R.drawable.bladerunner1
                                            1 -> R.drawable.gris1
                                            2 -> R.drawable.fall1
                                            else -> R.drawable.drive1
                                        }),
                                        modifier = Modifier
                                            .clickable {
                                                if (index == 0) {
                                                    val intent = Intent(context, BladeRunner::class.java)
                                                    context.startActivity(intent)
                                                }
                                                if (index == 1) {
                                                    val intent = Intent(context, HombreGris::class.java)
                                                    context.startActivity(intent)
                                                }
                                            }
                                            .height(300.dp)
                                            .padding(end = 30.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color.White),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = stringResource(id = R.string.begin_image_description)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))  // Espacio entre los dos Column

                        // Segundo Column (idéntico al primero)
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Añadido Recientemente",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(4) { index ->
                                    Image(
                                        painter = painterResource(id = when (index) {
                                            0 -> R.drawable.image5
                                            1 -> R.drawable.image6
                                            2 -> R.drawable.image7
                                            else -> R.drawable.image8
                                        }),
                                        modifier = Modifier
                                            .height(130.dp)
                                            .padding(end = 30.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color.White),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = stringResource(id = R.string.begin_image_description)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Podria gustarte",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(4) { index ->
                                    Image(
                                        painter = painterResource(id = when (index) {
                                            0 -> R.drawable.lalaland1
                                            1 -> R.drawable.barbie1
                                            2 -> R.drawable.pasion1
                                            else -> R.drawable.lugar1
                                        }),
                                        modifier = Modifier
                                            .height(130.dp)
                                            .padding(end = 30.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color.White),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = stringResource(id = R.string.begin_image_description)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Populares",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                            )
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(4) { index ->
                                    Image(
                                        painter = painterResource(id = when (index) {
                                            0 -> R.drawable.solodios
                                            1 -> R.drawable.dostipos
                                            2 -> R.drawable.duelo1
                                            else -> R.drawable.loco1
                                        }),
                                        modifier = Modifier
                                            .height(130.dp)
                                            .padding(end = 30.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color.White),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = stringResource(id = R.string.begin_image_description)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
