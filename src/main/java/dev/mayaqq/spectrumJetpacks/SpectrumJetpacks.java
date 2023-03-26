package dev.mayaqq.spectrumJetpacks;

import de.dafuqs.spectrum.energy.color.InkColor;

import de.dafuqs.spectrum.registries.SpectrumItemGroups;
import dev.mayaqq.spectrumJetpacks.config.SpectrumJetpacksConfig;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import dev.mayaqq.spectrumJetpacks.networking.Packets;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectrumJetpacks implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("spectrumJetpacks");
	public static Identifier id(String path) {
		return new Identifier("spectrumjetpacks", path);
	}
	static OwoItemSettings settings = new OwoItemSettings().group(SpectrumItemGroups.ITEM_GROUP_GENERAL).tab(1).rarity(Rarity.RARE);
	public static SpectrumJetpacksConfig CONFIG;

	//register items
	public static final JetpackItem GEMSTONE_JETPACK = Registry.register(Registry.ITEM, id("gemstone_jetpack"), new JetpackItem(settings, id("gemstone_jetpack"), InkColor.of(DyeColor.PURPLE), 5000));
	public static final JetpackItem BEDROCK_JETPACK = Registry.register(Registry.ITEM, id("bedrock_jetpack"), new JetpackItem(settings, id("bedrock_jetpack"), InkColor.of(DyeColor.PURPLE), 10000));

	@Override
	public void onInitialize() {
		LOGGER.info("To the sky!");
		Packets.register();
		AutoConfig.register(SpectrumJetpacksConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(SpectrumJetpacksConfig.class).getConfig();
	}
}