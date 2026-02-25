package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override fun getAllItemsStream(): Flow<List<Item>> {
        return itemDao.getAllItems()
    }

    override fun getItemStream(id: Int): Flow<Item> {
        return itemDao.getItem(id)
    }

    override suspend fun insertItem(item: Item) {
        return itemDao.AddItem(item)
    }

    override suspend fun update(item: Item) {
        return itemDao.UpdateItem(item)
    }

    override suspend fun delete(item: Item) {
        return itemDao.DeleteItem(item)
    }
}
