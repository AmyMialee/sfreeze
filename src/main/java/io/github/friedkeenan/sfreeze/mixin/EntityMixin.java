package io.github.friedkeenan.sfreeze.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract boolean isRemoved();
    @Shadow public abstract boolean canFreeze();
    @Shadow public boolean isInPowderSnow;
    @Shadow public abstract Level level();
    @Shadow public abstract int getTicksRequiredToFreeze();
    @Shadow public abstract int getTicksFrozen();
    @Shadow public abstract void setTicksFrozen(int i);
    @Shadow public abstract BlockPos getOnPos();
    @Shadow public abstract double getX();
    @Shadow public abstract double getY();
    @Shadow public abstract double getZ();

    @Inject(method = "tryCheckInsideBlocks", at = @At("TAIL"))
    protected void sfreeze$checkInsideBlocks(CallbackInfo ci) {}
}