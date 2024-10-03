package io.github.friedkeenan.sfreeze.mixin;

import io.github.friedkeenan.sfreeze.Sfreeze;
import io.github.friedkeenan.sfreeze.util.Sfreezable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements Sfreezable {
    @Unique private Item sfreeze$result = null;

    @Override
    public Item sfreeze$getResult(Level level) {
        if (this.sfreeze$result == null) this.sfreeze$updateResult(level);
        return this.sfreeze$result;
    }

    @Override
    public void sfreeze$updateResult(@NotNull Level level) {
        var self = (Item) (Object) this;
        var holder = level.getRecipeManager().getRecipeFor(Sfreeze.SFREEZING, new SingleRecipeInput(self.getDefaultInstance()), level);
        if (holder.isPresent()) {
            this.sfreeze$result = holder.get().value().getResultItem(level.registryAccess()).getItem();
        } else {
            this.sfreeze$result = Items.AIR;
        }
    }
}