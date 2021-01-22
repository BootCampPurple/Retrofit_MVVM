package pe.com.bootcamp.retrofitmvvm.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.data.NetworkMessage
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.rickmorty.CharacterResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.vacation.VacationPost
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


        initializeUI()
        setupViewModel()


        binding.butDiscount.setOnClickListener {
            val intent = Intent(this, DiscountActivity::class.java)
            startActivity(intent)

        }

        binding.butRequestVacation.setOnClickListener {

            viewModel.doSaveVacation(VacationPost("01/02/2021", "14/02/2021"))

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
        viewModel.vacationSaved.observe(this, vacationSavedObserver)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    //region observable

    private val vacationSavedObserver = Observer<String> {

        this.showDialog(it)


    }

    private val isViewLoadingObserver = Observer<Boolean> {
        if (it) {
            this.showLoadingView("Guardando...")
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