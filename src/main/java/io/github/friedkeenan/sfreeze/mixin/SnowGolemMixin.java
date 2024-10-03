package io.github.friedkeenan.sfreeze.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowGolem.class)
public abstract class SnowGolemMixin extends LivingEntityMixin {
    protected SnowGolemMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void sfreeze$endOfHurt(DamageSource source, float damage, @NotNull CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValue() || this.level().isClientSide()) return;
        var pos = this.getOnPos();
        if (!this.mayInteract(this.level(), pos)) return;
        if (this.random.nextInt(3) == 0) {
            var state = this.level().getBlockState(pos);
            var newState = state;
            if (state.is(Blocks.CAULDRON)) {
                newState = Blocks.POWDER_SNOW_CAULDRON.defaultBlockState();
            } else if (state.is(Blocks.POWDER_SNOW_CAULDRON)) {
                newState = state.cycle(LayeredCauldronBlock.LEVEL);
            }
            if (state != newState) {
                this.level().setBlockAndUpdate(pos, newState);
                this.level().gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
            }
        }
    }
}