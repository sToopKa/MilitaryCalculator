package com.sto_opka91.militarydirectory.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.sto_opka91.militarydirectory.R

class CategoryDialog (context: Context) : AlertDialog(context){
    init {

        val dialogView = layoutInflater.inflate(R.layout.category_dialog_layout, null)
        setView(dialogView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Дополнительные настройки, если необходимо
    }
}