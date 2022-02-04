package fr.isen.eustache.androiderestaurant

import android.icu.util.ULocale
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.eustache.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.eustache.androiderestaurant.model.CategoryModel
import fr.isen.eustache.androiderestaurant.model.DishModel


class CustomAdapter(private val dishes: List<DishModel>, val onDishClicked: (DishModel) -> Unit) : RecyclerView.Adapter<CustomAdapter.DishViewHolder>() {

    class DishViewHolder(binding: CategoryCellBinding): RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.dishPicture
        val dishName = binding.dishName
        val dishPrice = binding.dishPrice
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val binding = CategoryCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

        // sets the image to the imageview from our itemHolder class
        holder.dishName.text = dishes[position].name_fr

        //todo with picasso holder.dishPicture.setImageResource(dishes[position].getFirstPicture())

        Picasso.get()
            .load(dishes[position].getFirstPicture())
            .error(R.drawable.logo)
            .placeholder(R.drawable.logo)
            .into(holder.dishPicture)


        holder.dishPrice.text = dishes[position].getFormattedPrice()

        holder.itemView.setOnClickListener {
            onDishClicked(dishes[position])
        }

    }

    override fun getItemCount(): Int = dishes.size


}
