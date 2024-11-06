package com.example.note_app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.note_app.ui.theme.page.About.About
import com.example.note_app.ui.theme.page.DetailNote.DetailNotes
import com.example.note_app.ui.theme.page.HomeNote.HomeNote
import com.example.note_app.ui.theme.page.NewNote.NewNote

@Composable
fun NavigationHost(innerPaddingValues: PaddingValues, navController: NavHostController)
{
        NavHost(
            navController = navController,
            startDestination =NavItem.HomeNote.route,
            modifier = Modifier.padding(innerPaddingValues),
        )
        {
            composable(NavItem.HomeNote.route)
            {


                    HomeNote(navController)
            }
            composable(NavItem.NewNote.route)
            {
                        NewNote(navController)
            }
            composable(NavItem.About.route)
            {
                         About()
            }
            composable(NavItem.Detail.route){backStackEntry ->

                val id = backStackEntry.arguments?.getString("id")
                if(id != null)
                {
                    DetailNotes(id, navController)
                }

            }
        }
}