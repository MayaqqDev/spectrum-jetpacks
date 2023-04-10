package dev.mayaqq.spectrumJetpacks.networking;

import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import de.dafuqs.spectrum.particle.SpectrumParticleTypes;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import dev.mayaqq.spectrumJetpacks.utils.JetpackPlayerExtension;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.CONFIG;
import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.id;
import static dev.mayaqq.spectrumJetpacks.registry.ServerEventRegistry.tick;

public class C2SPackets {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(id("propel"), (server, player, handler, buf, responseSender) -> {
            boolean goingUp = buf.readBoolean();
            server.execute(() -> {
                //particles, sounds, and energy drain
                ItemStack jetpack = EquipUtils.getJetpack(player);
                FixedSingleInkStorage inkStorage = EquipUtils.getEnergyStorage(jetpack);
                if (tick && !player.isCreative()) {
                    if (goingUp) {
                        inkStorage.drainEnergy(inkStorage.getStoredColor(), CONFIG.inkUsagePerTickGoingUp);
                    } else {
                        inkStorage.drainEnergy(inkStorage.getStoredColor(), CONFIG.inkUsagePerTick);
                    }
                }
                player.forwardSpeed = 10f;
                ((JetpackItem)jetpack.getItem()).setEnergyStorage(jetpack, inkStorage);
                ((JetpackPlayerExtension) player).setHasRecentlyUsedJetPack(true);
                //TODO: This sometimes breaks and looks a bit offset, fix it!
                ServerWorld world = player.getWorld();
                double xOffset = 1; // adjust this value as needed
                double yOffset = 0.6; // adjust this value as needed
                double zOffset = 1; // adjust this value as needed

                double playerX = player.getX();
                double playerY = player.getY();
                double playerZ = player.getZ();

                float yaw = player.getYaw();

                double cosYaw = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
                double sinYaw = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);

                double centerX = playerX + xOffset * sinYaw;
                double centerY = playerY + yOffset;
                double centerZ = playerZ + xOffset * cosYaw;

                double leftX = centerX - xOffset * cosYaw;
                double leftZ = centerZ + xOffset * sinYaw;

                double rightX = centerX + xOffset * cosYaw;
                double rightZ = centerZ - xOffset * sinYaw;

                world.spawnParticles(SpectrumParticleTypes.SHOOTING_STAR, leftX, centerY, leftZ, 1, 0, -0.1, 0, 0.5);
                world.spawnParticles(SpectrumParticleTypes.SHOOTING_STAR, centerX, centerY, centerZ,1, 0, -0.1, 0, 0.5);
                world.spawnParticles(SpectrumParticleTypes.SHOOTING_STAR, rightX, centerY, rightZ,1, 0, -0.1, 0, 0.5);
            });
        });
    }
}
