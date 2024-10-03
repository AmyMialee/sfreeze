package io.github.friedkeenan.sfreeze.recipe;

import io.github.friedkeenan.sfreeze.Sfreeze;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SfreezingRecipe implements Recipe<SingleRecipeInput> {
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;

    public SfreezingRecipe(String string, Ingredient ingredient, ItemStack itemStack) {
        this.group = string;
        this.ingredient = ingredient;
        this.result = itemStack;
    }

    @Override
    public boolean matches(@NotNull SingleRecipeInput singleRecipeInput, Level level) {
        return this.ingredient.test(singleRecipeInput.item());
    }

    @Override
    public @NotNull ItemStack assemble(SingleRecipeInput singleRecipeInput, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(this.ingredient);
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public @NotNull String getGroup() {
        return this.group;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Sfreeze.SFREEZING;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(Items.POWDER_SNOW_BUCKET);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Sfreeze.SFREEZING_RECIPE_SERIALIZER;
    }
}