package com.example.shadipoc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.shadipoc.R
import com.example.shadipoc.data.room.UserData
import com.example.shadipoc.databinding.ItemLayoutBinding
import kotlinx.android.synthetic.main.item_layout.view.*

class UserListAdapter(userItemLayout: Int, itemClick: ItemClick) :
    RecyclerView.Adapter<UserListAdapter.CustomView>() {

    private var userItemLayout: Int = 0
    private var userList: List<UserData>? = null
    private var itemClick: ItemClick

    lateinit var itemBinding: ItemLayoutBinding

    init {
        this.userItemLayout = userItemLayout
        this.itemClick = itemClick
    }

    fun setUserList(customers: List<UserData>) {
        userList = customers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {

        itemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                userItemLayout,
                parent,
                false
            )

        return CustomView(itemBinding);
    }

    override fun getItemCount(): Int {
        if (userList == null) {
            return 0
        } else
            return userList!!.size
    }

    override fun onBindViewHolder(holder: CustomView, listPosition: Int) {
        var context = holder.itemView.context

        holder.bind(userList!![listPosition])

        holder.itemView.like.setOnClickListener {
            userList!![listPosition].accepted = 1
            itemClick.onItemClick(userList!![listPosition])
        }

        holder.itemView.dislike.setOnClickListener {
            userList!![listPosition].accepted = 2
            itemClick.onItemClick(userList!![listPosition])
        }

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(50))
            .placeholder(context.getDrawable(R.drawable.placholder))

        Glide.with(context)
            .load(userList!![listPosition].largepicture)
            .apply(requestOptions)
            .into(holder.itemView.profile)

        when (userList!![listPosition].accepted) {
            0 -> {
                //no reaction
                holder.itemView.reacted.visibility = View.GONE

                holder.itemView.like.visibility = View.VISIBLE
                holder.itemView.dislike.visibility = View.VISIBLE
            }
            1 -> {
                //accepted
                holder.itemView.reacted.visibility = View.VISIBLE
                holder.itemView.reaction_text.text = context.getString(R.string.accept)
                holder.itemView.reaction_image.visibility = View.VISIBLE

                holder.itemView.like.visibility = View.GONE
                holder.itemView.dislike.visibility = View.GONE
            }
            2 -> {
                //rejected

                holder.itemView.reacted.visibility = View.VISIBLE
                holder.itemView.reaction_text.text = context.getString(R.string.reject)
                holder.itemView.reaction_image.visibility = View.GONE

                holder.itemView.like.visibility = View.GONE
                holder.itemView.dislike.visibility = View.GONE
            }
        }
    }

    class CustomView(itemPeopleViewBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemPeopleViewBinding.root) {
        private var itemBinding: ItemLayoutBinding = itemPeopleViewBinding

        fun bind(result: UserData) {
            itemBinding.data = result
            itemBinding.executePendingBindings()
        }
    }
}

interface ItemClick {
    fun onItemClick(userData: UserData)
}