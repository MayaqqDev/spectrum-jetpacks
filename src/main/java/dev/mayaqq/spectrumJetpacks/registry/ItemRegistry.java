package dev.mayaqq.spectrumJetpacks.registry;

import de.dafuqs.spectrum.energy.color.InkColor;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.CONFIG;
import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.id;

public class ItemRegistry {

    public static final JetpackItem GEMSTONE_JETPACK = Registry.register(
            Registries.ITEM, id("gemstone_jetpack"), new JetpackItem(new FabricItemSettings().rarity(Rarity.RARE), id("gemstone_jetpack"),
                    InkColor.of(DyeColor.PURPLE),
                    CONFIG.gemstoneJetpackMaxFuel,
                    CONFIG.gemstoneJetpackHorizontalSpeedMultiplier,
                    CONFIG.gemstoneJetpackVerticalSpeedAdditionPerTick,
                    CONFIG.gemstoneJetpackMaxHorizontalVelocity,
                    CONFIG.gemstoneJetpackMaxVerticalVelocity
            ));
    public static final JetpackItem BEDROCK_JETPACK = Registry.register(
            Registries.ITEM, id("bedrock_jetpack"), new JetpackItem(new FabricItemSettings().rarity(Rarity.EPIC), id("bedrock_jetpack"),
                    InkColor.of(DyeColor.PURPLE),
                    CONFIG.bedrockJetpackMaxFuel,
                    CONFIG.bedrockJetpackHorizontalSpeedMultiplier,
                    CONFIG.bedrockJetpackVerticalSpeedAdditionPerTick,
                    CONFIG.bedrockJetpackMaxHorizontalVelocity,
                    CONFIG.bedrockJetpackMaxVerticalVelocity
            ));

    public static void register() {}
}