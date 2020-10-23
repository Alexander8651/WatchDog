package com.andromoticaia.watchingdog.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.andromoticaia.watchingdog.R
import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.domain.RepositoryImpl
import com.andromoticaia.watchingdog.viewmodel.VMFactory
import com.andromoticaia.watchingdog.viewmodel.ViewModelMainFragment

class MainActivity : AppCompatActivity() {

    val viewmodel by viewModels<ViewModelMainFragment>{VMFactory(RepositoryImpl(DataSource()))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel.getData().observe(this, Observer {
            Log.d("respuesta", it.toString())
        })
    }
}