package pe.com.bootcamp.retrofitmvvm.util

import androidx.constraintlayout.widget.ConstraintLayout


interface ILoadingViewProtocol {
    fun setupLoadingView(layout: ConstraintLayout)
    fun showLoadingView(message: String = "")
    fun hideLoadingView()
}