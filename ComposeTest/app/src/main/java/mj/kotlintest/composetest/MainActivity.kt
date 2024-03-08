package mj.kotlintest.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mj.kotlintest.composetest.ui.FirstScreen
import mj.kotlintest.composetest.ui.ListScreen
import mj.kotlintest.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabApp()
        }
    }
}

@Composable
fun TabApp() {
    val tabTitles = listOf("Tab 1", "Tab 2")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.White,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp)
                    )
                }
            }
        }

        when (selectedTabIndex) {
            0 -> ComposeTestTheme {
                FirstScreen(modifier = Modifier.fillMaxSize())
            }
            1 -> ListScreen()
        }
    }
}

@Preview
@Composable
fun TabAppPreview() {
    TabApp()
}