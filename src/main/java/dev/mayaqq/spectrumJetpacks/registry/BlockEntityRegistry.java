package dev.mayaqq.spectrumJetpacks.registry;

import dev.mayaqq.spectrumJetpacks.blockEntites.InkChargerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.id;

public class BlockEntityRegistry {
    public static final BlockEntityType<InkChargerBlockEntity> INK_CHARGER_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            id("ink_charger_entity"),
            FabricBlockEntityTypeBuilder.create(InkChargerBlockEntity::new, BlockRegistry.INK_CHARGER).build()
    );
    public static void register() {

    }
}
