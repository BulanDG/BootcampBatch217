package com.android.bootcampbatch217.models.patchuser

import com.google.gson.annotations.SerializedName

data class PatchUserModel (
    @SerializedName("name") val name : String,
    @SerializedName("job") val job : String,
    @SerializedName("updatedAt") val updatedAt : String
)