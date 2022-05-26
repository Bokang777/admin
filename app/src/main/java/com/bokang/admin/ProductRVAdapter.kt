package com.bokang.admin


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


open class ProductRVAdapter: RecyclerView.Adapter<ProductRVAdapter.ViewHolder>() {
    // creating variables for our list, context, interface and position.
    private var productRVModalArrayList: ArrayList<ProductRVModal>? = null
    private var productClickInterface: ProductClickInterface? = null
    var lastPos = -1
    private var context:Context? = null

    // creating a constructor.
    fun ProductRVAdapter(
        productRVModalArrayList: ArrayList<ProductRVModal>?,
        context: Context?,
        productClickInterface: ProductClickInterface?
    ) {
        this.productRVModalArrayList = productRVModalArrayList
        this.productClickInterface = productClickInterface
        this.context = context
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //creating variable for our image view and text view on below line.
        val productIV: ImageView
        val productTV: TextView
        val productPriceTV: TextView

        init {
            //initializing all our variables on below line.
            productIV = itemView.findViewById(R.id.idIVCourse)
            productTV = itemView.findViewById(R.id.idTVCOurseName)
            productPriceTV = itemView.findViewById(R.id.idTVCousePrice)
        }
    }

    //creating a interface for on click
    interface ProductClickInterface {
        fun onProductClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.product_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // setting data to our recycler view item on below line.
        val productRVModal: ProductRVModal = productRVModalArrayList!![position];
        holder.productTV.setText(productRVModal.getProductName());
        holder.productPriceTV.setText("Rs. " + productRVModal.getProductPrice());
        Picasso.get().load(productRVModal.getProductImg()).into(holder.productIV);
        // adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);
        holder.productIV.setOnClickListener{
            productClickInterface?.onProductClick(position)
        }
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPos) {
            // on below line we are setting animation.
            val animation: Animation =
                AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            itemView.animation = animation
            lastPos = position
        }
    }

    override fun getItemCount(): Int {
        return productRVModalArrayList!!.size
    }
}