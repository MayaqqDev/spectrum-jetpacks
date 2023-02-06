package dev.mayaqq.spectrumJetpacks.functions;

import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacksClient.hoverKey;
import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacksClient.toggleKey;

public class JetpackPropel {
    public static void propel(PlayerEntity player) {
        if (toggleKey.isPressed()) {
            ItemStack jetpack = EquipUtils.getJetpack(player);
            FixedSingleInkStorage inkStorage = EquipUtils.getEnergyStorage(jetpack);
            long storedInk = inkStorage.getEnergy(inkStorage.getStoredColor());
            float propelMax = 0.1f;
            float propelSpeed = 0.1f;
            if (EquipUtils.hasJetpack(player) == 1) {
                propelMax = 0.3f;
            }
            if (MinecraftClient.getInstance().options.jumpKey.isPressed() && storedInk > 0) {
                player.setVelocity(player.getVelocity().x, Math.min(propelMax, player.getVelocity().y + propelSpeed), player.getVelocity().z);
                inkStorage.drainEnergy(inkStorage.getStoredColor(), 1);
                ClientPlayNetworking.send(new Identifier("spectrumjetpacks", "propel"), PacketByteBufs.empty());
            } else if (player.isSneaking() && storedInk > 0) {
                player.setVelocity(player.getVelocity().x, Math.max(-propelMax, player.getVelocity().y - propelSpeed), player.getVelocity().z);
                inkStorage.drainEnergy(inkStorage.getStoredColor(), 1);
                ClientPlayNetworking.send(new Identifier("spectrumjetpacks", "propel"), PacketByteBufs.empty());
            } else if (hoverKey.isPressed() && storedInk > 0) {
                player.setVelocity(player.getVelocity().x, Math.max(0.0, player.getVelocity().y), player.getVelocity().z);
                inkStorage.drainEnergy(inkStorage.getStoredColor(), 2);
                ClientPlayNetworking.send(new Identifier("spectrumjetpacks", "propel"), PacketByteBufs.empty());
            }
            ((JetpackItem)jetpack.getItem()).setEnergyStorage(jetpack, inkStorage);
        }
    }
}