package dev.mayaqq.spectrumadditions.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class InkPowerGeneratorBlockEntity extends BlockEntity {
    public static int RANGE = 64;

    public InkPowerGeneratorBlockEntity(BlockEntityType blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public boolean canSee(InkPowerGeneratorBlockEntity duct) {
        return duct.pos.isWithinDistance(pos, RANGE);
    }

    private boolean canTransferTo(BlockEntity blockEntity) {
        return blockEntity instanceof InkPowerGeneratorBlockEntity;
    }

    public int getRange() {
        return RANGE;
    }
}
