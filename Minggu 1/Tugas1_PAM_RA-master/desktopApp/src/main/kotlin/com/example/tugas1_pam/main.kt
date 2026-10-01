package com.example.tugas1_pam

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tugas1_PAM",
    ) {
        App()
    }
}