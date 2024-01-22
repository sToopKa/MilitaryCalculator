package com.sto_opka91.militarydirectory.data.repository_fp2023



interface ItemRepository  {

    fun getItemsKat(): List<String>
    fun getItemsLesson1(): List<String>
    fun getItemsLesson2(): List<String>
    fun getItemsLesson3(): List<String>
    fun getItemsLesson4(): List<String>
    fun getItemsGenre(): List<String>
    fun getItemsDescribeOfResult(): List<String>
    fun getKurs(): List<String>
}