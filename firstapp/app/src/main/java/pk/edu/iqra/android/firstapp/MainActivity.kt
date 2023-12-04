package pk.edu.iqra.android.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private var counter = 0
    private lateinit var txCounter:TextView
    private lateinit var btnCounter:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txCounter = findViewById(R.id.tx_counter)
        btnCounter = findViewById(R.id.btn_counter)

        if(savedInstanceState?.containsKey("counter") == true){
            counter = savedInstanceState.getInt("counter")
            //txCounter.visibility = View.VISIBLE
        }

        validateControl()
        //updateCounterOnUI(counter)

        /*btnCounter.setOnClickListener {
            updateCounterOnUI(counter++)
        }*/

        txCounter.setOnClickListener(this)
        btnCounter.setOnClickListener(this)
    }

    /*fun onCounterClicked(view: View) {
        counter++

        if(counter == 10){
            view.visibility = View.GONE
            return
        }
        updateCounterOnUI(counter)
    }*/

    private fun updateCounterOnUI(value:Int){
        txCounter.text = value.toString()
    }

    private fun validateControl(){
        if(counter<10){
            updateCounterOnUI(counter)
            return
        }

        if(counter==10){
            updateCounterOnUI(counter)
            btnCounter.visibility = View.GONE
            txCounter.visibility = View.VISIBLE
            return
        }

        if(counter>=10){
            counter = 0
            txCounter.visibility = View.VISIBLE
            btnCounter.visibility = View.VISIBLE
            updateCounterOnUI(counter)
            return
        }
    }
    override fun onClick(p0: View?) {
        /*if(p0?.id == R.id.btn_counter){
            updateCounterOnUI(counter++)
        }
        else if(p0?.id == R.id.tx_counter){
            if(counter >= 10){
                counter = 0
                btnCounter.visibility = View.VISIBLE
            }
        }*/

        when(p0?.id){
            R.id.btn_counter -> {
                ++counter
                validateControl()
            }
            R.id.tx_counter -> {
                ++counter
                validateControl()
            }
            /*else -> {

            }*/
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("counter",counter)
    }

    /*class ClickHandler : View.OnClickListener {
        override fun onClick(p0: View?) {

        }
    }*/
}