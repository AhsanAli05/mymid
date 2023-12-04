package pk.edu.iqra.android.firstapp.models;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Currency;

public class CUser implements Parcelable {
    public int userId;
    public String firstName;
    public String lastName;
    public String fatherName;
    public String cnic;
    public String dob;

    public CUser(Parcel parcel){
        userId = parcel.readInt();
        firstName = parcel.readString();
        lastName = parcel.readString();
        fatherName = parcel.readString();
        cnic = parcel.readString();
        dob = parcel.readString();
    }

    public static final Creator<CUser> CREATOR = new Creator<CUser>() {
        @Override
        public CUser createFromParcel(Parcel in) {
            return new CUser(in);
        }

        @Override
        public CUser[] newArray(int size) {
            return new CUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(fatherName);
        parcel.writeString(cnic);
        parcel.writeString(dob);
    }


}
