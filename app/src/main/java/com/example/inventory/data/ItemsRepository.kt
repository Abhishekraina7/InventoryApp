package com.example.inventory.data

import kotlinx.coroutines.flow.Flow
/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface ItemsRepository{
    fun getAllItemsStream(): Flow<List<Item>>

    fun getItemStream(id: Int): Flow<Item>

    suspend fun insertItem(item: Item)

    suspend fun update(item: Item)

    suspend fun delete(item: Item)
}
