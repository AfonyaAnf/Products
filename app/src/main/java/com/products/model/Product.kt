package com.products.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val count: Int?,
    @ColumnInfo
    val price: Int?
)