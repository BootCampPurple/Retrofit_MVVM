package pe.com.bootcamp.retrofitmvvm.util.component

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.view_loading.view.*
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.util.Constants


class LoadingView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    init {


        inflate(context, R.layout.view_loading, this)

        attrs?.let {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.LoadingView)
            tviTitleLoader.text = attributes.getString(R.styleable.LoadingView_title)


            attributes.recycle()
        }


    }

    fun updateText(message: String) {
        tviTitleLoader.text = message

    }

    fun initializeUI(
        title: String,
        backgroundLoader: Constants.EnumViewLoading = Constants.EnumViewLoading.VIEW_BACKGROUND_TRANSPARENT
    ) {

        var textColor = R.color.colorAccent


        when (backgroundLoader) {
            Constants.EnumViewLoading.VIEW_BACKGROUND_TRANSPARENT -> {

                this.llaLoading.setBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorLoading,
                        null
                    )
                )

                textColor = R.color.white


            }
            Constants.EnumViewLoading.VIEW_BACKGROUND_WHITE -> {
                this.llaLoading.setBackgroundColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        null
                    )
                )

                textColor = R.color.colorAccent

            }

        }

        tviTitleLoader.text = title

        this.tviTitleLoader.setTextColor(
            ResourcesCompat.getColor(
                resources,
                textColor,
                null
            )
        )


    }


}