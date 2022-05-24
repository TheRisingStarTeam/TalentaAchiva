package com.risingstar.talentaachiva.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity

interface ParticipantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBio(identity: Identity)

    @Query("SELECT event.eventId FROM event, identity WHERE event.eventId = ")
    fun getOngoingEvent(): PagingSource<Int, Event>

    @Query("DELETE FROM story")
    suspend fun deleteAll()
}