package com.android.bootcampbatch217.models.putuser

import com.google.gson.annotations.SerializedName

data class PutUserModel (
    @SerializedName("name") val name : String,
    @SerializedName("job") val job : String,
    @SerializedName("updatedAt") val updatedAt : String
)