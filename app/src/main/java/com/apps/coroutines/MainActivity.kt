package com.apps.coroutines

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_parts.layoutManager = LinearLayoutManager(this)
        rv_parts.hasFixedSize()
        adapter = PartAdapter(listOf()) { partItem: PartData -> partItemClicked(partItem) }
        rv_parts.adapter = adapter

        loadPartsAndUpdateList()
    }

    private fun loadPartsAndUpdateList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val webResponse = WebAccess.partsApi.getPartsAsync().await()
                if (webResponse.isSuccessful) {
                    val partList: List<PartData>? = webResponse.body()
                    adapter.partItemList = partList ?: listOf()
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Error ${webResponse.code()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: IOException) {
                Toast.makeText(this@MainActivity, "Exception ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

//    private fun addPart(partItem: PartData) {
//        GlobalScope.launch(Dispatchers.Main) {
//            val webResponse = WebAccess.partsApi.addPartAsync(partItem).await()
//            loadPartsAndUpdateList()
//        }
//    }

    private fun partItemClicked(partItem: PartData) {
        Toast.makeText(this, "Clicked: ${partItem.countryName}", Toast.LENGTH_LONG).show()
        val showDetailActivityIntent = Intent(this, PartDetailActivity::class.java)
        showDetailActivityIntent.putExtra("ItemId", partItem.id)
        showDetailActivityIntent.putExtra("ItemName", partItem.countryName)
        startActivity(showDetailActivityIntent)
    }

}
