package com.example.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase


@Entity(tableName = "menu_table")
data class Menu(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String,
)

@Dao
interface MenuDao {
    @Insert
    suspend fun insert(menu: Menu)

    @Update
    suspend fun update(menu: Menu)

    @Delete
    suspend fun delete(menu: Menu)

    @Query("delete from menu_table")
    fun deleteAllMenu()

    @Query("select * from menu_table")
    fun getAllMenu(): LiveData<List<Menu>>
}

@Database(entities = [Menu::class], version = 1)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        private var instance: MenuDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): MenuDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    MenuDatabase::class.java,
                    "menu_database"
                ).build()

            return instance!! as MenuDatabase
        }


    }


}


