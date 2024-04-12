package com.pereslavtsev_o_yu.task1

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pereslavtsev_o_yu.task1.provider.Resolver
import com.pereslavtsev_o_yu.task1.receiver.BroadcastReceiver
import com.pereslavtsev_o_yu.task1.ui.theme.Task1Theme

class MainActivity : ComponentActivity() {

    private lateinit var receiver: BroadcastReceiver
    private var resolverText = ""
    private var isReceiverRegistered = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val resolver = Resolver(contentResolver)
        receiver = BroadcastReceiver()
        findViewById<Button>(R.id.button).setOnClickListener(View.OnClickListener {
            resolverText = resolver.fetchSecretKey(
                "content://dev.surf.android.provider/text", "text").toString()
            Toast.makeText(this, resolverText, Toast.LENGTH_LONG).show()
        })

        findViewById<Button>(R.id.button2).setOnClickListener(View.OnClickListener {
            val filter = IntentFilter("ru.shalkoff.vsu_lesson2_2024.SURF_ACTION")
            registerReceiver(receiver, filter, RECEIVER_EXPORTED)
            isReceiverRegistered = true
        })
    }

    override fun onDestroy() {
        if (isReceiverRegistered) unregisterReceiver(receiver)
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("receiver", receiver?.receivserText)
        outState.putString("resolver", resolverText)
        Log.i("App", "Sleep")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Receiver", savedInstanceState.getString("receiver").toString())
        Log.i("Resolver", savedInstanceState.getString("resolver").toString())
    }



}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Task1Theme {
        Greeting("Android")
    }
}