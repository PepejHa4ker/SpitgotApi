package com.pepej.spigotApi.api.util.extensions

inline fun <reified T> Collection<T>.findOrNull(predicate: (T) -> Boolean): List<T?>? {
    val toReturn: MutableList<T?> = mutableListOf()
    for (element in this)  {
        if (predicate(element)) toReturn.add(element)
        return toReturn
    }
    return null

}
