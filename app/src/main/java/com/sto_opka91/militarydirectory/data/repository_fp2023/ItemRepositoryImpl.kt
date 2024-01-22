package com.sto_opka91.militarydirectory.data.repository_fp2023


import com.sto_opka91.militarydirectory.DESCRIBE_RESULT_FP2023_LIST
import com.sto_opka91.militarydirectory.GENRE_LIST
import com.sto_opka91.militarydirectory.KAT_LIST
import com.sto_opka91.militarydirectory.LESSON_1_LIST
import com.sto_opka91.militarydirectory.LESSON_2_LIST
import com.sto_opka91.militarydirectory.LESSON_3_LIST
import com.sto_opka91.militarydirectory.LESSON_4_LIST
import com.sto_opka91.militarydirectory.LIST_KURS


class ItemRepositoryImpl :ItemRepository {
    override fun getItemsKat(): List<String> {
        return KAT_LIST
    }
    override fun getItemsLesson1(): List<String> {
        return LESSON_1_LIST
    }

    override fun getItemsLesson2(): List<String> {
        return LESSON_2_LIST
    }

    override fun getItemsLesson3(): List<String> {
        return LESSON_3_LIST
    }

    override fun getItemsLesson4(): List<String> {
        return LESSON_4_LIST
    }

    override fun getItemsGenre(): List<String> {
        return GENRE_LIST
    }

    override fun getItemsDescribeOfResult(): List<String> {
        return DESCRIBE_RESULT_FP2023_LIST
    }

    override fun getKurs(): List<String> {
        return LIST_KURS
    }
}