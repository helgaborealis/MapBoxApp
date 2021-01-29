package net.borlis.mapboxandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mapbox.mapboxsdk.Mapbox
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val generalErrorViewModel: GeneralErrorViewModel by viewModel()
    private val dialogManager: DialogManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        generalErrorViewModel.generalErrorEvents.observe(this) {
            dialogManager.unexpectedErrorDialog(this).show()
        }
    }
}

