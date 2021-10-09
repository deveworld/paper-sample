package com.github.mcsim415.sample

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin(), Listener {
    // This is sample code, Please remove before use.

    override fun onEnable() {
        logger.info("Plugin has been loaded!")
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        logger.info("Plugin has been disabled!")
    }

}