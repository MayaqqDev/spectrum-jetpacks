package dev.mayaqq.spectrumadditions.networking;

import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import de.dafuqs.spectrum.particle.SpectrumParticleTypes;
import dev.mayaqq.spectrumadditions.items.JetpackItem;
import dev.mayaqq.spectrumadditions.utils.EquipUtils;
import dev.mayaqq.spectrumadditions.utils.PlayerExtensionsForTheJetPackMod;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;

import static dev.mayaqq.spectrumadditions.SpectrumAdditions.CONFIG;
import static dev.mayaqq.spectrumadditions.SpectrumAdditions.saId;

public class Packets {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(saId("propel"), (server, player, handler, buf, responseSender) -> {
            boolean hover = buf.readBoolean();
            server.execute(() -> {
                //particles, sounds, and energy drain
                ItemStack jetpack = EquipUtils.getJetpack(player);
                FixedSingleInkStorage inkStorage = EquipUtils.getEnergyStorage(jetpack);
                if (hover) {
                    inkStorage.drainEnergy(inkStorage.getStoredColor(), CONFIG.inkUsagePerTickHovering);
                } else {
                    inkStorage.drainEnergy(inkStorage.getStoredColor(), CONFIG.inkUsagePerTick);
                }
                ((JetpackItem) jetpack.getItem()).setEnergyStorage(jetpack, inkStorage);
                ((PlayerExtensionsForTheJetPackMod) player).setHasRecentlyUsedJetPack(true);
                //TODO: This sometimes breaks and looks a bit offset, fix it!
                float yawRadians = (float) Math.toRadians(-player.bodyYaw + 90);
                float xOff = 0.5f * (float) Math.cos(yawRadians);
                float zOff = -0.5f * (float) Math.sin(yawRadians);
                //play a sound in the location of the player
                player.getWorld().spawnParticles(SpectrumParticleTypes.SHOOTING_STAR, player.getX() + xOff, player.getY() + 0.5, player.getZ() + zOff, 1, 0, -0.1, 0, 0.1);
            });
        });
    }
}
