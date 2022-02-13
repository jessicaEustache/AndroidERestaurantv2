package fr.isen.eustache.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.eustache.androiderestaurant.databinding.ActivityCommencerBinding

class CommencerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommencerBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommencerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.CommencerEntrees.setOnClickListener{
            changeActivity(getString(R.string.entr_es))
        }

        binding.CommencerPlats.setOnClickListener{
            changeActivity(getString(R.string.plats))
        }

        binding.CommencerDesserts.setOnClickListener{
            changeActivity(getString(R.string.desserts))
        }


    }

    private fun changeActivity(category: String){
        val intent = Intent(this, DishActivity::class.java)
        intent.putExtra("category_type",category)
        startActivity(intent)
    }

}