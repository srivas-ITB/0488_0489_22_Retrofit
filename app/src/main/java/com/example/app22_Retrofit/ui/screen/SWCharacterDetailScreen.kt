import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app22_Retrofit.data.SWCharacter
import com.example.app22_Retrofit.ui.screen.CharacterDetailViewModel


@Composable
fun SWCharacterDetailScreen(id: Int, url:String, navigateBack: () -> Unit) {
    val vm: CharacterDetailViewModel = viewModel()
    val character by vm.character.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        //vm.getCharacterById(id)
        vm.getCharacterByUrl(url)
    }

    //ShowDetail_Basic (character, navigateBack )
    ShowDetail_Advanced (character, navigateBack )

}

@Composable
fun ShowDetail_Basic(character: SWCharacter?, navigateBack: () -> Unit) {
    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        if (character == null) {
            Text("Loading... waiting for data")
        } else {
            Text(character.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
        Button(navigateBack) { Text("Return") }
    }
}


@Composable
fun ShowDetail_Advanced( character: SWCharacter?, navigateBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1A1A2E), Color(0xFF16213E))
                )
            )
    ) {
        if (character == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color.Yellow)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Searching the galaxy...", color = Color.White)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // --- Avatar/Icono de Personaje ---
                Surface(
                    shape = CircleShape,
                    color = Color.Yellow.copy(alpha = 0.1f),
                    border = BorderStroke(2.dp, Color.Yellow),
                    modifier = Modifier.size(120.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = character.name.take(1),
                            style = TextStyle(
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.Yellow
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- Nombre y Título ---
                Text(
                    text = character.name.uppercase(),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    ),
                    color = Color.White
                )

                Text(
                    text = "Birth Year: ${character.birthYear}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(32.dp))

                // --- Cuadrícula de Información ---
                InfoGrid(character)

                Spacer(modifier = Modifier.height(40.dp))

                // --- Botón de Retorno Estilizado ---
                Button(
                    onClick = navigateBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Text("RETURN TO FLEET", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun InfoGrid(character: SWCharacter) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            InfoCard(label = "HEIGHT", value = character.height, icon = "📏", modifier = Modifier.weight(1f))
            InfoCard(label = "MASS", value = character.mass, icon = "⚖️", modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            InfoCard(label = "GENDER", value = character.gender.replaceFirstChar { it.uppercase() }, icon = "👤", modifier = Modifier.weight(1f))
            InfoCard(label = "EYES", value = character.eye_color.replaceFirstChar { it.uppercase() }, icon = "👁️", modifier = Modifier.weight(1f))
        }
        InfoCard(label = "HAIR COLOR", value = character.hair_color.replaceFirstChar { it.uppercase() }, icon = "💇", modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun InfoCard(label: String, value: String, icon: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.05f),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "$icon $label", style = MaterialTheme.typography.labelSmall, color = Color.Yellow)
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}
