package loveanddiaries.tictales.com.lovediaries.ui.auth


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_auth.*

import loveanddiaries.tictales.com.lovediaries.R
import loveanddiaries.tictales.com.lovediaries.base.BaseFragment
import loveanddiaries.tictales.com.lovediaries.utils.Result
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AuthFragment : BaseFragment() {

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.authProgress.observe(viewLifecycleOwner, Observer {
            when(it?.status){
                Result.ResultStatus.LOADING -> {
                    showLoading()
                    Log.d("ADID", "Status loading")
                }
                Result.ResultStatus.ERROR -> {
                    hideLoading()
                    showMessage("Authentication error")
                    Log.d("ADID", "Status error: ${it.message}")
                }
                Result.ResultStatus.SUCCESS -> {
                    hideLoading()
                    viewModel.onGoToMain()
                    Log.d("ADID", "Status success: ${it.value.toString()}")
                }
            }
        })

        viewModel.payload.observe(viewLifecycleOwner, Observer {
            Log.d("MYUSER", "User token: ${it?.token}, id: ${it?.user?.id}")
        })

        login_button.setOnClickListener {
            viewModel.authenticate()
        }

    }

    companion object {
        const val TAG = "AuthFragment"

        fun newInstance() = AuthFragment()
    }
}
