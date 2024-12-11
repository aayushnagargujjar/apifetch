package com.example.apifetch

data class Allnews(
    val data: List<Data>,
    val hitsPerPage: Int,
    val page: Int,
    val size: Int,
    val success: Boolean,
    val timeMs: Int,
    val totalHits: Int,
    val totalPages: Int
)