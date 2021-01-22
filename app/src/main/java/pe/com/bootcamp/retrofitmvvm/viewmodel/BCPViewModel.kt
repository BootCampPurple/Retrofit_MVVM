package pe.com.bootcamp.retrofitmvvm.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import pe.com.bootcamp.retrofitmvvm.data.remote.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.Discount
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.DiscountResponse
import pe.com.bootcamp.retrofitmvvm.data.repository.BCPRepository


class BCPViewModel @ViewModelInject constructor(private val repository: BCPRepository) :
    BaseViewModel() {

    private val _dashboard = MutableLiveData<DashboardResponse>()
    val dashboard: LiveData<DashboardResponse> = _dashboard

    private val _discount = MutableLiveData<List<Discount>>()
    val discount: LiveData<List<Discount>> = _discount


    fun doDashboardFetch() {
        _isViewLoading.postValue(true)

        viewModelScope.launch {

            val result: Result<DashboardResponse> = withContext(Dispatchers.IO) {
                repository.dashboardBCP()
            }


            _isViewLoading.postValue(false)

            when (result) {
                is Result.Success -> {
                    _dashboard.value = result.data

                }
                is Result.ApiError -> _onMessageError.postValue(result.exception)


            }

        }


    }

    fun doDiscountFetch() {
        _isViewLoading.postValue(true)

        viewModelScope.launch {

            val result: Result<DiscountResponse> = withContext(Dispatchers.IO) {
                repository.discountBCP()
            }


            _isViewLoading.postValue(false)

            when (result) {
                is Result.Success -> {
                    _discount.value = result.data.discounts

                }
                is Result.ApiError -> _onMessageError.postValue(result.exception)


            }

        }


    }

}