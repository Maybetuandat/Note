package com.example.note_app.ui.theme.page.DetailNote

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.util.Log
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note_app.domain.model.Note
import com.example.note_app.ui.theme.backGroundColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.note_app.R
import com.example.note_app.navigation.NavItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun DetailNotes(id: String?, navController: NavHostController) {
    val viewModel: DetailNotesViewModel = hiltViewModel()

    id?.toIntOrNull()?.let { noteId ->
        LaunchedEffect(noteId) {
            viewModel.getNoteById(noteId)
        }
    }

    val note by viewModel.note.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
//    Column(modifier = Modifier.fillMaxSize()) {
//        Text(text = note?.title ?: "nguuu")
//        Spacer(modifier = Modifier.fillMaxWidth().height(100.dp))
//        Text(text = note?.content ?: "nguu")
//    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundColor)
            .padding(16.dp)
    ) {
        Column {
            // Row for back and edit buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate(NavItem.HomeNote.route) }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack, // Replace with your back icon
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    if(isEditing)
                    {
                        viewModel.saveNote()
                        Log.d("detail", "Save ")
                    }
                    isEditing = !isEditing



                }) {
                    if (isEditing == true) {
                        Icon(
                            painter = painterResource(R.drawable.save),
                            contentDescription = "Edit",
                            tint = Color.White,

                            )
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            if (isEditing) {
                TextField(
                    value = note?.title ?: "",
                    onValueChange = {   viewModel.updateNoteTitle(it)},
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = backGroundColor,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Transparent,
                        focusedLeadingIconColor = Color.Transparent
                    )
                )
            } else {
                Text(
                    text = note?.title ?:"",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Content
            if (isEditing) {
                TextField(
                    value = note?.content ?: "",
                    onValueChange = { viewModel.updateNoteContent(it)},
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = backGroundColor,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Transparent,
                        focusedLeadingIconColor = Color.Transparent,

                        )
                )
            } else {
                Text(
                    text = note?.content ?: "",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    lineHeight = 22.sp
                )
            }
        }
    }

}