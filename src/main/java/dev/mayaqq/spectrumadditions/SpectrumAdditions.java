package dev.mayaqq.spectrumadditions;

import de.dafuqs.spectrum.registries.SpectrumItemGroups;
import dev.mayaqq.spectrumadditions.config.SpectrumAdditionsConfig;
import dev.mayaqq.spectrumadditions.networking.Packets;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectrumAdditions implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("spectrumAdditions");
    public static SpectrumAdditionsConfig CONFIG;

    public static Identifier saId(String path) {
        return new Identifier("spectrumadditions", path);
    }
    public static Identifier saId() {
        return new Identifier("spectrumadditions");
    }

    @Override
    public void onInitialize() {
        LOGGER.info("To the sky!");
        Packets.register();
        AutoConfig.register(SpectrumAdditionsConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SpectrumAdditionsConfig.class).getConfig();
    }
}