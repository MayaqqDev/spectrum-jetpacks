package dev.mayaqq.spectrumJetpacks.blocks;

import de.dafuqs.spectrum.blocks.energy.ColorPickerBlock;
import de.dafuqs.spectrum.blocks.energy.ColorPickerBlockEntity;
import de.dafuqs.spectrum.registries.SpectrumBlockEntities;
import dev.mayaqq.spectrumJetpacks.blockEntites.InkChargerBlockEntity;
import dev.mayaqq.spectrumJetpacks.registry.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public class InkChargerBlock extends BlockWithEntity {

    public InkChargerBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InkChargerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return !world.isClient ? checkType(type, BlockEntityRegistry.INK_CHARGER_ENTITY, InkChargerBlockEntity::tick) : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getGameEventListener(ServerWorld world, T blockEntity) {
        return super.getGameEventListener(world, blockEntity);
    }
}
