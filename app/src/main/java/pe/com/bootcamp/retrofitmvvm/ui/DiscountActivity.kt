package pe.com.bootcamp.retrofitmvvm.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.adapter.DiscountAdapter
import pe.com.bootcamp.retrofitmvvm.data.NetworkMessage
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.Discount
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityDashboardBinding
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityDiscountBinding
import pe.com.bootcamp.retrofitmvvm.util.Constants
import pe.com.bootcamp.retrofitmvvm.viewmodel.BCPViewModel

@AndroidEntryPoint
class DiscountActivity : BaseActivity() {


    private lateinit var binding: ActivityDiscountBinding
    private val viewModel: BCPViewModel by viewModels()

    private lateinit var adapter: DiscountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount)

        binding = ActivityDiscountBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claDiscount)


        setupViewModel()


        adapter = DiscountAdapter()


        binding.rviDiscounts.layoutManager = LinearLayoutManager(this)
        binding.rviDiscounts.adapter = adapter


        viewModel.doDiscountFetch()

    }


    private fun setupViewModel() {
        viewModel.discount.observe(this, discountsObserver)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    //region observable

    private val discountsObserver = Observer<List<Discount>> {

        adapter.arrayDiscount = it

    }

    private val isViewLoadingObserver = Observer<Boolean> {
        if (it) {
            this.showLoadingView("Cargando Descuentos...")
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