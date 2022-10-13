package com.tongji.yanluapp.app.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tongji.yanluapp.bean.School

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/24 18:19
 * @description:
 * @email: tongji0x208@gmail.com
 */

@Database(version = 1, entities = [School::class])
abstract class LikeSchoolDatabase : RoomDatabase() {

    abstract fun likeSchoolDao() : LikeSchoolDao

    companion object {
        private var instance: LikeSchoolDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): LikeSchoolDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                LikeSchoolDatabase::class.java, "school.db")
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }
    }
}