package com.example.note_app.navigation

sealed class NavItem(var label : String, var route : String)
{
    object HomeNote:NavItem("HomeNote", "home")
    object NewNote:NavItem("NewNote", "newnote")
    object About:NavItem("About", "about")
    object Detail:NavItem("Detail", "detail/{id}")
}