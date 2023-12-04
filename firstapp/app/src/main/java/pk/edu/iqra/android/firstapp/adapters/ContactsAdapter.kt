package pk.edu.iqra.android.firstapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import pk.edu.iqra.android.firstapp.R
import pk.edu.iqra.android.firstapp.database.entities.Contacts
import pk.edu.iqra.android.firstapp.listeners.DeleteClickListener
import pk.edu.iqra.android.firstapp.listeners.EditClickListener

class ContactsAdapter(var data:List<Contacts>, val editClickListener: EditClickListener, val deleteClickListener: DeleteClickListener) : RecyclerView.Adapter<ContactsAdapter.ContactsVH>() {


    class ContactsVH(item: View): RecyclerView.ViewHolder(item){
        val txName = item.findViewById<TextView>(R.id.tx_name)
        val txMobileNumber = item.findViewById<TextView>(R.id.tx_mobile_number)
        val ivEdit = item.findViewById<ImageView>(R.id.iv_edit)
        val ivDelete = item.findViewById<ImageView>(R.id.iv_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ContactsVH(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ContactsVH, position: Int) {
        val Contacts = data[position]

        holder.txName.text = Contacts.name
        holder.txMobileNumber.text = Contacts.mobileNumber

        holder.ivEdit.setOnClickListener {
            editClickListener.onEditClicked(Contacts)
        }

        holder.ivDelete.setOnClickListener {
            deleteClickListener.onDeleteClicked(Contacts)
        }
    }
}