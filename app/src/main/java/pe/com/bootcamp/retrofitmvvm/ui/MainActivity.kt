package pe.com.bootcamp.retrofitmvvm.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.data.NetworkMessage
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityMainBinding
import pe.com.bootcamp.retrofitmvvm.util.Constants
import pe.com.bootcamp.retrofitmvvm.viewmodel.BCPViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BCPViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claMain)


        this.setupViewModel()

        binding.butLogin.setOnClickListener {

            viewModel.doDashboardFetch()
        }

    }

    private fun setupViewModel() {

        viewModel.dashboard.observe(this, dashboardObserver)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onNetworkError.observe(this, networkObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)


    }

    //region observers

    private val dashboardObserver = Observer<DashboardResponse> {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra(
            Constants.INTENT_VALUE,
            it
        )
        startActivity(intent)

    }

    private val networkObserver = Observer<String> {

        this.showDialog(it)
    }


    private val isViewLoadingObserver = Observer<Boolean> {

        if (it) {
            this.showLoadingView(resources.getString(R.string.general_loading))
        } else {
            this.hideLoadingView()
        }

    }


    private val onMessageErrorObserver = Observer<Any> {


        val error = it as NetworkMessage
        this.showDialog(error.body)


    }


    //endregion
}