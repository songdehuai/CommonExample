package com.songdehuai.commonexample.ui.adapter

import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.Myentity
import com.songdehuai.commonlib.utils.kadapter.KTAdapterFactory.KAdapter
import kotlinx.android.synthetic.main.item_test.view.*

var KTAdapterTest = KAdapter<Myentity> {

   layout {
       R.layout.item_test
   }
    bindData { type, vh, data ->
        vh.itemView.item_tv.text = data.str
    }
}

