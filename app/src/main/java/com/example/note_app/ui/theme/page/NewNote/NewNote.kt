package com.example.note_app.ui.theme.page.NewNote

import android.text.Selection
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.note_app.R
import com.example.note_app.navigation.NavItem
import com.example.note_app.ui.theme.backGroundColor
import com.example.note_app.ui.theme.itemCardColor
import com.example.note_app.ui.theme.page.HomeNote.BottomApp
import com.example.note_app.ui.theme.page.HomeNote.MainHomeNote
import com.example.note_app.ui.theme.page.HomeNote.TopAppBar

@Composable
fun NewNote(navHostController: NavHostController) {
    val viewModel: NewNoteViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)


        ) {
            NewNoteAppBar(navHostController, viewModel)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(7f)
        ) {
            MainNewNote(viewModel)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNewNote(viewModel: NewNoteViewModel) {
    val rainbowColors: List<Color> = listOf()
    val brush = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = viewModel.titleText,
            onValueChange = {
                viewModel.titleText = it
            },

            placeholder = {
                Text(
                    text = "Title",
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Gray,
                cursorColor = Color.Gray


            ),
            textStyle = TextStyle(
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = viewModel.bodyText,
            onValueChange = {
                viewModel.bodyText = it
            },
            placeholder = {
                Text(
                    text = "Type Something ...",
                    color = Color.Gray,
                    fontSize = 30.sp
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Gray,
                cursorColor = Color.Gray
            ),
            textStyle = TextStyle(
                fontSize = 30.sp,

                color = Color.Gray
            ),

            )
    }
}

@Composable
fun NewNoteAppBar(navHostController: NavHostController, viewModel: NewNoteViewModel) {
    Row(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.Bottom),
            colors = CardDefaults.cardColors(itemCardColor),
            elevation = CardDefaults.elevatedCardElevation(20.dp),
            shape = RoundedCornerShape(10.dp)


        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable { navHostController.navigate(NavItem.HomeNote.route) },
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
        Spacer(Modifier.weight(1f))
        Card(
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.Bottom),
            colors = CardDefaults.cardColors(itemCardColor),
            elevation = CardDefaults.elevatedCardElevation(20.dp),
            shape = RoundedCornerShape(10.dp)


        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                    imageVector = Icons.Rounded.Lock,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
        Spacer(Modifier.width(20.dp))
        Card(
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.Bottom)
                .clickable {
                    viewModel.saveNewNote()
                    navHostController.navigate(NavItem.HomeNote.route)
                },
            colors = CardDefaults.cardColors(itemCardColor),
            elevation = CardDefaults.elevatedCardElevation(20.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.save),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Spacer(Modifier.width(20.dp))
    }
}
