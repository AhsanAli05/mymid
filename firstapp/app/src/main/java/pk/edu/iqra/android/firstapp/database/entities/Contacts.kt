package pk.edu.iqra.android.firstapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CONTACTS")
data class Contacts (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val id : Int = 0,
    @ColumnInfo(name = "NAME") var name:String,
    @ColumnInfo(name = "MOBILE_NUMBER") var mobileNumber:String
)