package com.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlin.jvm.internal.Intrinsics

class FragmentDetailsButton : Fragment(R.layout.fragment_details_button) {

    companion object {
        const val ARG_TEXT = "text"

        var containerId = 0


        fun create(text: String?, isLandscape: Boolean): FragmentDetailsButton {
            Intrinsics.checkNotNullParameter(text, "text")
            val fragmentDetailsButton = FragmentDetailsButton()
            if (isLandscape) {
                this.containerId = R.id.container_item
            } else {
                this.containerId = R.id.container_list
            }
            val args = Bundle()
            args.putString("text", text)
            fragmentDetailsButton.arguments = args
            return fragmentDetailsButton
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val button = view!!.findViewById<Button>(R.id.button)
        val text = requireArguments().getString("text")
        button.text = text
        button.setOnClickListener {
            onClick(text)
        }
        val activity = requireActivity() as MainActivity
        activity.provideText(text)
        activity.provideType(0)
        return view
    }


    override fun onResume() {
        (activity as MainActivity).increaseCounter()
        super.onResume()
    }


    override fun onPause() {
        (activity as MainActivity).decreaseCounter()
        super.onPause()
    }

    private fun onClick(text: String?) {
        val beginTransaction = parentFragmentManager.beginTransaction()
        beginTransaction.replace(containerId, FragmentDetailsText.create(text))
            .addToBackStack("BLA").commit()
        if ((activity as MainActivity).isLandscape()) {
            (activity as MainActivity).showBackButton()
        }
    }
}
