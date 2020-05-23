package com.example.hueray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_event.*
import org.w3c.dom.Text

/**
 * A simple [Fragment] subclass.
 */
class addEvent : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_event, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonPlusPurpose = view.findViewById<View>(R.id.textViewPlusPupose) as Button
        buttonPlusPurpose.setOnClickListener(this)
        val buttonMinusPurpose = view.findViewById<View>(R.id.textViewMinusPurpose) as Button
        buttonMinusPurpose.setOnClickListener(this)
        val buttonPlusPleasure = view.findViewById<View>(R.id.textViewPlusPleasure) as Button
        buttonPlusPleasure.setOnClickListener(this)
        val buttonMinusPleasure = view.findViewById<View>(R.id.textViewMinusPleasure) as Button
        buttonMinusPleasure.setOnClickListener(this)
        val buttonSave = view.findViewById<View>(R.id.button_save_event) as Button
        buttonSave.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v == this.view?.findViewById<Button>(R.id.textViewPlusPupose)){
            val bar = this.view?.findViewById<SeekBar>(R.id.seekBarPurpose)
            if(bar != null) {
                if(bar.progress != bar.max)
                    bar.progress += 1
            }
        }
        if(v == this.view?.findViewById<Button>(R.id.textViewMinusPurpose)){
            val bar = this.view?.findViewById<SeekBar>(R.id.seekBarPurpose)
            if(bar != null) {
                if(bar.progress != bar.min)
                    bar.progress -= 1
            }
        }
        if(v == this.view?.findViewById<Button>(R.id.textViewPlusPleasure)){
            val bar = this.view?.findViewById<SeekBar>(R.id.seekBarPleasure)
            if(bar != null) {
                if(bar.progress != bar.max)
                    bar.progress += 1
            }
        }
        if(v == this.view?.findViewById<Button>(R.id.textViewMinusPleasure)){
            val bar = this.view?.findViewById<SeekBar>(R.id.seekBarPleasure)
            if(bar != null) {
                if(bar.progress != bar.min)
                    bar.progress -= 1
            }
        }
        if(v == this.view?.findViewById<Button>(R.id.button_save_event)){
            val barPurpose = this.view?.findViewById<SeekBar>(R.id.seekBarPurpose)
            val barPleasure = this.view?.findViewById<SeekBar>(R.id.seekBarPleasure)
            val event = this.view?.findViewById<EditText>(R.id.editTextAddEvent)
            Toast.makeText(this.context,
                "ACTIVITY: " + event?.text + " purpose is: " + barPurpose?.progress + " pleasure is:" + barPleasure?.progress,
                Toast.LENGTH_SHORT).show()

            // Navigate back to the home screen
            findNavController().navigate(R.id.action_addEvent_to_homeScreen)
            }
        }



}
