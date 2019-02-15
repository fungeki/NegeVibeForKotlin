package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.ranuskin.ranloock.zibro.Adapters.GeneralEventCategoryFilterAdapter
import com.ranuskin.ranloock.zibro.Adapters.GeneralEventListAdapter
import com.ranuskin.ranloock.zibro.Objects.Category
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_general_event_list.*


class EventListFragment : Fragment() {

    var isFilterCategory = false
    var isFiltered = false
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
        val mAdapter = GeneralEventListAdapter(activity!!){ event ->
            val bundle = Bundle()
            bundle.putSerializable("event",event)
            val ft = activity!!.supportFragmentManager.beginTransaction().addToBackStack(null)
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right, R.anim.enter_from_right, R.anim.exit_from_right)
            val eventDetailFragment = EventDetailsFragment()
            eventDetailFragment.arguments = bundle
            ft.replace(R.id.fragments_container, eventDetailFragment).commit()
        }


            general_list_filter_category_spinner.setBackgroundColor(resources.getColor(R.color.colorWhite))
        general_event_list.adapter = mAdapter
        general_event_list_search_edittext.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let{ str->
                    mAdapter.filter.filter(str)
                    mAdapter.notifyDataSetChanged()
                }

            }

        })

        val mCategoryAdapter = GeneralEventCategoryFilterAdapter(
            context!!, R.layout.spinner_category_row_layout,
            Category.getList()
        )

        general_list_filter_category_spinner.adapter = mCategoryAdapter
        general_list_filter_category_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (isFiltered) {
                        view?.setBackgroundResource(R.drawable.background_spinner)
                        mAdapter.filterByType(position)
                        mAdapter.notifyDataSetChanged()
                        general_event_list_filter_layout.visibility = View.INVISIBLE
                        isFilterCategory = !isFilterCategory
                    }
                }
            }
        general_event_list_filter_background_view.setOnClickListener {
            isFiltered = true
            if (!isFilterCategory) {
                general_event_list_filter_layout.visibility = View.VISIBLE

            } else {
                general_event_list_filter_layout.visibility = View.INVISIBLE
            }
            isFilterCategory = !isFilterCategory
        }

    }


}
