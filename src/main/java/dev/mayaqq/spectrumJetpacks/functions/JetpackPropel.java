package dev.mayaqq.spectrumJetpacks.functions;

import de.dafuqs.spectrum.energy.storage.FixedSingleInkStorage;
import dev.mayaqq.spectrumJetpacks.items.JetpackItem;
import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import static dev.mayaqq.spectrumJetpacks.SpectrumJetpacks.CONFIG;
import static dev.mayaqq.spectrumJetpacks.registry.KeybindRegistry.*;


public class JetpackPropel {

    public static void propel(PlayerEntity player) {
        ItemStack jetpack = EquipUtils.getJetpack(player);
        JetpackItem jetpackItem = (JetpackItem) jetpack.getItem();

        float maxVerticalVelocity = jetpackItem.getMaxVerticalVelocity();
        float verticalSpeed = jetpackItem.getVerticalSpeed();
        float maxHorizontalVelocity = jetpackItem.getMaxHorizontalVelocity();
        float horizontalSpeed = jetpackItem.getHorizontalSpeed();

        FixedSingleInkStorage inkStorage = EquipUtils.getEnergyStorage(jetpack);
        long storedInk = inkStorage.getEnergy(inkStorage.getStoredColor());

        PacketByteBuf buf = PacketByteBufs.create();
        Vec3d v = player.getVelocity();
        MinecraftClient mc = MinecraftClient.getInstance();

        if (toggleKey.isPressed()) {;
            // vertical motion
            if (player.isOnGround() || storedInk <= 0) {
                return;
            } else if (mc.options.jumpKey.isPressed()) {
                v = new Vec3d(v.x, Math.min(maxVerticalVelocity, v.y + verticalSpeed), v.z);
                buf.writeBoolean(true);
                ClientPlayNetworking.send(new Identifier("spectrumjetpacks", "propel"), buf);
            } else if (player.isSneaking()) {
                v = new Vec3d(v.x, Math.max(-maxVerticalVelocity, v.y - verticalSpeed), v.z);
                buf.writeBoolean(false);
                ClientPlayNetworking.send(new Identifier("spectrumjetpacks", "propel"), buf);
            } else if (hoverKey.isPressed()) {
                v = new Vec3d(v.x, Math.max(-0.01f, v.y), v.z);
                buf.writeBoolean(false);
                ClientPlayNetworking.send(new Identifier("spectrumjetpacks", "propel"), buf);
            }
            // horizontal motion
            if (player.forwardSpeed > 0.0F && boostKey.isPressed()) {
                float yaw = player.getYaw(1.0f);
                float pitch = player.getPitch(1.0f);
                double xDir = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
                double zDir = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
                Vec3d direction = new Vec3d(xDir, v.y, zDir).normalize();

                double speedMultiplier = horizontalSpeed;
                Vec3d boost = direction.multiply(speedMultiplier, 0.0, speedMultiplier);
                Vec3d newVelocity = player.getVelocity().add(boost);
                double maxSpeed = Math.max(maxHorizontalVelocity, newVelocity.length());
                double keepY = v.y;
                v = new Vec3d(newVelocity.x, keepY, newVelocity.z).normalize().multiply(maxSpeed);
                v = new Vec3d(v.x, keepY, v.z);
            }
            sound(player);
            player.setVelocity(v);
        }
    }
    private static void sound(PlayerEntity player) {
        if (CONFIG.soundsEnabled) {
            player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 0.2f, 1.0f);
        }
    }
}