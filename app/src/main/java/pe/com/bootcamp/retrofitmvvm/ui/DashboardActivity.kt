package pe.com.bootcamp.retrofitmvvm.ui

import android.content.Intent
import android.os.Bundle
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityDashboardBinding
import pe.com.bootcamp.retrofitmvvm.util.Constants


class DashboardActivity : BaseActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claDashboard)


        initializeUI()

        binding.butDiscount.setOnClickListener {
            val intent = Intent(this, DiscountActivity::class.java)
            startActivity(intent)

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


}