/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

/**SleepNightAdapter.ViewHolder will make Adpater use the ViewHolder created**/
class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
    var data = listOf<SleepNight>() /**data that holds the list of nights**/

    /**Let the RecyclerView know when data changes**/
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    /**Create and inflate a view for data
     * list_item_sleep_night is the layout created for the ViewHolder**/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
        return ViewHolder(view)
    }

    /**Called by RecyclerView to display Item at a specified position and update the list**/
    /**It will be called when there is scrolling item or item on the screen**/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        holder.quality.text = convertNumericQualityToString(item.sleepQuality, res)

        holder.qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })

    }

    /**Return the total number of Items**/
    override fun getItemCount() = data.size

    /**Creating a ViewHolder class that will hold the views the recyclerView will use
     * It has its own layout, so it gets reference to these views**/
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val sleepLength : TextView = itemView.findViewById(R.id.sleep_length)
        val quality : TextView = itemView.findViewById(R.id.sleep_quality)
        val qualityImage : ImageView = itemView.findViewById(R.id.quality_image)
    }
}