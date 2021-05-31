package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var openFragments: OpenFragments
    private var min: EditText? = null
    private var max: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        openFragments = context as OpenFragments
        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        min = view.findViewById(R.id.min_value) as EditText
        max = view.findViewById(R.id.max_value) as EditText
        generateButton?.setOnClickListener {
            val inMin = min?.text.toString()
            val inMax = max?.text.toString()
            if (inMin.isEmpty() || inMax.isEmpty()) {
                Toast.makeText(context, "Данные не введены", Toast.LENGTH_LONG).show()
            } else if (inMin.toLong() >= Int.MAX_VALUE || inMax.toLong() >= Int.MAX_VALUE) {
                Toast.makeText(context, "Введены слишком большие числа", Toast.LENGTH_LONG).show()
            } else {
                validator(inMin, inMax)
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Tag", "FirstFragment прикреплен")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Tag", "FirstFragment откреплен")
    }
    private fun validator(inMin: String, inMax: String) {
        if (inMin.toInt() > inMax.toInt() ) {
            Toast.makeText(context,"Данные невалидны Мин.значение больше Мах.значения",Toast.LENGTH_LONG).show()
        } else if (inMin.toInt() == 0 && inMax.toInt() == 0) {
            Toast.makeText(context, "Данные не валидны Min.значение = 0 и Max.значение = 0", Toast.LENGTH_LONG).show()
        } else if (inMin.toInt() < 0 || inMax.toInt() < 0) {
            Toast.makeText(context, "Данные не валидны введены отрицательные значения", Toast.LENGTH_LONG).show()
        } else {
            openFragments.openSecondFragment(inMin.toInt(), inMax.toInt())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}