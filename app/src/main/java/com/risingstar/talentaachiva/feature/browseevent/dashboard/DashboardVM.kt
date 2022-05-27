package com.risingstar.talentaachiva.feature.browseevent.dashboard

import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.repository.MainRepository

class DashboardVM(val repository: MainRepository) {

    val user = repository.getIdentity()
    val getAllEvents = repository.getEvents()
    val activeEventAsParticipant = repository.getActiveEvents("participants")
    val activeEventAsOrganizer = repository.getActiveEvents("organizers")

    fun searchEvent(search:String): List<Event>? {
        return repository.getEventSearch(stringToWords(search))
    }

    fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()

}