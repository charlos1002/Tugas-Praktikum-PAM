package com.example.tugas3pam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas3pam.ui.theme.Tugas3PAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas3PAMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState()),
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileCard()
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// 1. Reusable Composable: ProfileHeader
// (Menampilkan Gambar Profil Melingkar, Nama, dan Bio)
// -------------------------------------------------------------
@Composable
fun ProfileHeader(
    name: String,
    bio: String,
    imageResId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = bio,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

// -------------------------------------------------------------
// 2. Reusable Composable: InfoItem
// (Menampilkan item informasi berupa Icon, Label, dan Nilai)
// -------------------------------------------------------------
@Composable
fun InfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// -------------------------------------------------------------
// 3. Reusable Composable: ProfileCard
// (Card utama yang menggabungkan Komponen Header, List Info, dan Button)
// -------------------------------------------------------------
@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Memanggil ProfileHeader dengan file gambar profil Anda
            ProfileHeader(
                name = "Roberto Charlos Sagala",
                bio = "123140113 | Teknik Informatika",
                imageResId = R.drawable.profil_picture
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(8.dp))

            // List Informasi (Email, Phone, Location)
            InfoItem(
                icon = Icons.Default.Email,
                label = "Email",
                value = "roberto.123140113@student.itera.ac.id"
            )
            InfoItem(
                icon = Icons.Default.Phone,
                label = "Phone",
                value = "+62 812-3456-7890"
            )
            InfoItem(
                icon = Icons.Default.LocationOn,
                label = "Location",
                value = "Lampung, Indonesia"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button Interaktif
            Button(
                onClick = { /* Aksi ketika tombol diklik */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Contact Me")
            }
        }
    }
}

// -------------------------------------------------------------
// Preview untuk Android Studio
// -------------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    Tugas3PAMTheme {
        ProfileCard()
    }
}