package io.github.friedkeenan.sfreeze;

import io.github.friedkeenan.sfreeze.recipe.SfreezingRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SfreezeDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(@NotNull FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(SfreezeLanguageProvider::new);
        pack.addProvider(SfreezeRecipeGenerator::new);
    }

    public static class SfreezeLanguageProvider extends FabricLanguageProvider {
        public SfreezeLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generateTranslations(HolderLookup.Provider registryLookup, @NotNull TranslationBuilder translationBuilder) {
            translationBuilder.add(subtitleString(Sfreeze.SFREEZE_SOUND), "Item sfreezes");
        }

        public static @NotNull String subtitleString(@NotNull SoundEvent event) {
            return "subtitles." + event.getLocation().getNamespace() + "." + event.getLocation().getPath();
        }
    }

    public static class SfreezeRecipeGenerator extends FabricRecipeProvider {
        public SfreezeRecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        public void buildRecipes(RecipeOutput exporter) {
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.BAKED_POTATO), Items.POTATO);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.BLACK_GLAZED_TERRACOTTA), Items.BLACK_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.BLUE_GLAZED_TERRACOTTA), Items.BLUE_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.BRICK), Items.CLAY_BALL);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.BROWN_GLAZED_TERRACOTTA), Items.BROWN_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.MISC, Ingredient.of(Items.CHARCOAL), Items.OAK_LOG);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.COOKED_BEEF), Items.BEEF);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.COOKED_CHICKEN), Items.CHICKEN);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.COOKED_COD), Items.COD);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.COOKED_MUTTON), Items.MUTTON);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.COOKED_PORKCHOP), Items.PORKCHOP);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.COOKED_RABBIT), Items.RABBIT);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.COOKED_SALMON), Items.SALMON);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.MISC, Ingredient.of(Items.COPPER_INGOT), Items.RAW_COPPER);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.CRACKED_DEEPSLATE_BRICKS), Items.DEEPSLATE_BRICKS);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.CRACKED_DEEPSLATE_TILES), Items.DEEPSLATE_TILES);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.CRACKED_NETHER_BRICKS), Items.NETHER_BRICKS);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS), Items.POLISHED_BLACKSTONE_BRICKS);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.CRACKED_STONE_BRICKS), Items.STONE_BRICKS);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.CYAN_GLAZED_TERRACOTTA), Items.CYAN_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.DEEPSLATE), Items.COBBLED_DEEPSLATE);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.DRIED_KELP), Items.KELP);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.GLASS), Items.SAND);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.MISC, Ingredient.of(Items.GOLD_INGOT), Items.RAW_GOLD);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.GRAY_GLAZED_TERRACOTTA), Items.GRAY_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.MISC, Ingredient.of(Items.GREEN_DYE), Items.CACTUS);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.GREEN_GLAZED_TERRACOTTA), Items.GREEN_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.MISC, Ingredient.of(Items.IRON_INGOT), Items.RAW_IRON);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.LIGHT_BLUE_GLAZED_TERRACOTTA), Items.LIGHT_BLUE_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.LIGHT_GRAY_GLAZED_TERRACOTTA), Items.LIGHT_GRAY_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.MISC, Ingredient.of(Items.LIME_DYE), Items.SEA_PICKLE);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.LIME_GLAZED_TERRACOTTA), Items.LIME_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.MAGENTA_GLAZED_TERRACOTTA), Items.MAGENTA_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.MISC, Ingredient.of(Items.NETHERITE_SCRAP), Items.ANCIENT_DEBRIS);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.NETHER_BRICK), Items.NETHERRACK);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.ORANGE_GLAZED_TERRACOTTA), Items.ORANGE_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.PINK_GLAZED_TERRACOTTA), Items.PINK_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.FOOD, Ingredient.of(Items.POPPED_CHORUS_FRUIT), Items.CHORUS_FRUIT);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.PURPLE_GLAZED_TERRACOTTA), Items.PURPLE_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.RED_GLAZED_TERRACOTTA), Items.RED_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.SMOOTH_BASALT), Items.BASALT);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.SMOOTH_QUARTZ), Items.QUARTZ_BLOCK);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.SMOOTH_RED_SANDSTONE), Items.RED_SANDSTONE);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.SMOOTH_SANDSTONE), Items.SANDSTONE);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.SMOOTH_STONE), Items.STONE);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.SPONGE), Items.WET_SPONGE);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.STONE), Items.COBBLESTONE);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.TERRACOTTA), Items.CLAY);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.WHITE_GLAZED_TERRACOTTA), Items.WHITE_TERRACOTTA);
            SfreezingRecipeBuilder.write(exporter, RecipeCategory.BUILDING_BLOCKS, Ingredient.of(Items.YELLOW_GLAZED_TERRACOTTA), Items.YELLOW_TERRACOTTA);
        }
    }
}