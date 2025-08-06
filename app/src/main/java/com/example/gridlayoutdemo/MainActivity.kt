package com.example.gridlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gridlayoutdemo.ui.theme.GridLayoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridLayoutDemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GridDemo()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridDemo() {
    val items = (1..20).map { "Item $it" }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("📌 Fixed Grid (2 Columns)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(160.dp)
        ) {
            items(items.size) {
                GridItem(items[it])
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("🔄 Adaptive Grid (min 100.dp)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            modifier = Modifier.height(160.dp)
        ) {
            items(items.size) {
                GridItem(items[it])
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("📐 Staggered-like Grid", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.height(180.dp)
        ) {
            items(items.size) {
                val height = if (it % 2 == 0) 60.dp else 100.dp
                GridItem(items[it], height)
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("⭐ Varying Span Grid", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.height(180.dp)
        ) {
            itemsIndexed(items) { index, item ->
                val span = if (index % 5 == 0) 3 else 1
                GridItemWithSpan(item, span)
            }
        }
    }
}

@Composable
fun GridItem(text: String, height: Dp = 80.dp) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color(0xFFE3F2FD))
            .height(height)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}

@Composable
fun GridItemWithSpan(text: String, span: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color(0xFFFFF9C4))
            .height(60.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text("$text (Span $span)")
    }
}
