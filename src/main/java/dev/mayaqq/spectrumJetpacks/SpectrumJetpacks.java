package dev.mayaqq.spectrumJetpacks;

import dev.mayaqq.spectrumJetpacks.config.SpectrumJetpacksConfig;
import dev.mayaqq.spectrumJetpacks.networking.C2SPackets;
import dev.mayaqq.spectrumJetpacks.registry.BlockEntityRegistry;
import dev.mayaqq.spectrumJetpacks.registry.BlockRegistry;
import dev.mayaqq.spectrumJetpacks.registry.ItemRegistry;
import dev.mayaqq.spectrumJetpacks.registry.ServerEventRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectrumJetpacks implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("spectrumJetpacks");
	public static Identifier id(String path) {
		return new Identifier("spectrumjetpacks", path);
	}
	public static SpectrumJetpacksConfig CONFIG;

	//register items

	@Override
	public void onInitialize() {
		LOGGER.info("To the sky!");
		AutoConfig.register(SpectrumJetpacksConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(SpectrumJetpacksConfig.class).getConfig();
		ItemRegistry.register();
		BlockRegistry.register();
		BlockEntityRegistry.register();
		C2SPackets.register();
		ServerEventRegistry.register();
	}
}