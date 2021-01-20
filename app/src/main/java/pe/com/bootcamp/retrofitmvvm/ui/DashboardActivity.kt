package pe.com.bootcamp.retrofitmvvm.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.data.NetworkMessage
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityDashboardBinding
import pe.com.bootcamp.retrofitmvvm.util.Constants
import pe.com.bootcamp.retrofitmvvm.viewmodel.BCPViewModel

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: BCPViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claDashboard)




        this.initializeUI()
        this.setupViewModel()

        binding.butDiscount.setOnClickListener {

            viewModel.doDashboardFetch()
        }
    }


    private fun initializeUI() {
        intent.getParcelableExtra<DashboardResponse>(Constants.INTENT_VALUE)?.let {
            binding.tviName.text = it.employee.fullName
            binding.tviEmail.text = it.employee.email

            binding.tviVacationPending.text = "Vacaciones pendientes: ${it.vacation.pendientes}"
            binding.tviVacation.text = "Vacaciones ganadas: ${it.vacation.ganados}"
        }
    }
    private fun setupViewModel() {

        viewModel.dashboard.observe(this, discountObserver)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onNetworkError.observe(this, networkObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)


    }

    //region observers

    private val discountObserver = Observer<DashboardResponse> {


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