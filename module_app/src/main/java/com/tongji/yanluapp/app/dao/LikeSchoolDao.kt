package com.tongji.yanluapp.app.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tongji.yanluapp.bean.School

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/24 18:18
 * @description:
 * @email: tongji0x208@gmail.com
 */

@Dao
interface LikeSchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchool(school: School)

    @Query("select * from School")
    fun loadAllSchools(): LiveData<List<School>>

}