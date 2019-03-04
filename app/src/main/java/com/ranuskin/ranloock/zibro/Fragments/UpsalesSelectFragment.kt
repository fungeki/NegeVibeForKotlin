package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ranuskin.ranloock.zibro.Adapters.UpsalesBasketAdapter
import com.ranuskin.ranloock.zibro.Interfaces.UpsalesDelegate
import com.ranuskin.ranloock.zibro.Objects.Upsales.UpsalesObj
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_upsales_select.*
import kotlinx.android.synthetic.main.fragment_upsales_select.view.*


class UpsalesSelectFragment : Fragment(), UpsalesDelegate {


    lateinit var mUpsalesList: MutableList<UpsalesObj>
    lateinit var mAdapter: UpsalesBasketAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upsales_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUpsalesList = mutableListOf()
        mAdapter = UpsalesBasketAdapter(context!!, this, mUpsalesList)
        fragment_upsales_select_recyclerview.layoutManager = LinearLayoutManager(context)
        fragment_upsales_select_recyclerview.adapter = mAdapter
        fragment_upsales_select_add_upsales_button.setOnClickListener{
            fragment_upsales_select_item_title_edittext.clearFocus()
            fragment_upsales_select_price_in_event_edittext.clearFocus()
            fragment_upsales_select_app_price_edittext.clearFocus()
            val title = fragment_upsales_select_item_title_edittext.text.toString().trim()
            if (title.count() < 5){
                showDialogForText("שם מוצר לא תקין", "נא להכניס שם מוצר מעל 5 אותיות", "סבבה")
                return@setOnClickListener
            }
            val priceOnEvent = fragment_upsales_select_price_in_event_edittext.text.toString().trim()
            if (priceOnEvent.count() < 1){
                showDialogForText("מחיר באירוע לא תקין", "נא להכניס מחיר מוצר באירוע", "סבבה")
                return@setOnClickListener
            }
            val priceOnApp = fragment_upsales_select_app_price_edittext.text.toString().trim()
            if (priceOnApp.count() < 1){
                showDialogForText("מחיר באפליקציה לא תקין", "נא להכניס מחיר מוצר באפליקציה", "סבבה")
                return@setOnClickListener
            }
            val mUpsalesObj = UpsalesObj(title, priceOnEvent.toDouble(), priceOnApp.toDouble())
            mUpsalesList.add(mUpsalesObj)
            mAdapter.notifyDataSetChanged()
            fragment_upsales_select_item_title_edittext.text.clear()
            fragment_upsales_select_price_in_event_edittext.text.clear()
            fragment_upsales_select_app_price_edittext.text.clear()
        }

    }

    private fun showDialogForText(title: String, message: String, buttonStr: String){
        // Initialize a new instance of
        val builder = AlertDialog.Builder(activity!!)

        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(buttonStr){dialog, which ->
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
    override fun removeItem(placed: Int) {
        mUpsalesList.removeAt(placed)
        mAdapter.notifyDataSetChanged()
    }

}
