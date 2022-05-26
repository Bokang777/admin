package com.bokang.admin

import android.os.Parcel
import android.os.Parcelable.Creator


open class ProductRVModal {
    // creating variables for our different fields.
    private var productName: String? = null
    private var productDescription: String? = null
    private var productPrice: String? = null
    private var bestSuitedFor: String? = null
    private var productImg: String? = null
    private var productLink: String? = null
    private var productId: String? = null

    fun getProductId(): String? {
        return productId
    }

    fun setProductId(productId: String?) {
        this.productId = productId
    }


    // creating an empty constructor.
    fun ProductRVModal() {}

    protected fun ProductRVModal(`in`: Parcel) {
        productName = `in`.readString()
        productId = `in`.readString()
        productDescription = `in`.readString()
        productPrice = `in`.readString()
        bestSuitedFor = `in`.readString()
        productImg = `in`.readString()
        productLink = `in`.readString()
    }

    val CREATOR: Creator<ProductRVModal> = object : Creator<ProductRVModal> {
        override fun createFromParcel(`in`: Parcel): ProductRVModal {
            return this@ProductRVModal
        }

        override fun newArray(size: Int): Array<ProductRVModal?> {
            return arrayOfNulls<ProductRVModal>(size)
        }
    }

    // creating getter and setter methods.
    fun getProductName(): String? {
        return productName
    }

    fun setCourseName(productName: String?) {
        this.productName = productName
    }

    fun getProductDescription(): String? {
        return productDescription
    }

    fun setProductDescription(productDescription: String?) {
        this.productDescription = productDescription
    }

    fun getProductPrice(): String? {
        return productPrice
    }

    fun setProductPrice(coursePrice: String?) {
        this.productPrice = coursePrice
    }

    fun getBestSuitedFor(): String? {
        return bestSuitedFor
    }

    fun setBestSuitedFor(bestSuitedFor: String?) {
        this.bestSuitedFor = bestSuitedFor
    }

    fun getProductImg(): String? {
        return productImg
    }

    fun setProductImg(productImg: String?) {
        this.productImg = productImg
    }

    fun getProductLink(): String? {
        return productLink
    }

    fun setProductLink(productLink: String?) {
        this.productLink = productLink
    }


    fun ProductRVModall(
        productId: String?,
        productName: String?,
        productDescription: String?,
        productPrice: String?,
        bestSuitedFor: String?,
        productImg: String?,
        productLink: String?
    ) {
        this.productName = productName
        this.productId = productId
        this.productDescription = productDescription
        this.productPrice = productPrice
        this.bestSuitedFor = bestSuitedFor
        this.productImg = productImg
        this.productLink = productLink
    }

    fun describeContents(): Int {
        return 0
    }

    fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(productName)
        dest.writeString(productId)
        dest.writeString(productDescription)
        dest.writeString(productPrice)
        dest.writeString(bestSuitedFor)
        dest.writeString(productImg)
        dest.writeString(productLink)
    }
}