package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Adapters.ChatListAdapter
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.DB.Update.updateUsername
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatChannel
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_chat_list.*
import kotlinx.android.synthetic.main.username_select_dialog.view.*
import java.util.regex.Pattern


/**
 * A simple [Fragment] subclass.
 *
 */
class GeneralChatChannelsFragment : Fragment() {

    var chatChannel = ChatChannel("0", "woof", "meow", "20:00")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chat_list_recycler_view.layoutManager = LinearLayoutManager(context)
        chat_list_recycler_view.adapter = ChatListAdapter{ event ->
            if (SignedInUser.getUsername() == null){
                showUsernameDialog()
            } else {
                val ft = activity!!.supportFragmentManager.beginTransaction().addToBackStack(null)
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right, R.anim.enter_from_right, R.anim.exit_from_right)
                val bundle = Bundle()
                bundle.putSerializable("event",event)
                val chatForEventFragment = ChatForEventFragment()
                chatForEventFragment.arguments = bundle
                ft.replace(R.id.fragments_container, chatForEventFragment).commit()
            }


        }


    }
    fun showUsernameDialog(){
        val builder = AlertDialog.Builder(context!!)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.username_select_dialog, null)

        builder.setView(dialogView)

        var dialog = builder.create()
        dialog.show()
        dialogView.username_select_dialog_accept.isEnabled = false
        dialogView.username_select_dialog_accept.setOnClickListener {
            val uid = SignedInUser.getUID()
            if (uid.isNotEmpty()){
                updateUsername(dialogView.username_select_dialog_name_edittext.text.toString().trim()) {
                    println("meow")
                    println("new usernamed successfully updated")
                    val ft = activity!!.supportFragmentManager.beginTransaction().addToBackStack(null)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right, R.anim.enter_from_right, R.anim.exit_from_right)
                    ft.replace(R.id.fragments_container, ChatForEventFragment()).commit()
                    SignedInUser.setUsername(dialogView.username_select_dialog_name_edittext.text.toString().trim())
                    dialog.dismiss()
                }
            }
        }
        dialogView.username_select_dialog_cancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogView.username_select_dialog_name_edittext.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let { str->
                    val strTrimmed = str.trim()
                    dialogView.username_select_dialog_accept.isEnabled = strTrimmed.length > 4 && isValidName(strTrimmed.toString())
                }
            }

        })



    }

    fun isValidName(str: String): Boolean{
        val num = ".*[0-9a-zA-Zא-ת ].*"
        val p = Pattern.compile(num)
        val matcher = p.matcher(str)

        return matcher.matches()
    }
}
