package io.github.friedkeenan.sfreeze.recipe;

import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SfreezingRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final Ingredient ingredient;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private @Nullable String group;

    private SfreezingRecipeBuilder(RecipeCategory recipeCategory, @NotNull ItemLike itemLike, Ingredient ingredient) {
        this.category = recipeCategory;
        this.result = itemLike.asItem();
        this.ingredient = ingredient;
        this.unlockedBy("has_ingredient", VanillaRecipeProvider.has(itemLike));
    }

    public static void write(RecipeOutput exporter, RecipeCategory category, Ingredient ingredient, Item result) {
        var builder = new SfreezingRecipeBuilder(category, result, ingredient);
        builder.unlockedBy("has_ingredient", VanillaRecipeProvider.has(result));
        builder.save(exporter);
    }

    @Override
    public @NotNull SfreezingRecipeBuilder unlockedBy(String string, Criterion<?> criterion) {
        this.criteria.put(string, criterion);
        return this;
    }

    @Override
    public @NotNull SfreezingRecipeBuilder group(@Nullable String string) {
        this.group = string;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result;
    }

    @Override
    public void save(@NotNull RecipeOutput recipeOutput, ResourceLocation resourceLocation) {
        this.ensureValid(resourceLocation);
        var builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation)).rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        var recipe = new SfreezingRecipe(Objects.requireNonNullElse(this.group, ""), this.ingredient, new ItemStack(this.result));
        recipeOutput.accept(resourceLocation, recipe, builder.build(resourceLocation.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation resourceLocation) {
        if (this.criteria.isEmpty()) throw new IllegalStateException("No way of obtaining recipe " + resourceLocation);
    }
}