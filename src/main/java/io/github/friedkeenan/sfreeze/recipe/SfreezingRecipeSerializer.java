package io.github.friedkeenan.sfreeze.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class SfreezingRecipeSerializer implements RecipeSerializer<SfreezingRecipe> {
    private final MapCodec<SfreezingRecipe> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, SfreezingRecipe> streamCodec;

    public SfreezingRecipeSerializer() {
        this.codec = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                        ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                        )
                        .apply(instance, SfreezingRecipe::new)
        );
        this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
    }

    @Override
    public @NotNull MapCodec<SfreezingRecipe> codec() {
        return this.codec;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, SfreezingRecipe> streamCodec() {
        return this.streamCodec;
    }

    private @NotNull SfreezingRecipe fromNetwork(@NotNull RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        var string = registryFriendlyByteBuf.readUtf();
        var ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(registryFriendlyByteBuf);
        var itemStack = ItemStack.STREAM_CODEC.decode(registryFriendlyByteBuf);
        return new SfreezingRecipe(string, ingredient, itemStack);
    }

    private void toNetwork(@NotNull RegistryFriendlyByteBuf registryFriendlyByteBuf, @NotNull SfreezingRecipe recipe) {
        registryFriendlyByteBuf.writeUtf(recipe.group);
        Ingredient.CONTENTS_STREAM_CODEC.encode(registryFriendlyByteBuf, recipe.ingredient);
        ItemStack.STREAM_CODEC.encode(registryFriendlyByteBuf, recipe.result);
    }
}