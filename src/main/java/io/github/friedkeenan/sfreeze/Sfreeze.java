package io.github.friedkeenan.sfreeze;

import io.github.friedkeenan.sfreeze.recipe.SfreezingRecipe;
import io.github.friedkeenan.sfreeze.recipe.SfreezingRecipeSerializer;
import io.github.friedkeenan.sfreeze.util.SimpleRecipeType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sfreeze implements ModInitializer {
    public static final String MOD_ID = "sfreeze";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final SoundEvent SFREEZE_SOUND = SoundEvent.createVariableRangeEvent(id("entity.item.sfreeze"));
    public static final RecipeType<SfreezingRecipe> SFREEZING = Registry.register(BuiltInRegistries.RECIPE_TYPE, id("sfreezing"), new SimpleRecipeType<>("sfreeze:sfreezing"));
    public static final RecipeSerializer<SfreezingRecipe> SFREEZING_RECIPE_SERIALIZER = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id("sfreezing"), new SfreezingRecipeSerializer());

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, SFREEZE_SOUND.getLocation(), SFREEZE_SOUND);
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) LOGGER.info("sfreeze initialized!");
    }

    public static @NotNull ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}