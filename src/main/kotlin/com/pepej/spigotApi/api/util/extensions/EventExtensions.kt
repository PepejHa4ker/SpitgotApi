package com.pepej.spigotApi.api.util.extensions

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

inline fun <reified T : Event> KListener<*>.event(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = true,
    crossinline block: T.() -> Unit
) = event(plugin, priority, ignoreCancelled, block)
inline fun <reified T : Event> Listener.event(
    plugin: Plugin,
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = true,
    crossinline block: T.() -> Unit
) {
    Bukkit.getServer().pluginManager.registerEvent(
        T::class.java,
        this,
        priority,
        {_, e ->
            (e as? T)?.block()
        },
        plugin,
        ignoreCancelled
    )
}

inline fun Plugin.events(block: KListener<*>.() -> Unit) = InlineKListener(
    this
).apply(block)
interface KListener<T : Plugin> : Listener { val plugin: T }
inline class InlineKListener(override val plugin: Plugin) :
    KListener<Plugin>