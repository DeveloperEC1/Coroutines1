package com.elior.coroutines.ClassesPackage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.elior.coroutines.AdapterPackage.PartAdapter
import com.elior.coroutines.DataPackage.JSONResponse
import com.elior.coroutines.DataPackage.MovieModel
import com.elior.coroutines.R
import com.elior.coroutines.RetrofitPackage.WebAccess
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadParts()
    }

    private fun loadParts() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val webResponse = WebAccess.partsApi.getPartsAsync(
                    "/3/search/movie?/&query=q&api_key=" + getString(R.string.api_key)
                ).await()
                if (webResponse.isSuccessful) {
                    val partList: JSONResponse? = webResponse.body()
                    partList?.results?.toList()?.let { initRecyclerView(it) }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error ${webResponse.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: IOException) {
                Toast.makeText(this@MainActivity, "Exception ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun initRecyclerView(dataList: List<MovieModel>) {
        rv_parts.adapter = PartAdapter(dataList)
        rv_parts.layoutManager = LinearLayoutManager(this)
    }

}
