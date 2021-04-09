package pe.com.bootcamp.retrofitmvvm.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.Discount
import pe.com.bootcamp.retrofitmvvm.data.repository.BCPRepository
import javax.inject.Inject

@HiltViewModel
class BCPViewModel @Inject constructor(private val repository: BCPRepository) :
    BaseViewModel() {

    private val _dashboard = MutableLiveData<DashboardResponse>()
    val dashboard: LiveData<DashboardResponse> = _dashboard

    private val _discount = MutableLiveData<List<Discount>>()
    val discount: LiveData<List<Discount>> = _discount




}