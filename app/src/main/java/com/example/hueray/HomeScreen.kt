package com.example.hueray

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home_screen.*



/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // If the "Daily Pleasure and Purpose" button is pressed we navigate to screen to rate your
        // daily pleasure and purpose
        button_daily.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_dailyPleasureAndPurpose)
        }

        // if the add event button is pressed we navigate to screen to free text about an event
        button_event.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_addEvent)
        }

        // If the show statics button is pressed we navigate to our old thanks for rating screen
        // which shows the last values.
        button_stats.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_thanks_for_rating)
        }
    }
}
