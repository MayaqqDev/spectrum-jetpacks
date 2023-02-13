package dev.mayaqq.spectrumadditions.mixin;

import dev.mayaqq.spectrumadditions.functions.JetpackPropel;
import dev.mayaqq.spectrumadditions.utils.EquipUtils;
import dev.mayaqq.spectrumadditions.utils.PlayerExtensionsForTheJetPackMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerExtensionsForTheJetPackMod {
    private boolean hasRecentlyUsedJetPack;

    @Inject(method = "tick", at = @At("TAIL"))
    @Environment(EnvType.CLIENT)
    public void jetpackTick(CallbackInfo ci) {
        PlayerEntity player = ((PlayerEntity) (Object) this);
        if (EquipUtils.hasJetpack(player) > 0) {
            JetpackPropel.propel(player);
        }
    }

    @Override
    public boolean hasRecentlyUsedJetPack() {
        return hasRecentlyUsedJetPack;
    }

    @Override
    public void setHasRecentlyUsedJetPack(boolean newValue) {
        hasRecentlyUsedJetPack = newValue;
    }
}