package dev.mayaqq.spectrumJetpacks.networking;

import de.dafuqs.spectrum.energy.color.InkColor;
import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import de.dafuqs.spectrum.particle.SpectrumParticleTypes;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import dev.mayaqq.spectrumJetpacks.utils.JetpackPlayerExtension;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;

import java.util.HashMap;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.CONFIG;
import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.id;
import static dev.mayaqq.spectrumJetpacks.registry.ServerEventRegistry.tick;

public class C2SPackets {

    public static HashMap<PlayerEntity, Boolean> propellingMap = new HashMap<>();

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(id("propel"), (server, player, handler, buf, responseSender) -> {
            int working = buf.readInt();
            server.execute(() -> {
                //particles, sounds, and energy drain
                ItemStack jetpack = EquipUtils.getJetpack(player);
                FixedSingleInkStorage inkStorage = EquipUtils.getEnergyStorage(jetpack);
                InkColor color = inkStorage.getStoredColor();
                propellingMap.put(player, true);
                if (tick && !player.isCreative()) {
                    switch (working) {
                        case 0 -> inkStorage.drainEnergy(color, CONFIG.inkUsagePerTick);
                        case 1 -> inkStorage.drainEnergy(color, CONFIG.inkUsagePerTickGoingUp);
                        case 2 -> inkStorage.drainEnergy(color, CONFIG.inkUsagePerTickForward);
                    }
                }
                player.forwardSpeed = 10f;
                ((JetpackItem)jetpack.getItem()).setEnergyStorage(jetpack, inkStorage);
                ((JetpackPlayerExtension) player).setHasRecentlyUsedJetPack(true);
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(id("stoppropel"), (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                propellingMap.put(player, false);
            });
        });
    }
}
