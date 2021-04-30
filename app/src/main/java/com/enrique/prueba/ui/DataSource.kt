package com.enrique.prueba.ui

import com.enrique.prueba.ui.explorar.ExplorarViewModel


class DataSource{

    companion object{

        fun createDataSet(): ArrayList<ExplorarViewModel>{
            val list = ArrayList<ExplorarViewModel>()
            list.add(
                ExplorarViewModel(
                    "Congratulations!",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png",
                    25.30,
                    3.0,
                    6
                )
            )
            list.add(
                ExplorarViewModel(
                    "Time to Build a Kotlin App!",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/time_to_build_a_kotlin_app.png",
                    30.45,
                    4.5,
                    3
                )
            )

            list.add(
                ExplorarViewModel(
                    "Interviewing a Web Developer and YouTuber",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/coding_for_entrepreneurs.png",
                    10.99,
                    2.0,
                    15
                )
            )
            list.add(
                ExplorarViewModel(
                    "Freelance Android Developer (Vasiliy Zukanov)",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_vasiliy_zukanov.png",
                    15.00,
                    5.0,
                    20
                )
            )
            return list
        }
    }
}