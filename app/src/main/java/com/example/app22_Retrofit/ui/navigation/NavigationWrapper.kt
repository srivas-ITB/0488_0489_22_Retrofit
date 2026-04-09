package com.example.app22_Retrofit.ui.navigation
import SWCharacterDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.app22_Retrofit.ui.screen.SWCharacterListScreen

@Composable
fun NavigationWrapper_starwars(modifier: Modifier) {
    val backStack = rememberNavBackStack(Route.CharacterListScreen)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.CharacterListScreen> {
                SWCharacterListScreen { id -> backStack.add(Route.CharacterDetailScreen(id)) }
            }
            entry<Route.CharacterDetailScreen> { key->
                SWCharacterDetailScreen(key.id) {
                    backStack.removeLastOrNull()
                }
            }
        }

    )

}
