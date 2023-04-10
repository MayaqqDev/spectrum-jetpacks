package dev.mayaqq.spectrumJetpacks.registry;

import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.registries.SpectrumItemGroups;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.CONFIG;
import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.id;

public class ItemRegistry {

    static OwoItemSettings settings = new OwoItemSettings().group(SpectrumItemGroups.ITEM_GROUP_GENERAL).tab(1).rarity(Rarity.RARE);
    public static final JetpackItem GEMSTONE_JETPACK = Registry.register(
            Registry.ITEM, id("gemstone_jetpack"), new JetpackItem(settings, id("gemstone_jetpack"),
                    InkColor.of(DyeColor.PURPLE),
                    CONFIG.gemstoneJetpackMaxFuel,
                    CONFIG.gemstoneJetpackHorizontalSpeedMultiplier,
                    CONFIG.gemstoneJetpackVerticalSpeedAdditionPerTick,
                    CONFIG.gemstoneJetpackMaxHorizontalVelocity,
                    CONFIG.gemstoneJetpackMaxVerticalVelocity
            ));
    public static final JetpackItem BEDROCK_JETPACK = Registry.register(
            Registry.ITEM, id("bedrock_jetpack"), new JetpackItem(settings, id("bedrock_jetpack"),
                    InkColor.of(DyeColor.PURPLE),
                    CONFIG.bedrockJetpackMaxFuel,
                    CONFIG.bedrockJetpackHorizontalSpeedMultiplier,
                    CONFIG.bedrockJetpackVerticalSpeedAdditionPerTick,
                    CONFIG.bedrockJetpackMaxHorizontalVelocity,
                    CONFIG.bedrockJetpackMaxVerticalVelocity
            ));

    public static void register() {}
}