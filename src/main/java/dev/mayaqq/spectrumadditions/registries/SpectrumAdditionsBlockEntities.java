package dev.mayaqq.spectrumadditions.registries;

import dev.mayaqq.spectrumadditions.blocks.InkPowerGeneratorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static dev.mayaqq.spectrumadditions.SpectrumAdditions.saId;

public class SpectrumAdditionsBlockEntities {
    private static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, saId(), FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }
    public static void register() {
        INK_POWER_GENERATOR = void register("ink_power_generator", InkPowerGeneratorBlockEntity::new, INK_POWER_GENERATOR);
    }
}
