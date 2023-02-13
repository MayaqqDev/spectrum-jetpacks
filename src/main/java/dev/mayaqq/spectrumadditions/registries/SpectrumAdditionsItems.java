package dev.mayaqq.spectrumadditions.registries;

import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.registries.SpectrumItemGroups;
import dev.mayaqq.spectrumadditions.items.JetpackItem;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static dev.mayaqq.spectrumadditions.SpectrumAdditions.saId;

public class SpectrumAdditionsItems {
    static OwoItemSettings settings = new OwoItemSettings().group(SpectrumItemGroups.ITEM_GROUP_GENERAL).tab(1).rarity(Rarity.RARE);

    public static final JetpackItem GEMSTONE_JETPACK = Registry.register(Registry.ITEM, saId("gemstone_jetpack"), new JetpackItem(settings, saId("gemstone_jetpack"), InkColor.of(DyeColor.PURPLE), 5000));

}
