package dev.mayaqq.spectrumJetpacks.registry;

import dev.mayaqq.spectrumJetpacks.blocks.InkChargerBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.id;

public class BlockRegistry {
    public static final InkChargerBlock INK_CHARGER = new InkChargerBlock(FabricBlockSettings.of(Material.STONE).hardness(3.0F));

    public static void register() {
        Registry.register(Registry.BLOCK, id("ink_charger"), INK_CHARGER);
        Registry.register(Registry.ITEM, id("ink_charger"), new BlockItem(INK_CHARGER, new FabricItemSettings()));
    }
}
