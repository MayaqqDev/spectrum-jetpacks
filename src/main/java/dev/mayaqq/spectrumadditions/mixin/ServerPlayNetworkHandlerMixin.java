package dev.mayaqq.spectrumadditions.mixin;

import dev.mayaqq.spectrumadditions.utils.PlayerExtensionsForTheJetPackMod;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @Shadow private boolean floating;

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onPlayerMove", at = @At("TAIL"))
    private void onPlayerMove(CallbackInfo ci) {
        if (((PlayerExtensionsForTheJetPackMod)player).hasRecentlyUsedJetPack()) {
            this.floating = false;
        }
    }
}