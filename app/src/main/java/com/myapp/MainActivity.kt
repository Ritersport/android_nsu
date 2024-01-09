package com.myapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.Arrays
import java.util.concurrent.atomic.AtomicInteger


class MainActivity : AppCompatActivity() {
    private var counter = AtomicInteger(0)
    private var landscape = 0
    private var orientation = 0
    private var text: String? = null
    private var type = 0

    fun isLandscape() = (orientation == landscape)


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        orientation = resources.configuration.orientation
        landscape = 2
        var str: String? = "111111"

        if (savedInstanceState == null) {
            val fragmentMenu = FragmentMenu()

            if (orientation == landscape) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container_list,
                    fragmentMenu, FRAGMENT_MENU
                ).commit()
                showButtonFragment(str)
            } else {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container_list,
                    fragmentMenu, FRAGMENT_MENU
                ).addToBackStack("BLA").commit()
            }

        } else {
            val findFragmentByTag =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MENU)
            if (findFragmentByTag != null) {
                supportFragmentManager.beginTransaction().remove(findFragmentByTag)
                    .commit()
            }
            val newFragment = FragmentMenu()
            val text2 = savedInstanceState.getString("text")
            val type2 = savedInstanceState.getInt("type")
            if (orientation == landscape) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container_list,
                    (newFragment as Fragment), FRAGMENT_MENU
                ).commit()
                showButtonFragment(text2 ?: str)
                if (type2 != 0) {
                    if (text2 != null) {
                        str = text2
                    }
                    showTextFragment(str)
                    return
                }
                return
            } else {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container_list,
                    (newFragment as Fragment), FRAGMENT_MENU
                ).addToBackStack("BLA").commit()

                if (text2 != null) {
                    showButtonFragment(text2)
                    if (type2 != 0) {
                        showTextFragment(text2)

                    }

                }
            }
        }
    }

    fun increaseCounter() {
        counter.incrementAndGet()
        val format = String.format(
            "counter: %s", *Arrays.copyOf(
                arrayOf<Any>(
                    counter
                ), 1
            )
        )
        (findViewById<View>(R.id.counter_tv) as TextView).text = format
    }

    fun decreaseCounter() {
        counter.decrementAndGet()
        val format = String.format(
            "counter: %s", *Arrays.copyOf(
                arrayOf<Any>(
                    counter
                ), 1
            )
        )
        (findViewById<View>(R.id.counter_tv) as TextView).text = format
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("text", text)
        outState.putInt("type", type)
        super.onSaveInstanceState(outState)
    }

    fun backToButton() {
        if (orientation == landscape) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun showButtonFragment(text2: String?) {
        var z = true
        if (orientation == landscape) {
            supportFragmentManager.popBackStack("BLA", 1)
        }
        val containerId: Int = if (orientation == landscape) {
            R.id.container_item
        } else {
            R.id.container_list
        }
        val old = supportFragmentManager.findFragmentByTag(FRAGMENT_BUTTON)
        if (old != null) {
            supportFragmentManager.beginTransaction().remove(old).commit()
        }
        if (orientation != landscape) {
            z = false
        }
        supportFragmentManager.beginTransaction().replace(
            containerId,
            (FragmentDetailsButton.create(text2, z) as Fragment), FRAGMENT_BUTTON
        ).addToBackStack("BLA").commit()
    }

    private fun showTextFragment(text2: String?) {
        val containerId: Int = if (orientation == landscape) {
            R.id.container_item
        } else {
            R.id.container_list
        }
        val old = supportFragmentManager.findFragmentByTag(FRAGMENT_TEXT)
        if (old != null) {
            supportFragmentManager.beginTransaction().remove(old).commit()
        }
        supportFragmentManager.beginTransaction()
            .replace(containerId, FragmentDetailsText.create(text2), FRAGMENT_TEXT)
            .addToBackStack("BLA")
            .commit()
    }

    fun showBackButton() {
        val findFragmentById = supportFragmentManager.findFragmentById(R.id.container_list)
        (findFragmentById as FragmentMenu).showBackButton()

    }

    fun provideText(text2: String?) {
        text = text2
    }

    fun provideType(type2: Int) {
        type = type2
    }

    companion object {
        private const val FRAGMENT_BUTTON = "fragmentButton"
        private const val FRAGMENT_MENU = "fragmentMenu"
        private const val FRAGMENT_TEXT = "fragmentText"

    }
}