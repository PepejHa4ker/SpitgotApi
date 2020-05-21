package com.pepej.spigotApi.api.util.extensions

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.HoverEvent
import org.bukkit.ChatColor
import org.bukkit.entity.Player


fun String.translateColor(code: Char = '&'): String = ChatColor.translateAlternateColorCodes(code, this)
fun Player.sendMessage(text: BaseComponent) = spigot().sendMessage(text)
operator fun String.unaryPlus(): String = translateColor()
inline fun <reified T : BaseComponent> T.hover(hoverEvent: HoverEvent) = apply { this.hoverEvent = hoverEvent }
inline fun <reified T : BaseComponent> T.showText(component: BaseComponent) = hover(HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(component)))