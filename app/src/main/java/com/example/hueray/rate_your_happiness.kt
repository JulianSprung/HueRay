package com.example.hueray

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_rate_your_happiness.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.time.Instant
import kotlin.concurrent.timer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [rate_your_happiness.newInstance] factory method to
 * create an instance of this fragment.
 */


class rate_your_happiness : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_rate_your_happiness, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            // Database thread
            var score = HappinessScore(
                Timestamp(System.currentTimeMillis()).toInstant().toString(),
                this.button.text.toString().toInt()
            )

            // Write value to db
            GlobalScope.launch {
                val happinessDao =
                    HappinessRoomDatabase.getDatabase(Application(), scope = GlobalScope).HappinessStoreDao()
                happinessDao.insert(score)
            }

            // Navigate to 'thanks for rating view'
            findNavController().navigate(R.id.action_rate_your_happiness_to_thanks_for_rating)
        }

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            // Database thread
            var score = HappinessScore(
                Timestamp(System.currentTimeMillis()).toInstant().toString(),
                this.button2.text.toString().toInt()
            )

            // Write value to db
            GlobalScope.launch {
                val happinessDao =
                    HappinessRoomDatabase.getDatabase(Application(), scope = GlobalScope).HappinessStoreDao()
                happinessDao.insert(score)
                Log.d("MESSAGE", "Inserted score:")
            }

            // Navigate to 'thanks for rating view'
            findNavController().navigate(R.id.action_rate_your_happiness_to_thanks_for_rating)
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
            rate_your_happiness().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
