package com.pereslavtsev_o_yu.task1.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastReceiver: BroadcastReceiver() {

    var receivserText: String = ""

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == "ru.shalkoff.vsu_lesson2_2024.SURF_ACTION") {
            receivserText = intent.getStringExtra("message").toString()
            Toast.makeText(context, receivserText, Toast.LENGTH_LONG).show()
        }
    }
}
