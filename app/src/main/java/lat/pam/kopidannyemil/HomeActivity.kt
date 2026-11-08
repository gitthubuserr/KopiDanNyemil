
package lat.pam.kopidannyemil

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_home)

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val savedName = sharedPref.getString("name", "")
        val tvHalo = findViewById<TextView>(R.id.tvHalo)
        tvHalo.text = "Halo $savedName,"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val products = listOf(

            Product(
                "Kopi Susu Gula Aren",
                "Espresso, susu, gula aren",
                "Rp 22.000",
                R.drawable.kopsus
            ),

            Product(
                "Donat Cokelat",
                "Donat empuk, glaze cokelat",
                "Rp 12.000",
                R.drawable.doncok
            ),

            Product(
                "Sourdough Loaf",
                "Roti fermentasi alami",
                "Rp 35.000",
                R.drawable.sordo
            ),

            Product(
                "Cappuccino",
                "Espresso, foam susu lembut",
                "Rp 25.000",
                        R.drawable.capp
            ),

            Product(
                "Matcha Latte",
                "Matcha Jepang, susu segar",
                "Rp 27.000",
                R.drawable.matchla
            )

        )

        val recyclerView =
            findViewById<RecyclerView>(R.id.rvProducts)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter =
            ProductAdapter(products)

        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val btnProfile = findViewById<Button>(R.id.btnProfile)

        btnOrder.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}

