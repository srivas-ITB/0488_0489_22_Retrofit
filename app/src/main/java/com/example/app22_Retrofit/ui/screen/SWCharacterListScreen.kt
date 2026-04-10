package com.example.app22_Retrofit.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app22_Retrofit.ui.screen.CharacterListViewModel
import com.example.app22_Retrofit.data.SWCharacter


@Composable
fun SWCharacterListScreen(navigateToDetail: (Int,String) -> Unit) {
    val vm: CharacterListViewModel = viewModel()
    val characters by vm.characters.collectAsStateWithLifecycle()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characters) { character ->
            //Extreu l'Id de la ruta  (penultima part si separem per / )
            var parts= character.url.split("/")
            val id = Integer.parseInt(parts.get(parts.lastIndex-1))
            CharacterItem_Advanced(character = character, { navigateToDetail(id, character.url) })
        }
    }
}

@Composable
fun CharacterItem_Basic(character: SWCharacter, navigateToDetail: () -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier.fillMaxWidth()
            .clickable { navigateToDetail() }) {
        Column {
            Text(character.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = "HomeWorld: ${character.homeworld}")
        }
    }
}




@Composable
fun CharacterItem_Advanced(
    character: SWCharacter,
    navigateToDetail: () -> Unit
) {
    Card(
        // Cambiamos a ElevatedCard para darle profundidad automática
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { navigateToDetail() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Un pequeño contenedor para un "Avatar" o inicial del personaje
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = character.name.take(1),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Fila para Gender y Height
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // Esto empuja los elementos a los extremos
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Alineado a la izquierda
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = character.gender.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Alineado a la derecha
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Height: ",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${character.height}cm",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }


            // Un indicador visual de que es clickeable
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}