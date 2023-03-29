package dev.mayaqq.spectrumJetpacks.utils;

import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Pair;

import java.util.Optional;

import static dev.mayaqq.spectrumJetpacks.registry.ItemRegistry.BEDROCK_JETPACK;
import static dev.mayaqq.spectrumJetpacks.registry.ItemRegistry.GEMSTONE_JETPACK;

public class EquipUtils {
    public static int hasJetpack(PlayerEntity player) {
        // 0 = no jetpack
        // 1 = gemstone jetpack
        // 2 = bedrock jetpack
        // more
        if (TrinketsApi.getTrinketComponent(player).get().isEquipped(GEMSTONE_JETPACK)) {
            return 1;
        }
        if (TrinketsApi.getTrinketComponent(player).get().isEquipped(BEDROCK_JETPACK)) {
            return 2;
        }
        return 0;
    }
    public static ItemStack getJetpack(PlayerEntity player) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player);
        for (Pair<SlotReference, ItemStack> pair : component.get().getEquipped(item -> true)) {
            SlotReference slot = pair.getLeft();
            TrinketInventory inv = slot.inventory();
            if (inv.getSlotType().getName().equals("back")) {
                ItemStack stack = pair.getRight();
                return stack;
            }
        }
        return null;
    }

    public static FixedSingleInkStorage getEnergyStorage(ItemStack stack) {
        return ((JetpackItem) stack.getItem()).getEnergyStorage(stack);
    }
    public static long getEnergy(ItemStack stack) {
        try {
            FixedSingleInkStorage inkStorage = ((JetpackItem) stack.getItem()).getEnergyStorage(stack);
            return inkStorage.getEnergy(InkColor.of(DyeColor.PURPLE));
        } catch (Exception e) {
            return 0;
        }
    }
}