package fr.isen.eustache.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.eustache.androiderestaurant.databinding.ActivityRegisterBinding
import fr.isen.eustache.androiderestaurant.model.DishResult
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent (this, CommencerActivity::class.java)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.Enregistrer.setOnClickListener {
            if(validFistName() && validlastName() && validEmail() && validAdress() && validPassword()){
                val firstname = binding.firstName.text.toString()
                val lastname = binding.lastName.text.toString()
                val address = binding.address.text.toString()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()


                val url = "http://test.api.catering.bluecodegames.com/user/register"
                val jsonObject = JSONObject()
                jsonObject.put("id_shop", "1")
                jsonObject.put("firstname", firstname)
                jsonObject.put("lastname", lastname)
                jsonObject.put("address", address)
                jsonObject.put("email", email)
                jsonObject.put("password", password)


                val jsonRequest = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject, { response ->
                        var gson = Gson()
                        Log.d("","$response")

                    }, {
                        Log.e("", "erreur lors de la récupération")
                    })
                Volley.newRequestQueue(this).add(jsonRequest)
            }
            startActivity((intent))
        }
        binding.alreadyAccount.setOnClickListener {
            val intent = Intent(this,CommencerActivity::class.java)
            startActivity((intent))
        }

    }
    private fun validFistName(): Boolean {
        if (binding.firstName.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"saisir votre prénom",Toast.LENGTH_LONG).show()
            binding.firstName.requestFocus()
            return false
        }
        return true
    }

    private fun validlastName(): Boolean {
        if (binding.lastName.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"saisir votre nom",Toast.LENGTH_LONG).show()
            binding.lastName.requestFocus()
            return false
        }
        return true
    }

    private fun validEmail(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"saisir un email",Toast.LENGTH_LONG).show()
            binding.email.requestFocus()
            return false
        }
        return true
    }

    private fun validAdress(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"saisir un email",Toast.LENGTH_LONG).show()
            binding.email.requestFocus()
            return false
        }
        return true
    }

    private fun validPassword(): Boolean {
        if (binding.password.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"saisir un mot de passe",Toast.LENGTH_LONG).show()
            binding.password.requestFocus()
            return false
        } else if (binding.password.text.toString().length < 5) {
            Toast.makeText(this, "Le mot de passe est trop court", Toast.LENGTH_LONG).show()
            binding.password.requestFocus()
            return false
        }
        return true
    }
}