package pk.edu.iqra.android.firstapp.models

import java.io.Serializable

data class KUser(
    val id:Int,
    val firstName:String,
    val lastName:String,
    val fatherName:String,
    val cnic:String,
    val dob:String
):Serializable
