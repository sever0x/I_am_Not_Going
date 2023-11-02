package com.shdwraze.notgoing.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shdwraze.notgoing.data.converter.Converter
import com.shdwraze.notgoing.data.dao.LessonDao
import com.shdwraze.notgoing.data.dao.ScheduleDao
import com.shdwraze.notgoing.data.entity.Lesson
import com.shdwraze.notgoing.data.entity.Schedule

@Database(entities = [Schedule::class, Lesson::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    abstract fun lessonDao(): LessonDao

    companion object {
        @Volatile
        private var Instance: ReminderDatabase? = null

        fun getDatabase(context: Context): ReminderDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ReminderDatabase::class.java, "reminderDb")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}