package com.example.hueray

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_multi_toggle.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_NUMBER_OF_STATES = "numberOfStates"
private const val ARG_TITLE = "title"

/**
 * A simple [Fragment] subclass.
 * Use the [MultiToggle.newInstance] factory method to
 * create an instance of this fragment.
 */
class MultiToggle : Fragment() {
    // TODO: Rename and change types of parameters
    private var numberOfStates: Int = 10
    private var mTitle: String? = "Multi State Toggle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            numberOfStates = it.getInt(ARG_NUMBER_OF_STATES)
            mTitle = it.getString(ARG_TITLE)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multi_toggle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        multi_toggle_title.text = mTitle

        for(i in 1..numberOfStates) {
            val chip = Chip(multi_state_chip_group.context)
            chip.text = i.toString()
            // necessary to get single selection working
            chip.isClickable = true
            chip.isCheckable = true
            multi_state_chip_group.addView(chip)
            chip.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param numberOfStates The number of states that can be toggled.
         * @param title The title of the multi state toggle.
         * @return A new instance of fragment MultiToggle.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(numberOfStates: Int, title: String) =
            MultiToggle().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NUMBER_OF_STATES, numberOfStates)
                    putString(ARG_TITLE, title)
                }
            }
    }
}
