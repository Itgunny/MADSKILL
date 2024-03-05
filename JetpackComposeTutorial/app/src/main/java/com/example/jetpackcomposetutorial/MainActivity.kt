package com.example.jetpackcomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Composable
fun Greeting(names: List<String>) {
    for(name in names) {
        Text("Hello $name")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTutorialTheme {
        Greeting("Android")
    }
}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}

@Composable
fun SharedPrefsToggle(text: String, value: Boolean, onValueChanged: (Boolean) -> Unit) {
    Row {
       Text(text)
       Checkbox(checked = value, onCheckedChange = onValueChanged)
    }
}

@Suppress("UNUSED_PARAMETER")
@Composable
fun MyFancyNavigation(content: @Composable () -> Unit) {
}

@Composable
fun StartScreen() {
}

@Composable
fun MiddleScreen() {
}

@Composable
fun EndScreen() {
}

// Composable functions can execute in any order.
// Composable 함수는 호출 순서와 상관 없이 실행됨.
@Composable
fun ButtonRow() {
    MyFancyNavigation {
        StartScreen()
        MiddleScreen()
        EndScreen()
    }
}

// Composable functions can run in parallel.
// Composable 함수는 동시에 실행할 수 있어서 스레드 사용시 주의 해야함.
@Composable
fun ListComposable(myList: List<String>) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for(item in myList) {
                Text("Item: $item")
            }
        }
        Text("Count: ${myList.size}")
    }
}

@Composable
@Deprecated("Example with bug")
fun ListWithBug(myList: List<String>) {
    var items = 0
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for(item in myList) {
                Text("Item : $item")
                items++ // Avoid! Side-effect of the column recomposing.
            }
        }
    }
}

/**
 * Display a list of names the user can click with header
 */
@Composable
fun NamePicker(
    header: String,
    names: List<String>,
    onNameClicked: (String) -> Unit
) {
    Column {
        // this will recompose when [header] changes, but not when [names] changes
        Text(header, style = MaterialTheme.typography.bodyLarge)
        Divider()

        // LazyColumn is the Compose version of a RecyclerView.
        // The lambda passed to items() is similar to a RecyclerView.ViewHolder.
        LazyColumn{
            items(names) {name ->
                // When an item's [name] updates, the adapter for that item
                // will recompose. This will not recompose when [header] changes
                NamePickerItem(name, onNameClicked)
            }
        }
    }
}

/**
 * Display a single name the user can click
 */
@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {
    Text(name, Modifier.clickable(onClick = { onClicked(name) }))
}




