package dev.mayaqq.spectrumJetpacks.mixin;

import dev.mayaqq.spectrumJetpacks.functions.JetpackPropel;
import dev.mayaqq.spectrumJetpacks.utils.EquipUtils;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    public void jetpackTick(CallbackInfo ci) {
        PlayerEntity player = ((PlayerEntity) (Object) this);
        if (EquipUtils.hasJetpack(player)) {
            JetpackPropel.propel(player);
        }
    }
}