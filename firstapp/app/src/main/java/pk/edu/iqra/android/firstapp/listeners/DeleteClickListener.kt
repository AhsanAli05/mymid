package pk.edu.iqra.android.firstapp.listeners

import pk.edu.iqra.android.firstapp.database.entities.Contacts

interface DeleteClickListener {
    fun onDeleteClicked(contacts: Contacts)
}