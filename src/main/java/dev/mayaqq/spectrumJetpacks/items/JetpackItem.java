package dev.mayaqq.spectrumJetpacks.items;

import de.dafuqs.spectrum.energy.InkStorageItem;
import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import de.dafuqs.spectrum.items.trinkets.SpectrumTrinketItem;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class JetpackItem extends SpectrumTrinketItem implements InkStorageItem<FixedSingleInkStorage> {

    public final InkColor inkColor;
    public final long maxInk;

    public JetpackItem(Settings settings, Identifier identifier ,InkColor inkColor, long maxInk) {
        super(settings.maxCount(1), identifier);
        this.inkColor = inkColor;
        this.maxInk = maxInk;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        FixedSingleInkStorage inkStorage = getEnergyStorage(stack);
        long storedInk = inkStorage.getEnergy(inkStorage.getStoredColor());

        if (storedInk == maxInk) {
            tooltip.add(Text.translatable("spectrum.tooltip.ink_drain.tooltip.maxed_out").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("Stored Ink: " + storedInk).formatted(Formatting.GRAY));
        }
    }
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return true;
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onEquip(stack, slot, entity);
    }
}