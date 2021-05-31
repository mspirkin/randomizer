package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private lateinit var label: TextView
    private lateinit var openFragments: OpenFragments

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)
        label = view.findViewById(R.id.result_label)
        openFragments = context as OpenFragments

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()
        label.text = "Result:${result?.text}"

        backButton?.setOnClickListener {
            openFragments.deleteFragmentStack()
            openFragments.openFirstFragment(result?.text.toString().toInt())
        }

    }


    private fun generate(min: Int, max: Int): Int {
        return (min..max).random()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Tag", "SecondFragment прикреплен")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Tag", "SecondFragment откреплен")
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}