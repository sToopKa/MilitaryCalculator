package com.sto_opka91.militarydirectory.data.repository_vt4

import com.sto_opka91.militarydirectory.DESCRIBE_RESULT_VT4
import com.sto_opka91.militarydirectory.LESSON_1_VT4_LIST
import com.sto_opka91.militarydirectory.LESSON_2_VT4_LIST
import com.sto_opka91.militarydirectory.LESSON_3_VT4_LIST

class ItemVT4repositoryImpl: ItemVT4Repository {
    override fun getItemsLesson1VT4(): List<String> {
        return LESSON_1_VT4_LIST
    }

    override fun getItemsLesson2VT4(): List<String> {
        return LESSON_2_VT4_LIST
    }

    override fun getItemsLesson3VT4(): List<String> {
        return LESSON_3_VT4_LIST
    }

    override fun getItemsDescribeVT4(): List<String> {
        return DESCRIBE_RESULT_VT4
    }
}