package com.mkrdeveloper.weatherappexample.data.pollutionModels

data class PollutionData(
    val coord: Coord,
    val list: List<Pollution>
)