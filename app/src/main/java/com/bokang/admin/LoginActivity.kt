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
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // initializing all our variables.
        val userNameEdt: TextInputEditText = findViewById(R.id.idEdtUserName)
        val passwordEdt: TextInputEditText = findViewById(R.id.idEdtPassword)
        val loginBtn: Button = findViewById(R.id.idBtnLogin)
        val newUserTV: TextView = findViewById(R.id.idTVNewUser)
        val loadingPB: ProgressBar = findViewById(R.id.idPBLoading)
        // adding click listener for our new user tv.
        newUserTV.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            loadingPB.visibility = View.VISIBLE
            // getting data from our edit text on below line.
            val email = userNameEdt.text.toString()
            val password = passwordEdt.text.toString()

            // on below line validating the text input.
            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter your credentials..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // on below line we are calling a sign in method and passing email and password to it.
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task->
                    // on below line we are checking if the task is success or not.
                    if (task.isSuccessful) {
                        // on below line we are hiding our progress bar.
                        loadingPB.visibility = View.GONE
                        Toast.makeText(this, "Login Successful..", Toast.LENGTH_SHORT).show()
                        // on below line we are opening our mainactivity.
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    } else {
                        // hiding our progress bar and displaying a toast message.
                        loadingPB.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "Please enter valid user credentials..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        // in on start method checking if
        // the user is already sign in.
        // in on start method checking if
        // the user is already sign in.
        val user: FirebaseUser? = mAuth.currentUser
        if (user != null) {
            // if the user is not null then we are
            // opening a main activity on below line.
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}