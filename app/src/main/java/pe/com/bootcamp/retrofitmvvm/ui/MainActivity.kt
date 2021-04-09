package pe.com.bootcamp.retrofitmvvm.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityMainBinding
import pe.com.bootcamp.retrofitmvvm.viewmodel.BCPViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: BCPViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claMain)



        binding.butLogin.setOnClickListener {

            viewModel.doDashboardBCP()
        }


        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.dashboard.observe(this, dashboardObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }


    private val dashboardObserver = Observer<DashboardResponse> { response ->

        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("value", response)
        startActivity(intent)

    }

    private val isViewLoadingObserver = Observer<Boolean> {

        if (it) {
            this.showLoadingView("Cargando")
        } else {
            this.hideLoadingView()
        }

    }


    private val onMessageErrorObserver = Observer<String> {

        showDialog(errorBody = it)
    }


}