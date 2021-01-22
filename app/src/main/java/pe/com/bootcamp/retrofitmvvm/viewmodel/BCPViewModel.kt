package pe.com.bootcamp.retrofitmvvm.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import pe.com.bootcamp.retrofitmvvm.data.remote.Result
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.com.bootcamp.retrofitmvvm.data.entities.GenericResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.Discount
import pe.com.bootcamp.retrofitmvvm.data.entities.discount.DiscountResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.rickmorty.CharacterResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.vacation.VacationPost
import pe.com.bootcamp.retrofitmvvm.data.repository.BCPRepository


class BCPViewModel @ViewModelInject constructor(private val repository: BCPRepository) :
    BaseViewModel() {

    private val _dashboard = MutableLiveData<DashboardResponse>()
    val dashboard: LiveData<DashboardResponse> = _dashboard

    private val _discount = MutableLiveData<List<Discount>>()
    val discount: LiveData<List<Discount>> = _discount

    private val _character = MutableLiveData<CharacterResponse>()
    val character: LiveData<CharacterResponse> = _character

    private val _vacationSaved = MutableLiveData<String>()
    val vacationSaved: LiveData<String> = _vacationSaved


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

    fun doCharacterFetch(id: String) {
        _isViewLoading.postValue(true)

        viewModelScope.launch {

            val result: Result<CharacterResponse> = withContext(Dispatchers.IO) {
                repository.getCharacterRickMorty(id)
            }


            _isViewLoading.postValue(false)

            when (result) {
                is Result.Success -> {
                    _character.value = result.data

                }
                is Result.ApiError -> _onMessageError.postValue(result.exception)


            }

        }

    }


    fun doSaveVacation(post: VacationPost) {
        _isViewLoading.postValue(true)

        viewModelScope.launch {

            val result: Result<GenericResponse> = withContext(Dispatchers.IO) {
                repository.saveVacation(post)
            }


            _isViewLoading.postValue(false)

            when (result) {
                is Result.Success -> {
                    _vacationSaved.value = result.data.message

                }
                is Result.ApiError -> _onMessageError.postValue(result.exception)


            }

        }

    }

}