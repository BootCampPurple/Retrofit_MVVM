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


class DashboardActivity : BaseActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claDashboard)

        initializeUI()

        binding.butDiscount.setOnClickListener {


        }
    }

    fun initializeUI() {

        //safe null
        //.let scope function
        intent.getParcelableExtra<DashboardResponse>("value")?.let { response ->

            binding.tviName.text = response.employee.fullName
            binding.tviEmail.text = response.employee.email

            binding.tviVacationPending.text =
                "Vacaciones pendientes ${response.vacation.pendientes}"
            binding.tviVacation.text = "Vacaciones ganadas ${response.vacation.ganados}"

        }

    }


}