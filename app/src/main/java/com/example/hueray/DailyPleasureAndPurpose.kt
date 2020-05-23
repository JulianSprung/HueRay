package com.example.hueray

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.hueray.database.HappyScore
import com.example.hueray.database.HappyScoreDatabase
import com.google.android.material.chip.ChipGroup
import com.google.android.play.core.internal.m
import kotlinx.android.synthetic.main.fragment_daily_pleasure_and_purpose.*
import kotlinx.android.synthetic.main.fragment_multi_toggle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DailyPleasureAndPurpose.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyPleasureAndPurpose : Fragment(), View.OnClickListener, ChipGroup.OnCheckedChangeListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mPleasureScore: Int? = ChipGroup.NO_ID
    private var mPurposeScore: Int? = ChipGroup.NO_ID

    // Something with having a different thread for the database write?
    private val fragmentScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_pleasure_and_purpose, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pleasure = childFragmentManager.findFragmentById(R.id.fragment_pleasure)
        val purpose = childFragmentManager.findFragmentById(R.id.fragment_purpose)

        //Setting the titles on the 3 child fragments for rating
        pleasure?.multi_toggle_title?.text = "Pleasure"
        purpose?.multi_toggle_title?.text = "Purpose"

        // Making this fragment listen to changes in multi state toggle (= the rating buttons)
        pleasure?.multi_state_chip_group?.setOnCheckedChangeListener(this)
        purpose?.multi_state_chip_group?.setOnCheckedChangeListener(this)

        // hiding the save button until pleasure and purpose are rated
        // and setting up this fragment as a listener when the save button is clicked.
        val saveButton = view.findViewById<Button>(R.id.button_save_daily)
        saveButton.visibility = View.INVISIBLE
        saveButton.setOnClickListener(this)
    }

    override fun onCheckedChanged(p0: ChipGroup?, p1: Int) {

        // when either the pleasure or the purpose score is updated we get both values and check if
        // the user has selected both. Then we will show the save button.
        val pleasure = childFragmentManager.findFragmentById(R.id.fragment_pleasure)
        val purpose = childFragmentManager.findFragmentById(R.id.fragment_purpose)
        mPleasureScore = pleasure?.multi_state_chip_group?.checkedChipId
        mPurposeScore = purpose?.multi_state_chip_group?.checkedChipId

        if(mPurposeScore != ChipGroup.NO_ID && mPleasureScore != ChipGroup.NO_ID) {
            view?.findViewById<Button>(R.id.button_save_daily)?.visibility  = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        // this function is called when the save button that we listen for is clicked

        if(mPleasureScore != null && mPleasureScore != null) {
            var score = HappyScore(
                Timestamp(System.currentTimeMillis()).toInstant().toString(),
                mPleasureScore!!.toInt()
            )

            // Write value to db in different thread
            fragmentScope.launch {
                val happinessDao =
                    HappyScoreDatabase.getDatabase(Application()).HappyScoreDao()
                happinessDao.insert(score)
            }
        }

        // Navigate back to the home screen
        findNavController().navigate(R.id.action_dailyPleasureAndPurpose_to_homeScreen)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DailyPleasureAndPurpose.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DailyPleasureAndPurpose().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
