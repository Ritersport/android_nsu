package com.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class FragmentMenu : Fragment(R.layout.fragment_menu) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val activity = activity as MainActivity
        view!!.findViewById<Button>(R.id.button_1)
            .setOnClickListener {
                activity.provideText("111111")
                activity.showButtonFragment("111111")
            }

        view.findViewById<Button>(R.id.button_2)
            .setOnClickListener {
                activity.provideText("222222")

                activity.showButtonFragment("222222")
            }
        view.findViewById<View>(R.id.button_3)
            .setOnClickListener {
                activity.provideText("333333")

                activity.showButtonFragment("333333")
            }
        view.findViewById<View>(R.id.back)
            .setOnClickListener {
                activity.backToButton()
                if (activity.isLandscape()) {
                    hideBackButton()
                }
            }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as MainActivity
        if (activity.isLandscape()) {
            hideBackButton()
        }
    }


    fun showBackButton() {
        (requireView().findViewById<View>(R.id.back) as Button).visibility = View.VISIBLE
    }

    private fun hideBackButton() {
        (requireView().findViewById<View>(R.id.back) as Button).visibility = View.INVISIBLE
    }

    override fun onResume() {
        (activity as MainActivity).increaseCounter()
        super.onResume()
    }

    override fun onPause() {
        (activity as MainActivity).decreaseCounter()
        super.onPause()
    }
}