package com.example.tugas1_pam

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform