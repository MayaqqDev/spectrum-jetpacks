package dev.mayaqq.spectrumadditions.registries;

import dev.mayaqq.spectrumadditions.blocks.InkPowerGeneratorBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;

public class SpectrumAdditionsBlocks {
    public static final InkPowerGeneratorBlock INK_POWER_GENERATOR = new InkPowerGeneratorBlock(FabricBlockSettings.of(Material.STONE).hardness(3.0F));
}
