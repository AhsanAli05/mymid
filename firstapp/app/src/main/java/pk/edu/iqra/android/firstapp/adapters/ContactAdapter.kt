package pk.edu.iqra.android.firstapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pk.edu.iqra.android.firstapp.R
import pk.edu.iqra.android.firstapp.models.Contact

class ContactAdapter(var data:List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactVH>() {


    class ContactVH(item: View):RecyclerView.ViewHolder(item){
        val txName = item.findViewById<TextView>(R.id.tx_name)
        val txMobileNumber = item.findViewById<TextView>(R.id.tx_mobile_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ContactVH(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ContactVH, position: Int) {
        val contact = data[position]

        holder.txName.text = contact.name
        holder.txMobileNumber.text = contact.mobileNumber
    }
}