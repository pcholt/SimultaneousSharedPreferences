package com.example.simultaneoussharedpreferences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simultaneoussharedpreferences.ui.theme.SimultaneousSharedPreferencesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimultaneousSharedPreferencesTheme {
                Display(output = vm.output, onClick = {vm.trigger()})
            }
        }
    }
}

@Composable
fun Display(output:String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        Greeting(name="Trigger", onClick=onClick)
        Text(text = "output = $output")
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(onClick = onClick,  modifier = modifier) {
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimultaneousSharedPreferencesTheme {
        Greeting("Android") { /*TODO*/ }
    }
}