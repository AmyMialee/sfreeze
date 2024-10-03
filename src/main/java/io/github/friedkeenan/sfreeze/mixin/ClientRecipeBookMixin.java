package io.github.friedkeenan.sfreeze.mixin;

import io.github.friedkeenan.sfreeze.Sfreeze;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(at = @At("HEAD"), method = "getCategory", cancellable = true)
    private static void sfreeze$preventBookError(@NotNull RecipeHolder<?> recipeHolder, CallbackInfoReturnable<RecipeBookCategories> cir) {
        if (recipeHolder.value().getType() == Sfreeze.SFREEZING) cir.setReturnValue(RecipeBookCategories.UNKNOWN);
    }
}