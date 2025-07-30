package com.wildtraff.gatesofolympus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wildtraff.gatesofolympus.data.dao.GameLevelDao
import com.wildtraff.gatesofolympus.data.entities.GameLevel
import com.wildtraff.gatesofolympus.data.typeconverter.ListConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [GameLevel::class], version = 2)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameLevelDao(): GameLevelDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "gates_of_olympus_db"
            ).fallbackToDestructiveMigration(false)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            val dao = buildDatabase(context).gameLevelDao()
                            dao.insertAll(GameSeedData.generateInitialData())
                        }
                    }
                })
                .build()
        }
    }
}
