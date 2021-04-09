package pe.com.bootcamp.retrofitmvvm.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import pe.com.bootcamp.retrofitmvvm.data.remote.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.Discount
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.DiscountResponse
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