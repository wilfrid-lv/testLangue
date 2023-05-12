package com.example.testlangue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var mBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate() // call LoadLocate
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.app_name)


        mBtn = findViewById(R.id.mChangeLang)

        mBtn.setOnClickListener {

            showChangeLang()

        }

    }

    private fun showChangeLang() {

        val listItmes = arrayOf("Français", "English", "Español")

        val mBuilder = AlertDialog.Builder(this@MainActivity)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItmes, -1) { dialog, which ->
            if (which == 0) {
                setLocate("fr")
                recreate()
            } else if (which == 1) {
                setLocate("en")
                recreate()
            } else if (which == 2) {
                setLocate("es")
                recreate()
            }

            dialog.dismiss()
        }
        val mDialog = mBuilder.create()

        mDialog.show()

    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        val newContext = createConfigurationContext(config)
        //baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }


    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", null)
        setLocate(language !!)
    }
}