package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import com.example.ui.theme.CyberLinuxTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      CyberLinuxTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
          Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DashboardScreen(modifier = Modifier.padding(innerPadding))
          }
        }
      }
    }
  }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
  Text(text = "مرحباً بك في سايبر لينكس!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
  CyberLinuxTheme { DashboardScreen() }
}
