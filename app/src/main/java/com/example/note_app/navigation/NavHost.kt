package com.example.note_app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
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
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        )
        {
            composable(NavItem.HomeNote.route,
                enterTransition = {
                    slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start)
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    )
                })
            {


                    HomeNote(navController)
            }
            composable(NavItem.NewNote.route,
                enterTransition = {
                            scaleIn(
                                initialScale = 0.5f, // Màn hình bắt đầu từ kích thước 80%
                                animationSpec = tween(300, easing = EaseIn)
                            )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    )
                })
            {
                        NewNote(navController)
            }
            composable(NavItem.About.route,
                enterTransition = {
                    slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start)
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    )
                })
            {
                         About()
            }
            composable(NavItem.Detail.route,
                enterTransition = {
                    scaleIn(
                        initialScale = 0.5f, // Màn hình bắt đầu từ kích thước 80%
                        animationSpec = tween(300, easing = EaseIn)
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    )
                }){backStackEntry ->

                val id = backStackEntry.arguments?.getString("id")
                if(id != null)
                {
                    DetailNotes(id, navController)
                }

            }
        }
}