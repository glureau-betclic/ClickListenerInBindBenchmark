package com.glureau.clicklistenerinbindbenchmark

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glureau.clicklistenerinbindbenchmark.databinding.ItemBinding
import kotlin.random.Random

data class ImMessage(val content: String = "msg", val sentByMe: Boolean = Random.nextBoolean())

class MessagesAdapter : ListAdapter<ImMessage, MessagesAdapter.MessageViewHolder>(
    diffUtil
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemBinding =
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MessageViewHolder(private val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val color1 = itemView.context.getColor(R.color.purple_200)
        val color2 = itemView.context.getColor(R.color.teal_200)

        fun bind(message: ImMessage) {
            itemBinding.message.text = message.content
            if (message.sentByMe) {
                itemBinding.message.gravity = Gravity.END
                //itemBinding.message.textAlignment = View.TEXT_ALIGNMENT_CENTER
                itemBinding.root.setCardBackgroundColor(color1)
            } else {
                itemBinding.root.setCardBackgroundColor(color2)
                itemBinding.message.gravity = Gravity.START
                //itemBinding.message.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ImMessage>() {
            override fun areItemsTheSame(
                oldItem: ImMessage,
                newItem: ImMessage
            ) = oldItem === newItem

            override fun areContentsTheSame(
                oldItem: ImMessage,
                newItem: ImMessage
            ) = oldItem.content == newItem.content
        }
    }
}