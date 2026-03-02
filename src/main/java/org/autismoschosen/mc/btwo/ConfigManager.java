package org.autismoschosen.mc.btwo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigManager {
	private static final Gson GSON = new GsonBuilder()
			.setPrettyPrinting()
			.disableHtmlEscaping()
			.create();

	public static BeaconBoostConfig CONFIG = new BeaconBoostConfig();

	public static void load() {
		Path path = FabricLoader.getInstance().getConfigDir().resolve("beaconboost.json");

		if (Files.exists(path)) {
			try (Reader reader = Files.newBufferedReader(path)) {
				BeaconBoostConfig loaded = GSON.fromJson(reader, BeaconBoostConfig.class);
				if (loaded != null) {
					CONFIG = loaded;
				}
			} catch (IOException e) {
				BeaconBoost.LOGGER.error("Failed to load beaconboost config, using defaults", e);
			}
		} else {
			save(path); // write defaults
		}
	}

	private static void save(Path path) {
		try {
			Files.createDirectories(path.getParent());
			try (Writer writer = Files.newBufferedWriter(path)) {
				GSON.toJson(CONFIG, writer);
			}
		} catch (IOException e) {
			BeaconBoost.LOGGER.error("Failed to save beaconboost default config", e);
		}
	}

	public static void save() {
	    Path path = FabricLoader.getInstance().getConfigDir().resolve("beaconboost.json");
	    try {
		Files.createDirectories(path.getParent());
		try (Writer writer = Files.newBufferedWriter(path)) {
		    GSON.toJson(CONFIG, writer);
		}
	    } catch (IOException e) {
		BeaconBoost.LOGGER.error("Failed to save beaconboost config", e);
	    }
	}
}
