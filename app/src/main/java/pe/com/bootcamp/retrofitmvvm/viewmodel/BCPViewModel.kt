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

    //private val _discount = MutableLiveData<List<Discount>>()
    //val discount: LiveData<List<Discount>> = _discount

    protected val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading


    protected val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError


    fun doDashboardBCP() {

        _isViewLoading.postValue(true)

        viewModelScope.launch {

            val result: Result<DashboardResponse> = withContext(Dispatchers.IO) {
                repository.dashboardBCP()
            }


            when (result) {
                is Result.Success -> {
                    _dashboard.value = result.data
                }

                is Result.ApiError -> {
                    _onMessageError.postValue("Hubo un problema de conexión")

                }
            }


            _isViewLoading.postValue(false)


        }


    }


}