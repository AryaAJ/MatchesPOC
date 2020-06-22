package com.example.shadipoc.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shadipoc.R
import com.example.shadipoc.ui.user.UserInvitationFragment
import com.example.shadipoc.utils.replaceFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userInvitationFragment: UserInvitationFragment

    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        context = this

        replaceFragment(
            userInvitationFragment,
            false,
            "dashboardFragment",
            R.id.container,
            null
        )
    }
}