package com.bokang.admin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // initializing all our variables.
        val userNameEdt: TextInputEditText = findViewById(R.id.idEdtUserName)
        val passwordEdt: TextInputEditText = findViewById(R.id.idEdtPassword)
        val loadingPB : ProgressBar  = findViewById(R.id.idPBLoading)
        val confirmPwdEdt: TextInputEditText = findViewById(R.id.idEdtConfirmPassword)
        val loginTV: TextView = findViewById(R.id.idTVLoginUser)
        val registerBtn: Button = findViewById(R.id.idBtnRegister)
        val mAuth = FirebaseAuth.getInstance()

        loginTV.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerBtn.setOnClickListener {
            loadingPB.visibility = View.VISIBLE

            // getting data fro =m our edit text.
            val userName = userNameEdt.text.toString()
            val pwd  = passwordEdt.text.toString()
            val cnfPwd = confirmPwdEdt.text.toString()

            // checking if the password and confirm password is equal or not.
            if (pwd != cnfPwd) {
                Toast.makeText(this, "Please check both having same password..", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)) {

                // checking if the text fields are empty or not.
                Toast.makeText(this, "Please enter your credentials..", Toast.LENGTH_SHORT).show()
            } else {
                // on below line we are creating a new user by passing email and password.
                mAuth.createUserWithEmailAndPassword(userName, pwd)
                    .addOnCompleteListener(this) { task ->
                        // on below line we are checking if the task is success or not.
                        if (task.isSuccessful) {
                            // in on success method we are hiding our progress bar and opening a login activity.
                            loadingPB.visibility = View.GONE
                            Toast.makeText(this, "User Registered..", Toast.LENGTH_SHORT).show()
                            val i = Intent(this, LoginActivity::class.java)
                            startActivity(i)
                            finish()
                        } else {
                            // in else condition we are displaying a failure toast message.
                            loadingPB.visibility = View.GONE
                            Toast.makeText(this, "Fail to register user..", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

            }
        }
    }
}