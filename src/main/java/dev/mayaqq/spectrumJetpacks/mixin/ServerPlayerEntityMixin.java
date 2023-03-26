package dev.mayaqq.spectrumJetpacks.mixin;

import dev.mayaqq.spectrumJetpacks.utils.JetpackPlayerExtension;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public class ServerPlayerEntityMixin implements JetpackPlayerExtension {
    private boolean hasRecentlyUsedJetPack;
    @Override public boolean hasRecentlyUsedJetPack() { return hasRecentlyUsedJetPack; }
    @Override public void setHasRecentlyUsedJetPack(boolean newValue) { hasRecentlyUsedJetPack = newValue; }
}
