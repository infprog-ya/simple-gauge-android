/*******************************************************************************
 * Copyright 2018 Evstafiev Konstantin

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.ekn.gruzer.simplegaugeexample.ui.gauge

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekndev.gaugelibrary.Range
import com.ekndev.gaugelibrary.contract.ValueFormatter

import com.ekn.gruzer.simplegaugeexample.R
import kotlinx.android.synthetic.main.fragment_arc_gauge.*

class ArcGaugeFragment : Fragment() {

    companion object {
        fun newInstance() = ArcGaugeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arc_gauge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val range = Range()
        range.color = Color.parseColor("#ce0000")
        range.from = 0.0
        range.to = 250.0

        val range2 = Range()
        range2.color = Color.parseColor("#E3E500")
        range2.from = 250.0
        range2.to = 500.0

        val range3 = Range()
        range3.color = Color.parseColor("#00b20b")
        range3.from = 500.0
        range3.to = 1000.0

        arcGauge.minValue= 0.0
        arcGauge.maxValue = 1000.0
        arcGauge.value = 0.0


        arcGauge.addRange(range)
        arcGauge.addRange(range2)
        arcGauge.addRange(range3)
        arcGauge.isUseRangeBGColor= true
        arcGauge.valueColor = Color.BLUE
        arcGauge.setGaugeBGWidth(50f)
        arcGauge.setGaugeWidth(50f)
        arcGauge.setPadding(0,16,0,0)
        arcGauge.enableAnimation(true)
        arcGauge.displayValuePoint = true
        arcGauge.setBackgroundColor(Color.TRANSPARENT)
        arcGauge.setFormatter(ValueFormatter {
            it.toInt().toString()
        })


        arcgauge_update_btn.setOnClickListener {
            if (arcgauge_value_ed.text.toString().isNotEmpty()) {
                arcGauge.value = arcgauge_value_ed.text.toString().toDouble()
            }
        }


    }



}
