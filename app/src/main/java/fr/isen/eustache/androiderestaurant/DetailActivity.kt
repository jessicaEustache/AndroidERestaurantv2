package fr.isen.eustache.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.eustache.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.eustache.androiderestaurant.model.DishModel
import fr.isen.eustache.androiderestaurant.model.ItemsViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish= intent.getSerializableExtra("dish") as DishModel

        binding.DetailTitle.text = dish.name_fr
        initDetail(dish)

    }

    private fun initDetail(dish: DishModel){
        var nbInBucket = 1
        binding.DetailTitle.text = dish.name_fr

        binding.dishPhotoPager.adapter = DishPictureAdapter(this, dish.pictures)

        binding.dishIngredient.text = dish.ingredients.joinToString(",") {it.name_fr}

        binding.plus.setOnClickListener {
            nbInBucket++
            binding.compteur.text =nbInBucket.toString()
            binding.button.text = "AJOUTER AU PANIER " + (dish.prices[0].price.toFloat()*nbInBucket) + "€"
        }

        binding.moins.setOnClickListener{
            if(nbInBucket>0)
                nbInBucket--
            else nbInBucket = 0
            binding.compteur.text = nbInBucket.toString()
            binding.button.text = "AJOUTER AU PANIER " + (dish.prices[0].price.toFloat()*nbInBucket) + "€"

        }

        binding.button.setOnClickListener{
            val intent = Intent(this, ConnexionActivity::class.java)
            startActivity(intent)
        }

    }

}