package pk.edu.iqra.android.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pk.edu.iqra.android.firstapp.adapters.ContactAdapter
import pk.edu.iqra.android.firstapp.adapters.ContactsAdapter
import pk.edu.iqra.android.firstapp.database.AppDatabase
import pk.edu.iqra.android.firstapp.database.entities.Contacts
import pk.edu.iqra.android.firstapp.listeners.DeleteClickListener
import pk.edu.iqra.android.firstapp.listeners.EditClickListener
import pk.edu.iqra.android.firstapp.models.BUser
import pk.edu.iqra.android.firstapp.models.Contact
import pk.edu.iqra.android.firstapp.models.KUser
import pk.edu.iqra.android.firstapp.utils.DataHolder
import kotlin.coroutines.CoroutineContext

class HomeActivity : AppCompatActivity(), CoroutineScope, EditClickListener, DeleteClickListener {
    private lateinit var rvMessageList:RecyclerView
    private lateinit var ivAdd:ImageView
    private lateinit var contactAdapter: ContactsAdapter
    private var job = Job()

    override val coroutineContext : CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.AppTheme_PremiumCustomer)
        setContentView(R.layout.activity_home)

        val txName = findViewById<TextView>(R.id.txName)
        //val name = intent.extras?.getString("name")
        //val kUser = intent.extras?.getSerializable("user") as KUser
        //val bUser = intent.extras?.getParcelable<BUser>("user")
        val bUser = DataHolder.bUser

        txName.text = "Hi, ${bUser?.fatherName}"

        ivAdd = findViewById(R.id.ivAdd)

        initializeList()

        ivAdd.setOnClickListener {
            addContact()
        }
    }

    private fun addContact(){
        val contacts = Contacts(name = "Fahad Ali", mobileNumber = "+92-345-2992002")

        launch {
            val id = AppDatabase.getDatabase(this@HomeActivity).contactDAO().insertOrUpdate(contacts)
            refreshContacts()
            showMessage("Contact added -> Id = $id")
        }

    }

    private fun initializeList(){
        launch {
            val list = AppDatabase.getDatabase(this@HomeActivity).contactDAO().getAllContacts()
            renderList(list)
        }
    }

    private fun renderList(list:List<Contacts>){
        rvMessageList = findViewById(R.id.rvMessageList)
        contactAdapter = ContactsAdapter(list, this, this)
        rvMessageList.adapter = contactAdapter
    }

    private fun refreshContacts(){
        launch {
            val list = AppDatabase.getDatabase(this@HomeActivity).contactDAO().getAllContacts()
            showList(list)
        }
        /*val list = ArrayList<Contact>()

        list.add(Contact("1","Mr. John 1","+92-344-2992002"))
        list.add(Contact("2","Mr. John 2","+92-344-2992002"))
        list.add(Contact("3","Mr. John 3","+92-344-2992002"))
        list.add(Contact("4","Mr. John 4","+92-344-2992002"))
        list.add(Contact("5","Mr. John 5","+92-344-2992002"))
        list.add(Contact("6","Mr. John 6","+92-344-2992002"))
        list.add(Contact("7","Mr. John 7","+92-344-2992002"))
        list.add(Contact("8","Mr. John 8","+92-344-2992002"))
        list.add(Contact("9","Mr. John 9","+92-344-2992002"))
        list.add(Contact("10","Mr. John 10","+92-344-2992002"))

        return list*/
    }

    private fun showList(list:List<Contacts>){
        contactAdapter.data = list
        contactAdapter.notifyDataSetChanged()
    }

    private fun showMessage(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClicked(contacts: Contacts) {
        launch {
            AppDatabase.getDatabase(this@HomeActivity).contactDAO().delete(contacts)
            refreshContacts()
        }
    }

    override fun onEditClicked(contacts: Contacts) {
        contacts.name = "Jamal Ali"
        contacts.mobileNumber = "+92-346-2992066"

        launch {
            val id = AppDatabase.getDatabase(this@HomeActivity).contactDAO().insertOrUpdate(contacts)
            showMessage("Contact updated -> Id = $id")
            refreshContacts()
        }
    }
}