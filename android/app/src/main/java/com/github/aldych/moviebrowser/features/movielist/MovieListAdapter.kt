package com.github.aldych.moviebrowser.features.movielist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.aldych.moviebrowser.R
import data.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private val context: Context, private val itemClick: (MovieViewModel) -> Unit): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var listData: MutableList<MovieViewModel> = arrayListOf()

    fun refreshAllData(items: List<MovieViewModel>){
        listData.clear()
        listData.addAll(items)

        notifyDataSetChanged()
    }

    fun clearData(){
        listData.clear()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(context, view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listData[position], position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(private val c: Context, v: View, val itemClick: (MovieViewModel) -> Unit) : RecyclerView.ViewHolder(v) {

        fun bindData(model: MovieViewModel, pos: Int) {
            with(model){

                Glide.with(c)
                     .load(model.imgUrl)
                     .apply(RequestOptions()
                             .centerCrop()
                             .placeholder(R.drawable.ic_launcher_background))
                     .into(itemView.ivPoster)

                itemView.tvTitle.text = model.title
                itemView.tvType.text = model.type
                itemView.tvYear.text = model.year

                itemView.setOnClickListener {
                    itemClick(this)
                }
            }
        }
    }
}