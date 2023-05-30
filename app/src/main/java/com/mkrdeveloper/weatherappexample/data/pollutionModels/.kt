package com.mkrdeveloper.weatherappexample.data.pollutionModels

data class Pollution(
    val components: Components,
    val dt: Int,
    val main: Main
)