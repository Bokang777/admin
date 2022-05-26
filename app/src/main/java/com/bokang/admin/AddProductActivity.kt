package com.bokang.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class AddProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        // initializing all our variables.
        val addProductBtn: Button = findViewById(R.id.idBtnAddCourse)
        val productNameEdt: TextInputEditText = findViewById(R.id.idEdtCourseName)
        val productDescEdt: TextInputEditText = findViewById(R.id.idEdtCourseDescription)
        val productPriceEdt: TextInputEditText = findViewById(R.id.idEdtCoursePrice)
        val bestSuitedEdt: TextInputEditText = findViewById(R.id.idEdtSuitedFor)
        val productImgEdt: TextInputEditText = findViewById(R.id.idEdtCourseImageLink)
        val productLinkEdt: TextInputEditText = findViewById(R.id.idEdtCourseLink)
        val loadingPB: ProgressBar = findViewById(R.id.idPBLoading)
        val firebaseDatabase = FirebaseDatabase.getInstance()

        // on below line creating our database reference.
        val databaseReference = firebaseDatabase.getReference("Courses")
        // adding click listener for our add course button.
        addProductBtn.setOnClickListener {
            loadingPB.visibility = View.VISIBLE
            // getting data from our edit text.
            // getting data from our edit text.
            val productName: String = productNameEdt.text.toString()
            val productDesc: String = productDescEdt.text.toString()
            val productPrice: String = productPriceEdt.text.toString()
            val bestSuited = bestSuitedEdt.text.toString()
            val productImg: String = productImgEdt.text.toString()
            val productLink: String = productLinkEdt.text.toString()
            val productID = productName
            // on below line we are passing all data to our modal class.
            // on below line we are passing all data to our modal class.
            val productRVModal = ProductRVModal().ProductRVModall(
                productID,
                productName,
                productDesc,
                productPrice,
                bestSuited,
                productImg,
                productLink
            )
            // on below line we are calling a add value event
            // to pass data to firebase database.
            databaseReference.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    // on below line we are setting data in our firebase database.
                    databaseReference.child(productID).setValue(productRVModal)
                    // displaying a toast message.
                    // displaying a toast message.
                    Toast.makeText(this@AddProductActivity, "Course Added..", Toast.LENGTH_SHORT)
                        .show()
                    // starting a main activity.
                    // starting a main activity.
                    startActivity(Intent(this@AddProductActivity, MainActivity::class.java))

                }

                override fun onCancelled(error: DatabaseError) {
                    // displaying a failure message on below line.
                    Toast.makeText(this@AddProductActivity, "Fail to add Course..", Toast.LENGTH_SHORT).show()

                }
            })

        }
    }
}