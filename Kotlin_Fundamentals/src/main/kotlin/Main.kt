

enum class DayPart {MORNING, EVENING, NIGHT}
data class Event(val title: String, val description: String? = null, val dayPart: DayPart, val duration: Int, )


fun main(args: Array<String>) {

    val event = Event("Study Kotlin", null, DayPart.MORNING, 12)
    val event2 = Event("Study Kotlin2", null, DayPart.EVENING, 132)
    val event3 = Event("Study Kotlin3", null, DayPart.EVENING, 12)
    val event4 = Event("Study Kotlin4", null, DayPart.NIGHT, 43)
    val event5 = Event("Study Kotlin5", null, DayPart.EVENING, 10)
    val event6 = Event("Study Kotlin6", null, DayPart.EVENING, 66)

    val eventList = mutableListOf(event, event2, event3, event4, event5, event6)
    print(eventList)

    val shortEvents = eventList.filter { it.duration < 50 }
    println(shortEvents)
    println("you have ${shortEvents.size}")

    val eventGroups = eventList.groupBy { it.dayPart }
    eventGroups.forEach { (dayPart, events) -> println("$dayPart: ${events.size} events") }

    val title = eventList.last().title
    println(title)

    val Event. = String
    get() = if(this.duration < 60) {
        "Short"
    } else {
        "Long"
    }


}