package pe.com.bootcamp.retrofitmvvm.ui

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import pe.com.bootcamp.retrofitmvvm.util.ILoadingViewProtocol
import pe.com.bootcamp.retrofitmvvm.util.component.LoadingView


open class BaseActivity : AppCompatActivity(), ILoadingViewProtocol {

    private lateinit var vLoading: LoadingView

    fun setContentView(rootView: View, idView: Int) {

        super.setContentView(rootView)
        this.setupLoadingView(findViewById(idView))

    }




    fun showDialog(errorBody: String) {

        Toast.makeText(this, errorBody, Toast.LENGTH_SHORT).show()
    }

    //region interface Loading View
    override fun setupLoadingView(layout: ConstraintLayout) {
        vLoading = LoadingView(this, null)
        vLoading.id = View.generateViewId()


        layout.addView(vLoading)

        val constraintSet = ConstraintSet()

        constraintSet.connect(
            vLoading.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            0
        )
        constraintSet.connect(
            vLoading.id,
            ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.LEFT,
            0
        )
        constraintSet.connect(
            vLoading.id,
            ConstraintSet.RIGHT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.RIGHT,
            0
        )
        constraintSet.connect(
            vLoading.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM,
            0
        )

        constraintSet.applyTo(layout)

        vLoading.elevation = 10f
        vLoading.visibility = View.GONE
    }

    override fun showLoadingView(message: String) {
        vLoading.initializeUI(message)
        vLoading.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        vLoading.visibility = View.GONE
    }

//endregion
}





