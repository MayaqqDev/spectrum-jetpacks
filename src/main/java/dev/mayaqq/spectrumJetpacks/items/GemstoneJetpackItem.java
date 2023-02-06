package dev.mayaqq.spectrumJetpacks.items;

import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class GemstoneJetpackItem extends JetpackItem {
    public GemstoneJetpackItem(Settings settings, Identifier identifier, InkColor inkColor, long maxInk) {
        super(settings, identifier, inkColor, maxInk);
    }

    @Override
    public FixedSingleInkStorage getEnergyStorage(ItemStack itemStack) {
        NbtCompound compound = itemStack.getNbt();
        return compound != null && compound.contains("EnergyStore") ? FixedSingleInkStorage.fromNbt(compound.getCompound("EnergyStore")) : new FixedSingleInkStorage(this.maxInk, this.inkColor);
    }

    @Override
    public void setEnergyStorage(ItemStack itemStack, FixedSingleInkStorage storage) {
        NbtCompound compound = itemStack.getOrCreateNbt();
        compound.put("EnergyStore", storage.toNbt());
    }

    @Override
    public Drainability getDrainability() {
        return Drainability.PLAYER_ONLY;
    }
}
