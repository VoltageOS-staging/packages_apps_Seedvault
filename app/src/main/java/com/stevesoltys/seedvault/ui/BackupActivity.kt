/*
 * SPDX-FileCopyrightText: 2020 The Calyx Institute
 * SPDX-License-Identifier: Apache-2.0
 */

package com.stevesoltys.seedvault.ui

import android.view.MenuItem
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.Insets
import androidx.fragment.app.Fragment
import com.stevesoltys.seedvault.R

abstract class BackupActivity : AppCompatActivity() {

    protected fun setupInsets(rootView: View) {
        // Handle window insets for padding adjustments
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, insets ->
            val systemInsets: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                view.paddingLeft,
                systemInsets.top,
                view.paddingRight,
                systemInsets.bottom
            )
            insets
        }
    }

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    protected fun showFragment(f: Fragment, addToBackStack: Boolean = false, tag: String? = null) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, f, tag)
            if (addToBackStack) addToBackStack(null)
            commit()
        }
    }

}
