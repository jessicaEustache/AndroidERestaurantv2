package fr.isen.eustache.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.eustache.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.HomeBouton.setOnClickListener {
            changeActivity(getString(R.string.commencer))

        }



    }

    private fun changeActivity(category: String){
        val intent = Intent(this,CommencerActivity::class.java)
        intent.putExtra("category_type",category)
        startActivity(intent)
    }
}