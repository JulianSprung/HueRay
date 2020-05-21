package com.example.hueray.fragments.happy

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.hueray.R
import com.example.hueray.database.HappyScore
import com.example.hueray.database.HappyScoreDatabase
import kotlinx.android.synthetic.main.fragment_rate_your_happiness.*
import kotlinx.coroutines.*
import java.sql.Timestamp

/**
 * A simple [Fragment] subclass.
 * Use the [HappyRate.newInstance] factory method to
 * create an instance of this fragment.
 */

class HappyRate : Fragment() {
    val fragmentScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rate_your_happiness, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonIDs = listOf(R.id.button,R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10)
        for (buttonID in buttonIDs) {
            view.findViewById<Button>(buttonID).setOnClickListener {
                // Database thread
                var score = HappyScore(
                    Timestamp(System.currentTimeMillis()).toInstant().toString(),
                    view.findViewById<Button>(buttonID).text.toString().toInt()
                )

                // Write value to db
                fragmentScope.launch {
                    val happinessDao =
                        HappyScoreDatabase.getDatabase(Application()).HappyScoreDao()
                    happinessDao.insert(score)
                }

                // Navigate to 'thanks for rating view'
                findNavController().navigate(R.id.action_rate_your_happiness_to_thanks_for_rating)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment rate_your_happiness.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HappyRate().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
