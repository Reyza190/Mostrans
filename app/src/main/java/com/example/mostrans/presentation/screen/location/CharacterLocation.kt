package com.example.mostrans.presentation.screen.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mostrans.R
import com.example.mostrans.firebase.data.repository.Response
import com.example.mostrans.presentation.screen.list.CharacterItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationCharacterScreen(
    viewModel: CharacterLocationViewModel = hiltViewModel(),
    onSelectedCharacter: (id: String) -> Unit
)
{
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = stringResource(id = R.string.location), fontSize = 32.sp)
            })
        },
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when(val character = viewModel.characterResponse){
                is Response.Failure -> TODO()
                Response.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
                is Response.Success -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                        items(character.data) { character ->
                            CharacterItem(character, modifier = Modifier.clickable { onSelectedCharacter(character.id!!) })
                        }
                    })
                }
            }
        }
    }
}