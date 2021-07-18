package com.assignment.meteoriteapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.meteoriteapp.BR
import com.assignment.meteoriteapp.controllers.OnMeteorClickListener
import com.assignment.meteoriteapp.testData.Meteor
import com.assignment.meteoriteapp.databinding.ItemviewMeteorBinding

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MeteorAdapter(
    private val onMeteorClickListener: OnMeteorClickListener,
    private val isFromFavoriteTab: Boolean
) :
    RecyclerView.Adapter<MeteorAdapter.MeteorViewHolder>() {

    private var meteorList: ArrayList<Meteor> = ArrayList()

    class MeteorViewHolder(private val binding: ItemviewMeteorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Meteor, listener: OnMeteorClickListener, isNotFromFavoriteTab: Boolean) {
            binding.setVariable(BR.meteor, obj)
            binding.setVariable(BR.listener, listener)
            binding.setVariable(BR.isNormalMeteorFragment, isNotFromFavoriteTab)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeteorViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val meteorBinding: ItemviewMeteorBinding =
            ItemviewMeteorBinding.inflate(inflater, parent, false)
        return MeteorViewHolder(meteorBinding)
    }

    override fun onBindViewHolder(holder: MeteorViewHolder, position: Int) {
        val item = differ.currentList[position]
        item.position = position
        holder.bind(item, onMeteorClickListener, !isFromFavoriteTab)
    }

    override fun getItemCount() = differ.currentList.size

    private val METEOR_DIFF_CALLBACK: DiffUtil.ItemCallback<Meteor> =
        object : DiffUtil.ItemCallback<Meteor>() {
            override fun areItemsTheSame(
                @NonNull oldMeteor: Meteor,
                @NonNull newMeteor: Meteor
            ): Boolean {
                return oldMeteor.name == newMeteor.name
            }

            override fun areContentsTheSame(
                @NonNull oldMeteor: Meteor,
                @NonNull newMeteor: Meteor
            ): Boolean {
                return oldMeteor.name == newMeteor.name
            }
        }

    private val differ: AsyncListDiffer<Meteor> =
        AsyncListDiffer<Meteor>(this, METEOR_DIFF_CALLBACK)

    fun setMeteorList(meteors: ArrayList<Meteor>) {
        meteorList = meteors
        differ.submitList(meteors)
    }
}