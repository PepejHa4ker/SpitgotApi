package com.pepej.spigotApi.api.configuration

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

abstract class Config(
    private val plugin: Plugin,
    fileName: String
): YamlConfiguration() {
    private val fileName: String = fileName + if (fileName.endsWith(".yml")) "" else ".yml"
    private fun createFiles() {
        try {
            val file = File(plugin.dataFolder, fileName)
            if (!file.exists()) {
                if (plugin.getResource(fileName) != null)
                    plugin.saveResource(fileName, false)
                else
                    save(file)

            }
            load(file)
            try { save(file) } catch (ignored: Exception) {}
        } catch (ignored: Exception) {}

    }

    fun save() {
        try { save(File(plugin.dataFolder, fileName)) } catch (ignored: Exception) {}

    }
    inline fun <reified R> withConfig(block: Config.() -> R): R {
        return block()
    }

    init {
        createFiles()
    }

}