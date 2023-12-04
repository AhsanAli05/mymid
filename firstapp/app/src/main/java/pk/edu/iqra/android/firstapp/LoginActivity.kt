package pk.edu.iqra.android.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import pk.edu.iqra.android.firstapp.models.BUser
import pk.edu.iqra.android.firstapp.models.Customer
import pk.edu.iqra.android.firstapp.models.KUser
import pk.edu.iqra.android.firstapp.utils.DataHolder
import pk.edu.iqra.android.firstapp.utils.PrefManager
import pk.edu.iqra.android.firstapp.utils.getUIText

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edEmail:EditText
    private lateinit var edPassword:EditText
    private lateinit var btnSignIn:Button
    private lateinit var btnSignUp:Button
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edEmail = findViewById(R.id.ed_email)
        edPassword = findViewById(R.id.ed_password)
        btnSignIn = findViewById(R.id.btn_sign_in)
        btnSignUp = findViewById(R.id.btn_sign_up)

        btnSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)

        prefManager = PrefManager(this)

        /*if(prefManager.containData("id")){
            val id = prefManager.getData("id").toInt()
            val firstName = prefManager.getData("firstName")
            val lastName = prefManager.getData("lastName")
            val fatherName = prefManager.getData("fatherName")
            val cnic = prefManager.getData("cnic")
            val dob = prefManager.getData("dob")

            val bUser = BUser(id,firstName,lastName,fatherName,cnic,dob)
            goToHomeActivity(bUser)
        }*/

        if(prefManager.containData("user")){
            val gson = Gson()
            val userObjStr = prefManager.getData("user")
            val bUser = gson.fromJson(userObjStr, BUser::class.java)
            goToHomeActivity(bUser)
        }
        /*findViewById<Button>(R.id.btn_sign_in).setOnClickListener {
            //val kUser = KUser(1,"Muhammad","Wasim","Aziz","421873823293","10102010")
            val bUser = BUser(1,"Muhammad","Wasim","Aziz","421873823293","10102010")
            DataHolder.bUser = bUser
            Intent(this@LoginActivity,HomeActivity::class.java).apply {
                //putExtra("name","Muhammad Wasim")
                //putExtra("user",bUser)
                startActivity(this)
            }
        }*/
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_sign_in-> {
                doSignIn()
            }
            R.id.btn_sign_up-> {

            }
        }
    }

    private fun doSignIn(){
        val email = edEmail.getUIText()
        val edPassword = edPassword.getUIText()

        if(email.isNotEmpty() && edPassword.isNotEmpty()){
            val emailRegex = Regex("^[\\w!#\$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#\$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}\$")
            if(emailRegex.matches(email)){
                val bUser = BUser(1,"Muhammad","Wasim","Aziz","421873823293","10102010")

                /*prefManager.saveData("id",bUser.id.toString())
                prefManager.saveData("firstName",bUser.firstName)
                prefManager.saveData("lastName",bUser.lastName)
                prefManager.saveData("fatherName",bUser.fatherName)
                prefManager.saveData("cnic",bUser.cnic)
                prefManager.saveData("dob",bUser.dob)*/

                val gson = Gson()
                val userObjStr = gson.toJson(bUser)
                prefManager.saveData("user", userObjStr)

                goToHomeActivity(bUser)
            }
            else{
                Toast.makeText(this,"please enter correct email address",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this,"please enter credentials",Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToHomeActivity(bUser:BUser){
        DataHolder.bUser = bUser
        Intent(this@LoginActivity,HomeActivity::class.java).apply {
            startActivity(this)
        }
    }
}