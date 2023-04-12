package net.internalerror.betterfactories.world.item.crafting;

import com.google.gson.JsonObject;
import net.internalerror.betterfactories.util.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
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
public class CrushingRecipeSerializer implements RecipeSerializer<CrushingRecipe> {
  
  public static final CrushingRecipeSerializer INSTANCE = new CrushingRecipeSerializer();
  
  public static final String JSON_RESULT_ITEM = "resultItem";
  public static final String JSON_RESULT_ITEM_COUNT = "resultItemCount";
  public static final String JSON_RESULT_ITEM_CHANCE = "resultItemChance";
  public static final String JSON_SECONDARY_RESULT_ITEM = "secondaryResultItem";
  public static final String JSON_SECONDARY_RESULT_ITEM_COUNT = "secondaryResultItemCount";
  public static final String JSON_SECONDARY_RESULT_ITEM_CHANCE = "secondaryResultItemChance";
  public static final String JSON_PROCESSING_TIME = "processingTime";
  public static final String JSON_GROUP = "group";
  public static final String JSON_INGREDIENT = "ingredient";
  public static final String JSON_KEY_RESULT = "result";
  public static final String JSON_KEY_SECONDARY_RESULT = "secondaryResult";
  
  private CrushingRecipeSerializer() {
    // private constructor
  }
  
  @Override
  public @NotNull CrushingRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {
    ItemStack result = Util.deserializeItemStack(GsonHelper.getAsJsonObject(pSerializedRecipe, JSON_KEY_RESULT));
    float resultItemChance = GsonHelper.getAsFloat(pSerializedRecipe, JSON_RESULT_ITEM_CHANCE);
    ItemStack secondaryResult = Util.deserializeItemStack(GsonHelper.getAsJsonObject(pSerializedRecipe, JSON_KEY_SECONDARY_RESULT));
    float secondaryResultItemChance = GsonHelper.getAsFloat(pSerializedRecipe, JSON_SECONDARY_RESULT_ITEM_CHANCE);
    int processingTime = GsonHelper.getAsInt(pSerializedRecipe, JSON_PROCESSING_TIME);
    String group = GsonHelper.getAsString(pSerializedRecipe, JSON_GROUP);
    Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, JSON_INGREDIENT));
    
    return new CrushingRecipe(pRecipeId, result, resultItemChance, secondaryResult, secondaryResultItemChance, processingTime, group, ingredient);
  }
  
  @Override
  public @Nullable CrushingRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, @NotNull FriendlyByteBuf pBuffer) {
    ItemStack resultItem = pBuffer.readItem();
    float resultItemChance = pBuffer.readFloat();
    ItemStack secondaryResultItem = pBuffer.readItem();
    float secondaryResultItemChance = pBuffer.readFloat();
    int processingTime = pBuffer.readInt();
    String group = pBuffer.readUtf();
    Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
    return new CrushingRecipe(pRecipeId, resultItem, resultItemChance, secondaryResultItem, secondaryResultItemChance, processingTime, group, ingredient);
  }
  
  @Override
  public void toNetwork(@NotNull FriendlyByteBuf pBuffer, @NotNull CrushingRecipe pRecipe) {
    pBuffer.writeItem(pRecipe.getResultItem());
    pBuffer.writeFloat(pRecipe.getResultItemChance());
    pBuffer.writeItem(pRecipe.getSecondaryResultItem());
    pBuffer.writeFloat(pRecipe.getSecondaryResultItemChance());
    pBuffer.writeInt(pRecipe.getProcessingTime());
    pBuffer.writeUtf(pRecipe.getGroup());
    pRecipe.getIngredient().toNetwork(pBuffer);
  }
}
