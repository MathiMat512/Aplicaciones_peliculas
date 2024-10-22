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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidchura.sistema1228.ui.theme.Sistema1228Theme

class Favoritos : ComponentActivity() {
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
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Tu lista de favoritos",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold)
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@Favoritos,HomeActivity::class.java))},
                                    icon = {
                                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                    },
                                    label = { Text("Inicio",
                                        fontSize = 15.sp,) }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@Favoritos,Favoritos::class.java))},
                                    icon = {
                                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                                    },
                                    label = { Text("Favoritos",
                                        fontSize = 15.sp,) }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@Favoritos,Buscar::class.java))},
                                    icon = {
                                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                                    },
                                    label = { Text("Buscar",
                                        fontSize = 15.sp,) }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = {startActivity(Intent(this@Favoritos,MiPerfil::class.java))},
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
                    // El contenido principal queda vacío ya que los cards han sido eliminados
                    Box(modifier = Modifier.padding(innerPadding)) {
                        // Puedes agregar cualquier otro contenido aquí si lo deseas
                    }
                }
            }
        }
    }
}
