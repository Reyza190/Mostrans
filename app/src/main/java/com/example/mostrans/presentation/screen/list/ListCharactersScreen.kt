package com.example.mostrans.presentation.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mostrans.R
import com.example.mostrans.graphql.domain.model.SimpleCharacter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    onSelectedCharacter: (id: String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = stringResource(id = R.string.top_bar), fontSize = 32.sp)
            })
        },
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                    items(state.characters) { character ->
                        CharacterItem(character, modifier = Modifier.clickable { onSelectedCharacter(character.id!!) })
                    }
                })

            }
        }
    }

}


@Composable
fun CharacterItem(
    character: SimpleCharacter,
    modifier: Modifier = Modifier,
) {

    Card(
        shape = RoundedCornerShape(21.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Column {
            AsyncImage(
                model = character.image,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(109.dp)
            )
            Text(
                text = "Name: ${character.name}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = modifier.padding(start = 8.dp, top = 5.dp)
            )
            Text(
                text = "Species ${character.species}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                modifier = modifier.padding(start = 8.dp)
            )
        }

    }
}


