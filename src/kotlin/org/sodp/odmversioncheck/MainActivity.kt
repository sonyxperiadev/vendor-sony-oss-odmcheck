package org.sodp.odmversioncheck

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Register helper hook on help view button
        val helpBtn: FloatingActionButton = findViewById(R.id.helpBtn)
        helpBtn.setOnClickListener {
            val uri: Uri = Uri.parse(this.applicationContext.getString(R.string.helpUri))
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        //Update the views data fields
        val expVersionText: TextView = findViewById(R.id.expVersion)
        val gotVersionText: TextView = findViewById(R.id.gotVersion)
        val expVersion = getExpectedODMVersion()
        val gotVersion = getCurrentODMVersion()
        expVersionText.text = expVersion?.raw ?: "-"
        gotVersionText.text = gotVersion?.raw ?: "-"

        // We want to show a warning if:
        // 1. expVersion == null
        // 2. gotVersion == null
        // 3. expVersion != gotVersion
        if (expVersion == null || gotVersion == null || (expVersion.raw != gotVersion.raw))
            gotVersionText.setTextColor(Color.RED)
    }
}
