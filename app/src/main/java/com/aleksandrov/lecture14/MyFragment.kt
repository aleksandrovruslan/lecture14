package com.aleksandrov.lecture14

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val COUNT_PARAM = "COUNT_PARAM"

class MyFragment : Fragment() {

    private var mCount: Int = 0
    private lateinit var mCountTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mCount = it.getInt(COUNT_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCountTv = view.findViewById(R.id.count_tv)
        mCountTv.text = mCount.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(count: Int) =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putInt(COUNT_PARAM, count)
                }
            }
    }

}