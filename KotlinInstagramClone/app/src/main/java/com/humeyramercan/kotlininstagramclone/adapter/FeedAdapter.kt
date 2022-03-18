package com.humeyramercan.kotlininstagramclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.humeyramercan.kotlininstagramclone.databinding.RecyclerRowBinding
import com.humeyramercan.kotlininstagramclone.model.Post
import com.squareup.picasso.Picasso

class FeedAdapter(val postList:List<Post>):RecyclerView.Adapter<FeedAdapter.PostHolder>() {
    class PostHolder(val binding:RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
       val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.recyclerDateText.text=postList.get(position).date.toString()
        holder.binding.recyclerEMailText.text=postList.get(position).email
        holder.binding.reyclerExplanationText.text=postList.get(position).explanation
        Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.recyclerImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}