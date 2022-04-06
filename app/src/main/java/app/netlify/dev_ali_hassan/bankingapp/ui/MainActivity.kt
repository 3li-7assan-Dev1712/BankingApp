package app.netlify.dev_ali_hassan.bankingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.netlify.dev_ali_hassan.bankingapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}