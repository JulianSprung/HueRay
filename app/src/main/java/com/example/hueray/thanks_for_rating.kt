package com.example.hueray

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hueray.happyscore.adapter.HappyScoreListAdapter
import com.example.hueray.happyscore.database.HappyScoreDatabase
import com.example.hueray.happyscore.viewmodel.HappyScoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [thanks_for_rating.newInstance] factory method to
 * create an instance of this fragment.
 */
class thanks_for_rating : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var happyscoreViewModel: HappyScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        happyscoreViewModel = ViewModelProvider(this).get(HappyScoreViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thanks_for_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = HappyScoreListAdapter(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        happyscoreViewModel.lastTenScores.observe(this, Observer { scores ->
            // Update the cached copy of the words in the adapter.
            scores?.let { adapter.setScores(it) }
        })

        view.findViewById<Button>(R.id.button11).setOnClickListener {
            findNavController().navigate(R.id.action_thanks_for_rating_to_rate_your_happiness)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment thanks_for_rating.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            thanks_for_rating().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
