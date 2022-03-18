package com.humeyramercan.kotlininstagramclone.view

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.humeyramercan.kotlininstagramclone.R
import com.humeyramercan.kotlininstagramclone.adapter.FeedAdapter
import com.humeyramercan.kotlininstagramclone.databinding.ActivityFeedBinding
import com.humeyramercan.kotlininstagramclone.model.Post

class FeedActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFeedBinding

    private lateinit var postList:ArrayList<Post>
    private lateinit var feedAdapter:FeedAdapter

    private lateinit var auth:FirebaseAuth
    private lateinit var fireStore:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFeedBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //Firebase
        auth=Firebase.auth
        fireStore=Firebase.firestore
        postList= ArrayList<Post>()
        getData()
        //RecyclerView

        binding.recylerView.layoutManager=LinearLayoutManager(this)
        feedAdapter= FeedAdapter(postList)
        binding.recylerView.adapter=feedAdapter



    }
    private fun getData(){

        fireStore.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener { value, error ->

            if(error!=null){
                Toast.makeText(this@FeedActivity,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else if(value!=null && !value.isEmpty){
                val documents=value.documents
                postList.clear()
                for(document in documents){

                    val email=document.get("userEmail") as String
                    val explanation=document.get("explanation") as String
                    val date=document.getDate("date")
                    val downloadUrl=document.get("downloadUrl") as String
                    
                    val day = DateFormat.format("dd", date) as String // 20
                    val monthString = DateFormat.format("MMM", date) as String // Jun
                    val year = DateFormat.format("yyyy", date) as String // 2013

                    val fullDate=day+" "+monthString+" "+year

                    val post=Post(email,explanation,downloadUrl,fullDate)
                    println("test")
                    postList.add(post)

                }
                feedAdapter.notifyDataSetChanged()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.insta_menu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.addPost){

            val intent= Intent(this,UploadActivity::class.java)
            startActivity(intent)

        }else if(item.itemId==R.id.signOut){
            auth.signOut()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}