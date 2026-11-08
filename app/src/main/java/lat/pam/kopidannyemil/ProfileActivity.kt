
package lat.pam.kopidannyemil

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val savedName = sharedPref.getString("name", "")
        val tvHalo = findViewById<TextView>(R.id.tvHalo)
        tvHalo.text = "Halo $savedName,"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        btnOrder.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

            val etName = findViewById<EditText>(R.id.etName)
            val name = etName.text.toString().trim()

            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)

            when {
                name.isEmpty() -> {
                    Toast.makeText(this, "Nama harus diisi!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    sharedPref.edit()
                        .putString("name", name)
                        .apply()

                    Toast.makeText(this, "Update profile berhasil!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
