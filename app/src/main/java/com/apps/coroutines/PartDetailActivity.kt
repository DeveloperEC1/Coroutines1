package com.apps.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_part_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class PartDetailActivity : AppCompatActivity() {

    private var originalItemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part_detail)

        originalItemId = intent.getIntExtra("ItemId", 0)
        tv_item_id.text = originalItemId.toString()
        et_item_name.setText(intent.getStringExtra("ItemName"))

        bt_delete.setOnClickListener {
            deletePart(originalItemId)
        }

        bt_update.setOnClickListener {
            updatePart(
                originalItemId,
                PartData(originalItemId, et_item_name.text.toString())
            )
        }
    }

    private fun deletePart(itemId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val webResponse = WebAccess.partsApi.deletePartAsync(itemId).await()
                Toast.makeText(applicationContext, "Deleted: $itemId: ${webResponse.isSuccessful}", Toast.LENGTH_LONG)
                    .show()
            } catch (e: IOException) {
                Toast.makeText(this@PartDetailActivity, "Exception ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updatePart(originalItemId: Int, newItem: PartData) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val webResponse = WebAccess.partsApi.updatePartAsync(originalItemId, newItem).await()
                Toast.makeText(
                    applicationContext,
                    "Updated: $originalItemId: ${webResponse.isSuccessful}",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: IOException) {
                Toast.makeText(this@PartDetailActivity, "Exception ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

}
