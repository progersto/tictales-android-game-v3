package loveanddiaries.tictales.com.lovediaries.ui.auth

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import loveanddiaries.tictales.com.lovediaries.R
import loveanddiaries.tictales.com.lovediaries.base.BaseActivity
import loveanddiaries.tictales.com.lovediaries.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class AuthActivity : BaseActivity() {

    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        replaceFragment(AuthFragment.newInstance())

        viewModel.goToMain.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }
}
