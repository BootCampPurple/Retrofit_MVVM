package pe.com.bootcamp.retrofitmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by eagskunst in 1/12/2019.
 */
abstract class BaseViewModel : ViewModel() {


    protected val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading


    protected val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    protected val _onNetworkError = MutableLiveData<String>()
    val onNetworkError: LiveData<String> = _onNetworkError


}