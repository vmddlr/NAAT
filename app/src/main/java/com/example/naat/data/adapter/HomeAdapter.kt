package com.example.naat.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naat.R
import com.example.naat.data.model.entity.CompanyListEntity
import com.example.naat.databinding.ItemCompanyRecyclerviewLayoutBinding
import javax.inject.Inject

class HomeAdapter @Inject constructor(
    private var list: List<CompanyListEntity>,
    private val listener: IHomeListener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var context: Context
    var showIsVisible = 0
    var titleIsVisible = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context

        val binding = ItemCompanyRecyclerviewLayoutBinding.inflate(
            LayoutInflater.from(this.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = list[position]


        with(holder) {
            if (position > 0) {
                if (position % 2 == 0 || titleIsVisible < 2) {
                    binding.tvTitle.visibility = View.INVISIBLE
                    titleIsVisible++
                } else {
                    titleIsVisible = 0
                }
            }

            if (position % 3 == 0 || showIsVisible < 2) {
                binding.tvShow.visibility = View.INVISIBLE
                showIsVisible++
            } else {
                showIsVisible = 0
            }

            if (entity.description == context.getString(R.string.str_cellphone_minutes)) {
                binding.ibCompany.setOnClickListener {
                    listener.onClickRechargeView()
                }
            }

            binding.tvTitle.text = entity.title
            binding.ibCompany.setImageResource(entity.image)
            binding.tvDescription.text = entity.description
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemCompanyRecyclerviewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}

