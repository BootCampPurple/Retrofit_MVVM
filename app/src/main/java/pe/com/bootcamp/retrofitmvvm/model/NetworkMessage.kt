package pe.com.bootcamp.retrofitmvvm.model

import pe.com.bootcamp.retrofitmvvm.R
import pe.com.bootcamp.retrofitmvvm.util.Constants


data class NetworkMessage(
    val _body: String = "",
    val _httpCode: Int
) {

    var title: String = ""
    var body: String = ""
    var httpCode: Int = Constants.BAD_REQUEST

    init {


        this.title = "Mensaje"
        this.httpCode = _httpCode

        when (this.httpCode) {
            Constants.INT_SRV_ERROR, Constants.NOT_FOUND ->

                this.body = "Hubo un problema con el servidor. Estamos trabajando para solucionarlo."
            in 400..499 ->
                if (_body.isBlank()) {
                    this.body = "Hubo un problema con el servidor. Estamos trabajando para solucionarlo."
                } else {
                    this.body = _body
                }

            else -> this.body = "Hubo un problema con el servidor. Estamos trabajando para solucionarlo."
        }

    }
}