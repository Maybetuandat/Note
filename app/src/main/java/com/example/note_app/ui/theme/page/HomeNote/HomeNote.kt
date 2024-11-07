package com.example.note_app.ui.theme.page.HomeNote


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete

import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.note_app.R
import com.example.note_app.domain.model.Note
import com.example.note_app.navigation.NavItem
import com.example.note_app.ui.theme.backGroundColor
import com.example.note_app.ui.theme.endColorCard
import com.example.note_app.ui.theme.itemCardColor
import kotlinx.coroutines.flow.forEach


@Composable
fun HomeNote(navController: NavHostController) {

    val viewModel: HomeNoteViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 20.dp)
        ) {
            TopAppBar(navController)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
        ) {
            MainHomeNote(viewModel, navController)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)

        ) {
            BottomApp(navController)
        }


    }


}

@Composable
fun BottomApp(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 30.dp, bottom = 20.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(containerColor = FloatingActionButtonDefaults.containerColor,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(20.dp),
            modifier = Modifier.padding(16.dp),
            onClick = { navController.navigate(NavItem.NewNote.route) }) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White
            )
        }
    }

}

@Composable
fun MainHomeNote(viewModel: HomeNoteViewModel , navController: NavHostController) {

    val noteList by viewModel.noteList.collectAsState()
    // mai chỉnh thành if luôn đầu cho nhanh, cái đầu dùng column , cái sau dùng lazycolumn
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (noteList.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.notes), // Thay thế bằng hình minh họa phù hợp
                contentDescription = null, modifier = Modifier.size(300.dp) // Kích thước hình ảnh
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create your first note!",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(noteList) { note ->
                    NoteCard(note, navController, viewModel)

                }
            }
        }


    }
}

@Composable
fun NoteCard(note: Note, navController: NavHostController, viewModel: HomeNoteViewModel) {
    var isPressed by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
            .pointerInput(Unit){
                detectTapGestures(
                    onLongPress = {
                        isPressed = true
                    },
                    onTap = {
                        navController.navigate("detail/${note.id}")
                    }
                )
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        )
    {
        if(isPressed)
        {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically)
            {
                    IconButton(
                        onClick = {
                            isPressed = false
                            viewModel.deleteNote(note)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Green,
                            modifier = Modifier.size(height = 50.dp, width = 50.dp)
                        )
                    }
                IconButton(
                    onClick = {
                        isPressed = false

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete",
                        tint = Color.Red,
                        modifier = Modifier.size(height = 50.dp, width = 50.dp)
                    )
                }

            }

        }
        else
        {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(endColorCard),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )

            }
        }

    }
}

@Composable
fun TopAppBar(navController: NavHostController) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxHeight(),

            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Notes", fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.weight(1f))
        Card(
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.CenterVertically),
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
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
        Spacer(Modifier.width(20.dp))
        Card(
            modifier = Modifier
                .size(50.dp)
                .align(alignment = Alignment.CenterVertically),
            colors = CardDefaults.cardColors(itemCardColor),
            elevation = CardDefaults.elevatedCardElevation(20.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {navController.navigate(NavItem.About.route)}
        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Rounded.Info,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Spacer(Modifier.width(20.dp))
    }
}

