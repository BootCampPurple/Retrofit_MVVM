package pe.com.bootcamp.retrofitmvvm.ui

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claMain)



        binding.butLogin.setOnClickListener {

        }

    }




}