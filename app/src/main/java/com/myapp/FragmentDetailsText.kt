package com.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.jvm.internal.Intrinsics

class FragmentDetailsText : Fragment(R.layout.fragment_details_text) {

    companion object {
        const val ARG_TEXT = "text"

        fun create(text: String?): FragmentDetailsText {
            Intrinsics.checkNotNullParameter(text, "text")
            val fragmentDetailsButton = FragmentDetailsText()
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
        val tv = view!!.findViewById<TextView>(R.id.text)
        tv.text = requireArguments().getString("text")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as MainActivity
            activity.provideType(1)
        }

    override fun onResume() {
        super.onResume()
        val activity = activity as MainActivity
            if (activity.isLandscape()) {
                activity.showBackButton()

            }
        activity.increaseCounter()

    }

    override fun onPause() {
        (activity as MainActivity).decreaseCounter()
        super.onPause()
    }


}