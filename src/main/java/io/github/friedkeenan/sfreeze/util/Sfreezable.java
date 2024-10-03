package io.github.friedkeenan.sfreeze.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public interface Sfreezable {
    default Item sfreeze$getResult(Level level) {
        return null;
    }

    default void sfreeze$updateResult(Level level) {}
}