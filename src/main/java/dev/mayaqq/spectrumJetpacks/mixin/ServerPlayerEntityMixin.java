package dev.mayaqq.spectrumJetpacks.mixin;

import dev.mayaqq.spectrumJetpacks.networking.C2SPackets;
import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import dev.mayaqq.spectrumJetpacks.utils.JetpackPlayerExtension;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class ServerPlayerEntityMixin implements JetpackPlayerExtension {
    private boolean hasRecentlyUsedJetPack;
    private boolean isFromFalling;
    @Override public boolean hasRecentlyUsedJetPack() { return hasRecentlyUsedJetPack; }
    @Override public void setHasRecentlyUsedJetPack(boolean newValue) { hasRecentlyUsedJetPack = newValue; }

    @Inject(method = "damage", at = @At("HEAD"))
    public void damage$spectrumjetpacks(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isFromFalling()) {
            isFromFalling = true;
        } else {
            isFromFalling = false;
        }
    }

    @ModifyVariable(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z"), argsOnly = true)
    private float modifyDamage(float damage) {
        if (isFromFalling && EquipUtils.hasJetpack((PlayerEntity) (Object) this) && C2SPackets.propellingMap.get((PlayerEntity) (Object) this)) {
            return damage / 1.5f;
        }
        return damage;
    }

}
