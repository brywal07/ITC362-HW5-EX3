package com.brywal07.criminalintentbw

import java.util.*

data class Crime(val id: UUID, val title: String, val date: Date, val isSolved: Boolean, val requiresPolice: Boolean) {

}