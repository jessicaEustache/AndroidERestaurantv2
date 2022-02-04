package fr.isen.eustache.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import fr.isen.eustache.androiderestaurant.databinding.ActivityDishBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject
import fr.isen.eustache.androiderestaurant.model.DishModel
import fr.isen.eustache.androiderestaurant.model.DishResult
import fr.isen.eustache.androiderestaurant.model.ItemsViewModel
import org.json.JSONObject

class DishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryType = intent.getStringExtra("category_type") ?:""
        binding.mainDishTitle.text = categoryType

        loadDishesFromCategory(categoryType)


    }

    private fun loadDishesFromCategory(categorType: String) {
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                val dishResult = Gson().fromJson(response.toString(), DishResult::class.java)
                displayDishes(dishResult.data.firstOrNull { category -> category.name_fr == categorType }?.items ?: listOf())
            }, {
                Log.e("DishActivity", "erreur lors de la récupération de la liste des plats")
            })
        Volley.newRequestQueue(this).add(jsonRequest)

    }

    private fun displayDishes(dishes: List<DishModel>) {
        binding.dishList.layoutManager = LinearLayoutManager(this)

        binding.dishList.adapter = CustomAdapter(dishes) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }
    }
}



