package com.example.ui.desktop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.delay

@Composable
fun DesktopScreen() {
    var showTerminal by remember { mutableStateOf(false) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Desktop Wallpaper
        Image(
            painter = painterResource(id = R.drawable.cyber_wallpaper),
            contentDescription = "Desktop Wallpaper",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        
        // Terminal Window
        if (showTerminal) {
            TerminalWindow(
                onClose = { showTerminal = false },
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.6f)
            )
        }
        
        // Taskbar
        Taskbar(
            onTerminalClick = { showTerminal = !showTerminal },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Taskbar(
    onTerminalClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentTime by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        while (true) {
            val sdf = SimpleDateFormat("hh:mm a", Locale("ar"))
            currentTime = sdf.format(Date())
            delay(1000)
        }
    }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Start Menu Button & Apps
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Open Start Menu */ }) {
                Icon(Icons.Default.Menu, contentDescription = "قائمة إبدأ", tint = Color.Cyan)
            }
            
            IconButton(onClick = onTerminalClick) {
                Icon(Icons.Default.Terminal, contentDescription = "تيرمنال", tint = Color.White)
            }
            
            IconButton(onClick = { /* Open File Explorer */ }) {
                Icon(Icons.Default.Folder, contentDescription = "ملفاتي", tint = Color.Yellow)
            }
            
            IconButton(onClick = { /* Open Browser */ }) {
                Icon(Icons.Default.Language, contentDescription = "المتصفح", tint = Color.LightGray)
            }
        }
        
        // System Tray & Clock
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Wifi, contentDescription = "واي فاي", tint = Color.White, modifier = Modifier.size(20.dp))
            Icon(Icons.Default.BatteryFull, contentDescription = "البطارية", tint = Color.White, modifier = Modifier.size(20.dp))
            Text(
                text = currentTime,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun TerminalWindow(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    var logs by remember { mutableStateOf(listOf("تهيئة نظام سايبر لينكس...", "جاري فحص النواة...", "تحميل مساحة العمل الخاصة بالمطور...")) }
    
    LaunchedEffect(Unit) {
        delay(1000)
        logs = logs + "الاتصال بخادم الذكاء الاصطناعي... [تم]"
        delay(800)
        logs = logs + "إعداد مختبر الهندسة العكسية... [تم]"
        delay(800)
        logs = logs + "root@cyber-linux:~# "
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1E1E1E).copy(alpha = 0.95f))
    ) {
        // Window Title Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("الموجه - Terminal", color = Color.Gray, fontSize = 12.sp)
            IconButton(onClick = onClose, modifier = Modifier.size(24.dp)) {
                Icon(Icons.Default.Close, contentDescription = "إغلاق", tint = Color.Red, modifier = Modifier.size(16.dp))
            }
        }
        
        // Window Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            logs.forEach { log ->
                Text(
                    text = log,
                    color = Color.Green,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
            // Blinking cursor simulation
            Text(
                text = "█",
                color = Color.Green,
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp
            )
        }
    }
}
