# App 22 Retrofit

- Segueix l'exemple de la presentació i amplia el contingut
- Inclou la navegació i els viewModels
- Inclou la pantalla de detalls
- Afegida una versió per recuperar el personatge mitjançant l'id o bé la url

- `SWCharacterListScreen` :: La llista de personatges (`SWCharacter`) s'ha duplicat amb dos estils:
  - CharacterItem_Basic (tal i com es fa a la presentació)
  - CharacterItem_Advance (amb una UI més treballada i atractiva)
  - Fixa't que només canvia la UI. No hi ha cap canvi que afecti a la lògica del programa.
- `SWCharacterDetailScreen` :: La fitxa del personatge, també s'ha duplicat amb dos estils:
  - ShowDetail_Basic. S'ha afegit una pantalla bàsica de detall del personatge (que no hi és a la presentació)
  - ShowDetail_Advance (amb una UI més treballada i atractiva)
  - De la mateixa forma que a la llista, fixa't que només canvia la UI. No hi ha cap canvi que afecti a la lògica del programa.
> [!WARNING] 
> EXPERIMENT: La pantalla `SWCharacterDetailScreen` rep dos paràmetres:
>    - id : Int  --> identificador del personatge
>    - url: String --> URL per obtenir la informació del personatge
>      
> En la creació del viewModel `CharacterDetailViewModel` es decideix quin dels dos es fa servir.
> En realitat no cal fer servir tots dos sinó només un dels dos, però s'han implementat les dues formes per motius experimentals.
>
>```kotlin
@Composable
fun SWCharacterDetailScreen(id: Int, url:String, navigateBack: () -> Unit) {
    val vm: CharacterDetailViewModel = viewModel()
    val character by vm.character.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        //vm.getCharacterById(id)      <--  COMENTAR / DESCOMENTAR AQUESTES LÍNIES PER ACCEDIR PER ID o URL
        vm.getCharacterByUrl(url)
    }

    //ShowDetail_Basic (character, navigateBack )   <--  COMENTAR / DESCOMENTAR AQUESTES LÍNIES PER CANVIAR LA UI
    ShowDetail_Advanced (character, navigateBack )
}
```
> i per aquí acaba el missatge de warning
