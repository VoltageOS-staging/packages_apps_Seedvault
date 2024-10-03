/*
 * SPDX-FileCopyrightText: 2021 The Calyx Institute
 * SPDX-License-Identifier: Apache-2.0
 */

package de.grobox.storagebackuptester

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.Insets
import de.grobox.storagebackuptester.crypto.KeyManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle window insets for padding adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { view, insets ->
            val systemInsets: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                view.paddingLeft,
                systemInsets.top,
                view.paddingRight,
                systemInsets.bottom
            )
            insets
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LogFragment.newInstance())
                .commitNow()
        }

        KeyManager.storeMasterKey()

        if (!KeyManager.hasMasterKey()) {
            Log.e("TEST", "storing new key")
            KeyManager.storeMasterKey()
        } else {
            Log.e("TEST", "already have key")
        }

    }

}
