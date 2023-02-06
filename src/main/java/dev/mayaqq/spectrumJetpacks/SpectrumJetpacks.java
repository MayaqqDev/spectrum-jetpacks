package dev.mayaqq.spectrumJetpacks;

import de.dafuqs.spectrum.energy.color.InkColor;
import dev.mayaqq.spectrumJetpacks.items.GemstoneJetpackItem;
import dev.mayaqq.spectrumJetpacks.utils.PlayerExtensionsForTheJetPackMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectrumJetpacks implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("spectrumJetpacks");

	public static Identifier id(String path) {
		return new Identifier("spectrumjetpacks", path);
	}
	public static final GemstoneJetpackItem GEMSTONE_JETPACK = Registry.register(Registry.ITEM, id("gemstone_jetpack"), new GemstoneJetpackItem(new FabricItemSettings(), id("gemstone_jetpack"), InkColor.of(DyeColor.PURPLE), 10000));

	@Override
	public void onInitialize() {
		LOGGER.info("To the sky!");

		ServerPlayNetworking.registerGlobalReceiver(id("propel"), (server, player, handler, buf, responseSender) -> {
			server.execute(() -> {
				((PlayerExtensionsForTheJetPackMod) player).setHasRecentlyUsedJetPack(true);
				float yawRadians = (float) Math.toRadians(-player.bodyYaw + 90);
				float xOff = 0.5f * (float) Math.cos(yawRadians);
				float zOff = -0.5f * (float) Math.sin(yawRadians);
				//play a sound in the location of the player
				player.playSound(SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 0.2f, 1.0f);
				player.getWorld().spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, player.getX() + xOff, player.getY() + 0.5, player.getZ() + zOff, 1, 0, -0.1, 0, 0.1);
			});
		});
	}
}