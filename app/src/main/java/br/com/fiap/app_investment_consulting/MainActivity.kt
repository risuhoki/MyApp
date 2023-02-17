package br.com.fiap.app_investment_consulting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            if(hasValid() && isValidName(txtName.text.toString()) && isValidEmail(txtEmail.text.toString()) && isValidPhone(txtPhone.text.toString())){
                val intentProduct = Intent(this,ProductActivity::class.java)
                intentProduct.putExtra("name",txtName.text.toString())
                intentProduct.putExtra("email",txtEmail.text.toString())
                intentProduct.putExtra("phone",txtPhone.text.toString())
            }
        }
    }

    private fun hasValid():Boolean{
        if(
            txtName.text.toString().trim().isEmpty() ||
            txtEmail.text.toString().trim().isEmpty() ||
            txtPhone.text.toString().trim().isEmpty()
        ){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun isValidName(str:String):Boolean{
        val re = Regex("[A-Z][a-z].*[A-z][a-z].*")
        return str.matches(re)
    }

    private fun isValidPhone(str:String): Boolean{
        val re = Regex("^[0-9]{2}-([0-9]{8}|[0-9]{9})")
        return str.matches(re)
    }
    private fun isValidEmail(target:String):Boolean{
        if (TextUtils.isEmpty(target)){
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}