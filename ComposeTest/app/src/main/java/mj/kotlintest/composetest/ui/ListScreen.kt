package mj.kotlintest.composetest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListScreen() {
    val defaultList = (1..20).toList()
    var numbers by remember { mutableStateOf(emptyList<Int>()) }

    val onAddClicked = {
        numbers = numbers.toMutableList().apply {
            add(numbers.size + 1)
        }
    }
    val onRemoveClicked = {
        numbers = numbers.toMutableList().apply {
            if (isNotEmpty()) {
                removeLast()
            }
        }
    }
    val onResetClicked = {
        numbers = defaultList
    }

    // 컴포저블 함수의 Lifecycle에 맞춰서 코드 블럭 실행 및 취소해줌
    // key 값을 별도로 설정해주면 key가 변경되었을 때에도 재호출됨
    // Composition 시점에만 코드 블럭 실행되게끔 함
    // Recomposition 시점과 Decomposition 시점에는 무시
    LaunchedEffect(Unit) {
        numbers = defaultList
    }

    Column {
        TitleBar()
        ActionRow(
            onAddClicked,
            onRemoveClicked,
            onResetClicked
        )
        ItemList(numbers)
    }
}

@Composable
fun TitleBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(Color(0xFF997EE8))
    ) {
        Text(
            text = "List Controller",
            fontSize = 24.sp
        )
    }

}

@Composable
fun ActionRow(
    onAddClicked: () -> Unit,
    onRemoveClicked: () -> Unit,
    onResetClicked: () -> Unit
) {
    Row {
        Row(Modifier.weight(1f)) {
            ActionButton(text = "추가", Color(0xFFD9D9D9), onAddClicked)
        }
        Row(Modifier.weight(1f)) {
            ActionButton(text = "삭제", Color(0xFF944B4B), onRemoveClicked)
        }
        Row(Modifier.weight(1f)) {
            ActionButton(text = "초기화", Color(0xFF84D59F), onResetClicked)
        }
    }
}

@Composable
fun ActionButton(text: String, backgroundColor: Color, onClicked: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(backgroundColor)
            .clickable { onClicked() }
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}

@Composable
fun ItemList(numbers: List<Int>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(numbers) { NumberItem(it) }
    }
}

@Composable
fun NumberItem(number: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(5.dp)
            .background(Color.LightGray)
    ) {
        Text(
            text = "Item #$number",
            fontSize = 20.sp
        )
    }
}