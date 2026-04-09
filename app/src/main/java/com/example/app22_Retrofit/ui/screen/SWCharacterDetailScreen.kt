import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app22_Retrofit.data.Repository
import com.example.app22_Retrofit.ui.screen.CharacterDetailViewModel
import com.example.app22_Retrofit.ui.screen.CharacterItem_Advanced
import com.example.app22_Retrofit.ui.screen.CharacterListViewModel


@Composable
fun SWCharacterDetailScreen(id: Int, navigateBack: () -> Unit) {
    val vm: CharacterDetailViewModel = viewModel()
    val character by vm.character.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.getCharacterById(id)
    }

    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        if (character == null) {
            Text("Loading... waiting for id $id")
        } else {
            Text(character!!.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
        Button(navigateBack) { Text("Return") }
    }
}