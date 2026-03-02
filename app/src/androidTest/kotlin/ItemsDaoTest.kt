import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
class ItemsDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var database: InventoryDatabase
    private var item1 = Item(
        id = 1,
        name = "Apple",
        price = 19.0,
        quantity = 5
    )
    private var item2 = Item(
        id = 2,
        name = "Banana",
        price = 9.0,
        quantity = 10
    )

    suspend fun insertItemIntoDB(){
        itemDao.AddItem(item1)
        itemDao.AddItem(item2)
    }
    @Before
    /**
     *create an in MemoryDatabase just for testing
     * (inMemoryDatabaseBuilder is cleared after process is killed and is not persisted)
     *
     */
    fun createDb(){
        val context: Context = ApplicationProvider.getApplicationContext()
        database  = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = database.itemDao()
    }

    @After
    fun closeDB(){
        /**
         *  close the Database after Test is done running
         */
        database.close()
    }


    //Tests for DB
    @Test
    fun itemDao_insertsItemToDB() = runBlocking{
       insertItemIntoDB()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
    }

    @Test
    fun daoGetAllItems_getsAllItemsFromDB() = runBlocking{
        insertItemIntoDB()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }

}