package com.example.boadcastreceiveractivity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val receptor = Receptor()

    companion object {
        const val ACTION_TEXTO_BATERIA = "com.example.boadcastreceiveractivity.ACTION_TEXTO_BATERIA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_LOW)
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(ACTION_TEXTO_BATERIA)

        val textoBateria: TextView = findViewById(R.id.controlBateria)

        registerReceiver(receptor, filter)
        actualizarTextoBateria(textoBateria, receptor.modoAvion, receptor.bateriaBaja)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receptor)
    }

    private fun actualizarTextoBateria(
        textoBateria: TextView,
        modoAvion: Boolean,
        bateriaBaja: Boolean
    ) {
        runOnUiThread {
            val mensaje = buildString {
                if (modoAvion) append("Modo Avión Activado\n")
                if (bateriaBaja) append("Batería Baja")
            }

            if (mensaje.isNotEmpty()) {
                textoBateria.setText(mensaje)
            } else {
                textoBateria.setText("Control de Batería")
            }
        }
    }
}
