package com.aleksandrov.lecture14

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mCount = 0
    private var mIsAdd: Boolean? = null
    private var mIsAddToBackStack = false

    private lateinit var mAddBtn: Button
    private lateinit var mSubtractBtn: Button
    private lateinit var mRadioGroup: RadioGroup
    private lateinit var mAddRBtn: RadioButton
    private lateinit var mReplaceRBtn: RadioButton
    private lateinit var mAddToBackstackCbx: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, MyFragment.newInstance(mCount))
            .commit()

        mAddBtn = findViewById(R.id.add_btn)
        mAddBtn.setOnClickListener { addFragment() }

        mSubtractBtn = findViewById(R.id.subtract_btn)
        mSubtractBtn.setOnClickListener { subtractFragment() }

        mAddRBtn = findViewById(R.id.add_r_btn)
        mAddRBtn.setOnClickListener {
            mIsAdd = true
        }
        mReplaceRBtn = findViewById(R.id.replace_r_btn)
        mReplaceRBtn.setOnClickListener {
            mIsAdd = false
        }
        mAddToBackstackCbx = findViewById(R.id.add_to_backstack_cbx)
        mAddToBackstackCbx.setOnCheckedChangeListener { _, isChecked ->
            mIsAddToBackStack = isChecked
        }
    }

    private fun addFragment() {
        mIsAdd?.also { isAdd ->
            if (isAdd) {
                if (mIsAddToBackStack) mCount++
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, MyFragment.newInstance(mCount))
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, MyFragment.newInstance(mCount))
            }.also {
                if (mIsAddToBackStack) {
                    it.addToBackStack("fragment $mCount")
                }
                it.commit()
            }
        }
    }

    private fun subtractFragment() {
        if (mCount > 0) {
            supportFragmentManager.popBackStack()
            mCount--
        }
    }

}