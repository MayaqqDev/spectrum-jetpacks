package dev.mayaqq.spectrumadditions.functions;

import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import dev.mayaqq.spectrumadditions.utils.EquipUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import static dev.mayaqq.spectrumadditions.SpectrumAdditions.CONFIG;
import static dev.mayaqq.spectrumadditions.SpectrumAdditions.saId;
import static dev.mayaqq.spectrumadditions.SpectrumAdditionsClient.hoverKey;
import static dev.mayaqq.spectrumadditions.SpectrumAdditionsClient.toggleKey;


public class JetpackPropel {

    public static void propel(PlayerEntity player) {
        if (toggleKey.isPressed()) {
            float propelMax = 0.1f;
            float propelSpeed = 0.1f;
            if (EquipUtils.hasJetpack(player) == 1) {
                propelMax = 0.3f;
            }
            ItemStack jetpack = EquipUtils.getJetpack(player);
            FixedSingleInkStorage inkStorage = EquipUtils.getEnergyStorage(jetpack);
            long storedInk = inkStorage.getEnergy(inkStorage.getStoredColor());
            PacketByteBuf buf = PacketByteBufs.create();
            if (MinecraftClient.getInstance().options.jumpKey.isPressed() && storedInk > 0) {
                player.setVelocity(player.getVelocity().x, Math.min(propelMax, player.getVelocity().y + propelSpeed), player.getVelocity().z);
                sound(player);
                buf.writeBoolean(false);
                ClientPlayNetworking.send(saId("propel"), buf);
            } else if (player.isSneaking() && storedInk > 0) {
                player.setVelocity(player.getVelocity().x, Math.max(-propelMax, player.getVelocity().y - propelSpeed), player.getVelocity().z);
                sound(player);
                buf.writeBoolean(false);
                ClientPlayNetworking.send(saId("propel"), buf);
            } else if (hoverKey.isPressed() && storedInk > 0) {
                player.setVelocity(player.getVelocity().x, Math.max(0.0, player.getVelocity().y), player.getVelocity().z);
                sound(player);
                buf.writeBoolean(true);
                ClientPlayNetworking.send(saId("propel"), buf);
            }
        }
    }

    private static void sound(PlayerEntity player) {
        if (CONFIG.soundsEnabled) {
            player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 0.2f, 1.0f);
        }
    }
}