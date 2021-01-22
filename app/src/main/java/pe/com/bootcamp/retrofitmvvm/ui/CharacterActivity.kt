package pe.com.bootcamp.retrofitmvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.data.NetworkMessage
import pe.com.bootcamp.retrofitmvvm.data.entities.dashboard.DashboardResponse
import pe.com.bootcamp.retrofitmvvm.data.entities.rickmorty.CharacterResponse
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityCharacterBinding
import pe.com.bootcamp.retrofitmvvm.databinding.ActivityMainBinding
import pe.com.bootcamp.retrofitmvvm.util.Constants
import pe.com.bootcamp.retrofitmvvm.viewmodel.BCPViewModel

@AndroidEntryPoint
class CharacterActivity : BaseActivity() {

    private lateinit var binding: ActivityCharacterBinding

    private val viewModel: BCPViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root, R.id.claCharacter)


        this.setupViewModel()


        binding.butCharacter.setOnClickListener {
            viewModel.doCharacterFetch(binding.eteCharacter.text.toString())
        }

    }


    private fun setupViewModel() {
        viewModel.character.observe(this, characterObserver)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    //region observable

    private val characterObserver = Observer<CharacterResponse> {

        binding.tviName.text = it.name

        Picasso.get()
            .load(it.image)
            .into(binding.iviCharacter)


    }

    private val isViewLoadingObserver = Observer<Boolean> {
        if (it) {
            this.showLoadingView("Obteniendo Usuario...")
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