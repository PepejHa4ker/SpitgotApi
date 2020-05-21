package com.pepej.spigotApi.api.util.extensions

import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.ArrayList

inline fun <reified T : ItemMeta> ItemStack.meta(block: T.() -> Unit): ItemStack = apply { itemMeta = (itemMeta as? T)?.apply(block) ?: itemMeta }
inline fun item(material: Material, amount: Int = 1, data: Short = 0, meta: ItemMeta.() -> Unit = {}): ItemStack = ItemStack(material, amount, data).meta(meta)
fun Inventory.add(items: ArrayList<ItemStack>) = items.forEach { this.addItem(it) }