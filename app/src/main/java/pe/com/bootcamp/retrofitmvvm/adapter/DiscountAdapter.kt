package pe.com.bootcamp.retrofitmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import pe.com.bootcamp.retrofitmvvm.data.entities.discount.Discount
import pe.com.bootcamp.retrofitmvvm.databinding.ItemDiscountBinding
import pe.com.bootcamp.retrofitmvvm.util.ItemClickListener


class DiscountAdapter() : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<Discount>


    var arrayDiscount: List<Discount> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        //Este metodo hace la relacion con el layout del item
        val view = ItemDiscountBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Este metodo itera de acuerdo a lo que indicas en el metodo getItemCount

        val discount = arrayDiscount[position]
        holder.bind(discount)



    }

    override fun getItemCount(): Int {
        //Aqui indicas cuantas filas tendra tu RecyclerView
        return arrayDiscount.size
    }


    class ViewHolder(private val itemBinding: ItemDiscountBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        lateinit var binding: ItemDiscountBinding

        fun bind(discount: Discount) {

            binding = itemBinding
            itemBinding.tviNombrePelicula.text = discount.title

            Picasso.get()
                .load(discount.discountImage)
                .into(binding.iviProduct)


        }
    }

}