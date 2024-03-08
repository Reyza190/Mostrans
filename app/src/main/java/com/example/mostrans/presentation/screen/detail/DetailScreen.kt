package com.example.mostrans.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mostrans.R
import com.example.mostrans.firebase.domain.model.DetailedCharacterFirebase



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.padding(),
        bottomBar = {
            var textInput by remember { mutableStateOf("") }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Gray)
            ) {
                Row {
                    TextField(
                        value = textInput,
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        onValueChange = { input ->
                            textInput = input
                        },
                        modifier = Modifier.fillMaxHeight()
                    )
                    Button(
                        onClick = {
                                  viewModel.addCharacter(
                                      character = DetailedCharacterFirebase(
                                      id = state.character?.id,
                                      name = state.character?.name,
                                      image = state.character?.image,
                                      status = state.character?.status,
                                      species = state.character?.species,
                                      gender = state.character?.gender,
                                      location = textInput
                                  ))
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(
                                id = R.color.isPressed
                            )
                        ),
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(text = "Save Location", fontSize = 13.sp)
                    }
                }
            }
        }
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
                state.character.apply {
                    DetailScreenContent(
                        name = this?.name!!,
                        image = this.image!!,
                        species = this.species!!,
                        status = this.status!!,
                        gender = this.gender!!
                    )
                }

            }
        }
    }
}

@Composable
fun DetailScreenContent(
    name: String,
    image: String,
    species: String,
    status: String,
    gender: String

) {
    Column {
        AsyncImage(
            model = image,
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Column {
                Text(
                    text = "Name: $name",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(text = "Species: $species", fontSize = 20.sp)
            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Status: $status", fontSize = 20.sp)
                Text(text = "Gender: $gender", fontSize = 20.sp)
            }

        }

    }
}

@Preview
@Composable
fun DetrailScreenContentPreview() {
    DetailScreenContent(
        name = "Halo",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        species = "Horuz",
        status = "Alive",
        gender = "Gay"
    )
}