package com.codingblocks.cbonlineapp

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import cn.campusapp.router.Router
import com.codingblocks.cbonlineapp.home.HomeActivity
import com.codingblocks.cbonlineapp.util.extensions.getPrefs
import com.codingblocks.cbonlineapp.util.extensions.otherwise
import org.jetbrains.anko.intentFor

class URLRouterActivity : AppCompatActivity() {

    private fun fallBack() = startActivity(intentFor<HomeActivity>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getPrefs().SP_JWT_TOKEN_KEY != "jwt_token") {
            intent?.data?.let { uri ->

                if (TextUtils.isEmpty(uri.host)) fallBack()
                if (!uri.host!!.contains("online.codingblocks.com")) fallBack()

                val pathSegments = uri.pathSegments
                if (pathSegments.size < 2) fallBack()

                when (pathSegments[0]) {
                    "classroom" -> openRouter(uri)
                    "courses" -> openRouter(uri)
                    "player" -> openRouter(uri)
                    else -> fallBack()
                }
            }

                ?: finish()
        } else {
            fallBack()
        }
    }

    private fun openRouter(uri: Uri) {
        Router.open("activity://courseRun/$uri").otherwise { fallBack() }
        finish()
    }
}
