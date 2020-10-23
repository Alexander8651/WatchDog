package com.andromoticaia.watchingdog.arquitecturepatern

import android.app.AlertDialog
import android.content.Context
import com.andromoticaia.watchingdog.data.DataSource

class MessageFactory {

    companion object{
        val TYPE_DONT_NICKNAME_WRITTEN = "typedontnicknamewritten"
        val TYPE_DELETE_DOG_FROMFAVORITES = "typedeletedogfromfavorites"
    }

    fun getDialog(context: Context, type:String):AlertDialog.Builder{

        when(type){

            TYPE_DONT_NICKNAME_WRITTEN -> {
                return AlertDialog.Builder(context)
                    .setMessage("Before to add a favorites, add a nickname")
                    .setPositiveButton("Accept"){dialog, which ->
                    }
            }

            TYPE_DELETE_DOG_FROMFAVORITES ->{
                return AlertDialog.Builder(context)

            }

        }

        return AlertDialog.Builder(context)
    }
}