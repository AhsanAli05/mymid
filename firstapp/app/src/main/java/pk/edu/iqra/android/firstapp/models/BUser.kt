package pk.edu.iqra.android.firstapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BUser(
    val id:Int,
    val firstName:String,
    val lastName:String,
    val fatherName:String,
    val cnic:String,
    val dob:String
):Parcelable
