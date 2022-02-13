package fr.isen.eustache.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fr.isen.eustache.androiderestaurant.databinding.ActivityConnexionBinding

class ConnexionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnexionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConnexionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConnexion.setOnClickListener {
            if(validEmailLog() && validPassword()) {

            }
        }

        binding.Compte.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity((intent))
        }


    }

    private fun validEmailLog(): Boolean {
        if (binding.Email.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir un email",Toast.LENGTH_LONG).show()
            binding.Email.requestFocus()
            return false
        }
        return true
    }

    private fun validPassword(): Boolean {
        if (binding.Password.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir un mot de passe",Toast.LENGTH_LONG).show()
            binding.Password.requestFocus()
            return false
        } else if (binding.Password.text.toString().length < 8) {
            Toast.makeText(this, "Le mot de passe est trop court", Toast.LENGTH_LONG).show()
            binding.Password.requestFocus()
            return false
        }
        return true
    }

}