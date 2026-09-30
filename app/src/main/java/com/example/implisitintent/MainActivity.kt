package com.example.implisitintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnKirimPesan: Button = findViewById(R.id.btnKirimPesan)
        btnKirimPesan.setOnClickListener {
            val _sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra("address", "0811234")
                putExtra("sms_body", "ISI SMS")
                type = "text/plain"
            }

            val chooser = Intent.createChooser(_sendIntent, "PILIH APLIKASI")
            if (_sendIntent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        }

        val btnSetAlarm: Button = findViewById(R.id.btnSetAlarm)
        btnSetAlarm.setOnClickListener {
            val _alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "COBA ALARM")
                putExtra(AlarmClock.EXTRA_HOUR, 20)
                putExtra(AlarmClock.EXTRA_MINUTES, 15)
            }
            startActivity(_alarmIntent)
        }

        val btnSetTimer: Button = findViewById(R.id.btnSetTimer)
        btnSetTimer.setOnClickListener {
            val _timerIntent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "COBA TIMER")
                putExtra(AlarmClock.EXTRA_LENGTH, 20)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }
            startActivity(_timerIntent)
        }

        val etURL: EditText = findViewById(R.id.etURL)
        val btnOpenURL: Button = findViewById(R.id.btnOpenURL)

        btnOpenURL.setOnClickListener {
            val urlString = etURL.text.toString()
            val _webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://$urlString")
            )

            if (_webIntent.resolveActivity(packageManager) != null) {
                startActivity(_webIntent)
            } else {
                Toast.makeText(
                    this,
                    "Tidak ada Aplikasi Browser ditemukan",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}