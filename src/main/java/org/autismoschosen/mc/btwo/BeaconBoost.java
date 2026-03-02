package org.autismoschosen.mc.btwo;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeaconBoost implements ModInitializer {
	public static final String MOD_ID = "beaconboost";

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigManager.load();
		LOGGER.info("BeaconBoost initialized with config: groupSize={}, maxRange={}",
				ConfigManager.CONFIG.groupSize,
				ConfigManager.CONFIG.maxRange);
	}
}
