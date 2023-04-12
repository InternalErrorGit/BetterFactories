package net.internalerror.betterfactories.data.recipes;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.internalerror.betterfactories.util.Util;
import net.internalerror.betterfactories.world.item.crafting.CrushingRecipeSerializer;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Getter
@AllArgsConstructor
public class FinishedCrushingRecipe implements FinishedRecipe {
  
  private final ResourceLocation id;
  private final ItemStack resultItem;
  private final float resultItemChance;
  private final ItemStack secondaryResultItem;
  private final float secondaryResultItemChance;
  private final int processingTime;
  private final Ingredient ingredient;
  private String group;
  private final Advancement.Builder advancement;
  private final ResourceLocation advancementId;
  
  @Override
  public void serializeRecipeData(@NotNull JsonObject pJson) {
    pJson.add(CrushingRecipeSerializer.JSON_KEY_RESULT, Util.serializeItemStack(resultItem));
    pJson.addProperty(CrushingRecipeSerializer.JSON_RESULT_ITEM_CHANCE, resultItemChance);
    pJson.add(CrushingRecipeSerializer.JSON_KEY_SECONDARY_RESULT, Util.serializeItemStack(secondaryResultItem));
    pJson.addProperty(CrushingRecipeSerializer.JSON_SECONDARY_RESULT_ITEM_CHANCE, secondaryResultItemChance);
    pJson.addProperty(CrushingRecipeSerializer.JSON_PROCESSING_TIME, processingTime);
    pJson.addProperty(CrushingRecipeSerializer.JSON_GROUP, group);
    pJson.add(CrushingRecipeSerializer.JSON_INGREDIENT, ingredient.toJson());
  }
  
  @Override
  public @NotNull RecipeSerializer<?> getType() {
    return CrushingRecipeSerializer.INSTANCE;
  }
  
  @Nullable
  @Override
  public JsonObject serializeAdvancement() {
    return advancement.serializeToJson();
  }
  
}
