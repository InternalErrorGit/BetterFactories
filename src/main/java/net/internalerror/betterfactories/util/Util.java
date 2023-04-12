package net.internalerror.betterfactories.util;

import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.data.recipes.CrushingRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Slf4j
@UtilityClass
public final class Util {
  
  public static boolean isMouseOver(double x, double y, int mx, int my, int w, int h) {
    return true; // (mx >= x && mx <= x + w) && (my >= y && my <= y + h);
  }
  
  public static SimpleContainer copyInventory(ItemStackHandler pItemStackHandler) {
    SimpleContainer inventory = new SimpleContainer(pItemStackHandler.getSlots());
    for (int i = 0; i < pItemStackHandler.getSlots(); i++) {
      inventory.setItem(i, pItemStackHandler.getStackInSlot(i));
    }
    return inventory;
  }
  
  public static String getItemKey(Item pItem) {
    return getItemResourceLocation(pItem).getPath();
  }
  
  public static ResourceLocation getItemResourceLocation(Item pItem) {
    ResourceLocation loc = ForgeRegistries.ITEMS.getKey(pItem);
    if (loc == null) {
      throw new NullPointerException(String.format("Item not present %s", pItem));
    }
    return loc;
  }
  
  public static ResourceLocation recipeResourceLocation(String pGroup, TagKey<Item> pIngredient, ItemLike pResult) {
    return new ResourceLocation(MOD_ID, String.format("%s_%s_from_%s", pGroup, getItemKey(pResult.asItem()), pIngredient.location().getPath()).replace('/', '_'));
  }
  
  private static final String JSON_KEY_COUNT = "count";
  private static final String JSON_KEY_ITEM = "item";
  
  public static JsonObject serializeItemStack(ItemStack pItemStack) {
    JsonObject itemStack = new JsonObject();
    itemStack.addProperty(JSON_KEY_ITEM, Util.getItemResourceLocation(pItemStack.getItem()).toString());
    if (pItemStack.getCount() > 1) {
      itemStack.addProperty(JSON_KEY_COUNT, pItemStack.getCount());
    }
    return itemStack;
  }
  
  public static ItemStack deserializeItemStack(JsonObject pJsonObject) {
    Item item = GsonHelper.getAsItem(pJsonObject, JSON_KEY_ITEM);
    if (pJsonObject.has(JSON_KEY_COUNT)) {
      return new ItemStack(item, GsonHelper.getAsInt(pJsonObject, JSON_KEY_COUNT));
    }
    return new ItemStack(item);
  }
  
  public static ResourceLocation recipeResourceLocation(String pGroup, ItemLike pIngredient, ItemLike pResult) {
    return new ResourceLocation(MOD_ID, String.format("%s_%s_from_%s", pGroup, getItemKey(pResult.asItem()), getItemKey(pIngredient.asItem())).replace('/', '_'));
  }
  
  private static final String HAS_ITEM = "has_item";
  
  
  public static void smeltingRecipe(Consumer<FinishedRecipe> pConsumer, ItemLike pIngredient, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
    SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(pIngredient),
            pResult,
            pExperience,
            pCookingTime
        ).group(pGroup)
        .unlockedBy(HAS_ITEM, InventoryChangeTrigger.TriggerInstance.hasItems(pResult))
        .save(pConsumer, recipeResourceLocation(pGroup, pIngredient, pResult));
  }
  
  public static void smeltingRecipe(Consumer<FinishedRecipe> pConsumer, TagKey<Item> pIngredient, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
    SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(pIngredient),
            pResult,
            pExperience,
            pCookingTime
        ).group(pGroup)
        .unlockedBy(HAS_ITEM, InventoryChangeTrigger.TriggerInstance.hasItems(pResult))
        .save(pConsumer, recipeResourceLocation(pGroup, pIngredient, pResult));
  }
  
  public static void combiningRecipe(Consumer<FinishedRecipe> pConsumer, TagKey<Item> pIngredient, ItemLike pResult, String pGroup) {
    ShapedRecipeBuilder.shaped(pResult)
        .pattern("###")
        .pattern("###")
        .pattern("###")
        .define('#', pIngredient)
        .group(pGroup)
        .unlockedBy(HAS_ITEM, InventoryChangeTrigger.TriggerInstance.hasItems(pResult))
        .save(pConsumer, recipeResourceLocation(pGroup, pIngredient, pResult));
  }
  
  public static void splittingRecipe(Consumer<FinishedRecipe> pConsumer, TagKey<Item> pIngredient, ItemLike pResult, String pGroup) {
    ShapelessRecipeBuilder.shapeless(pResult, 9)
        .requires(pIngredient)
        .group(pGroup)
        .unlockedBy(HAS_ITEM, InventoryChangeTrigger.TriggerInstance.hasItems(pResult))
        .save(pConsumer, recipeResourceLocation(pGroup, pIngredient, pResult));
  }
  
  public static void crushingRecipe(Consumer<FinishedRecipe> pConsumer, TagKey<Item> pIngredient, Item pResult, int pResultItemCount, float pResultItemChance, ItemLike pSecondaryResult, int pSecondaryResultCount, float pSecondaryResultChance, int pProcessingTime, String pGroup) {
    CrushingRecipeBuilder.builder()
        .ingredient(Ingredient.of(pIngredient))
        .resultItem(new ItemStack(pResult, pResultItemCount))
        .resultItemChance(pResultItemChance)
        .secondaryResultItem(new ItemStack(pSecondaryResult, pSecondaryResultCount))
        .secondaryResultItemChance(pSecondaryResultChance)
        .processingTime(pProcessingTime)
        .build()
        .group(pGroup)
        .unlockedBy(HAS_ITEM, InventoryChangeTrigger.TriggerInstance.hasItems(pResult))
        .save(pConsumer, recipeResourceLocation(pGroup, pIngredient, pResult));
  }
}
