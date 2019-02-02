package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Adapters.GeneralEventListAdapter
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_general_event_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EventListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        general_event_list.layoutManager = LinearLayoutManager(context)
        general_event_list.adapter = GeneralEventListAdapter{ event ->
            val bundle = Bundle()
            bundle.putSerializable("event",event)
            val ft = activity!!.supportFragmentManager.beginTransaction().addToBackStack(null)
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right, R.anim.enter_from_right, R.anim.exit_from_right)
            val eventDetailFragment = EventDetailsFragment()
            eventDetailFragment.arguments = bundle
            ft.replace(R.id.fragments_container, eventDetailFragment).commit()
        }


    }


}
