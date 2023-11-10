package com.example.boadcastreceiveractivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class Receptor : BroadcastReceiver() {

    private val TAG = "Receptor"
    var modoAvion = false
    var bateriaBaja = false

    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")

            modoAvion = intent.getBooleanExtra("modoAvion", false)
            bateriaBaja = intent.getBooleanExtra("bateriaBaja", false)

        }.toString().also { log ->
            Log.d(TAG, log)
            Toast.makeText(context, log, Toast.LENGTH_LONG).show()
        }
    }
}
