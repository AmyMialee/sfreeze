package io.github.friedkeenan.sfreeze.mixin;

import io.github.friedkeenan.sfreeze.Sfreeze;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends EntityMixin {
    @Unique private boolean lastFreezing = false;

    @Shadow public abstract ItemStack getItem();

    @Override
    protected void sfreeze$checkInsideBlocks(CallbackInfo ci) {
        this.lastFreezing = this.isInPowderSnow;
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void sfreeze$freezeItem(CallbackInfo info) {
        if (this.isRemoved() || this.level().isClientSide()) return;
        if (this.canFreeze() && this.lastFreezing) {
            this.setTicksFrozen(Math.min(this.getTicksRequiredToFreeze(), this.getTicksFrozen() + 1));
            var stack = this.getItem();
            var result = stack.getItem().sfreeze$getResult(this.level());
            if (this.getTicksFrozen() >= this.getTicksRequiredToFreeze() && result != Items.AIR) {
                this.level().playSound(null, this.getOnPos(), Sfreeze.SFREEZE_SOUND, SoundSource.NEUTRAL, 1.0f, 1.0f);
                this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), new ItemStack(result)));
                stack.shrink(1);
            }
        } else {
            this.setTicksFrozen(Math.max(0, this.getTicksFrozen() - 2));
        }
    }
}