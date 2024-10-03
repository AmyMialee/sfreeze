package io.github.friedkeenan.sfreeze.util;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public record SimpleRecipeType<T extends Recipe<?>>(String name) implements RecipeType<T> {}