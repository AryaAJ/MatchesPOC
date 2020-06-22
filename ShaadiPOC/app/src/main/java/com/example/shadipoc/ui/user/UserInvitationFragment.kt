package com.example.shadipoc.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shadipoc.R
import com.example.shadipoc.adapter.ItemClick
import com.example.shadipoc.adapter.UserListAdapter
import com.example.shadipoc.data.room.UserData
import com.example.shadipoc.viewmodel.UserInvitationViewModel
import com.example.shadipoc.viewmodel.UserInvitationViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.second_fragment.*
import javax.inject.Inject

class UserInvitationFragment @Inject constructor() : Fragment(), ItemClick {

    @Inject
    lateinit var viewModelFactory: UserInvitationViewModelFactory
    private var adapter: UserListAdapter = UserListAdapter(R.layout.item_layout, this)

    companion object {
        fun newInstance() = UserInvitationFragment()
    }

    private lateinit var viewModel: UserInvitationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(UserInvitationViewModel::class.java)

        showProgress()

        viewModel.getAllUsers().observe(viewLifecycleOwner, Observer {
            if (it != null && it.size > 0) {
                adapter.setUserList(it)
                hideProgress()
            } else {
                //Toast.makeText(context, "No internet And no data in DB", Toast.LENGTH_SHORT).show()
                hideProgress()
            }
        })
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onItemClick(userData: UserData) {
        viewModel.updateUser(userData)
    }
}
