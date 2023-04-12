package net.internalerror.betterfactories.data.recipes;

import lombok.Builder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@Builder
public class CrushingRecipeBuilder implements RecipeBuilder {
  
  private ItemStack resultItem;
  private float resultItemChance;
  private ItemStack secondaryResultItem;
  private float secondaryResultItemChance;
  private int processingTime;
  private Ingredient ingredient;
  private String group;
  private final Advancement.Builder advancement = Advancement.Builder.advancement();
  
  @Override
  public @NotNull RecipeBuilder unlockedBy(@NotNull String pCriterionName, @NotNull CriterionTriggerInstance pCriterionTrigger) {
    advancement.addCriterion(pCriterionName, pCriterionTrigger);
    return this;
  }
  
  @Override
  public @NotNull RecipeBuilder group(@Nullable String pGroupName) {
    this.group = pGroupName;
    return this;
  }
  
  
  @Override
  public @NotNull Item getResult() {
    return resultItem.getItem();
  }
  
  @Override
  public void save(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, @NotNull ResourceLocation pRecipeId) {
    if (advancement.getCriteria().isEmpty()) {
      throw new IllegalStateException("No way of optaining Recipe: " + pRecipeId);
    }
    advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
    
    pFinishedRecipeConsumer.accept(new FinishedCrushingRecipe(
        pRecipeId,
        resultItem,
        resultItemChance,
        secondaryResultItem,
        secondaryResultItemChance,
        processingTime,
        ingredient,
        group,
        advancement,
        getResourceLocation(pRecipeId)
    ));
  }
  
  private ResourceLocation getResourceLocation(ResourceLocation pRecipeId) {
    CreativeModeTab tab = getResult().getItemCategory();
    if (tab == null) {
      return new ResourceLocation(pRecipeId.getNamespace(), "recipes/failed/" + pRecipeId.getPath());
    }
    return new ResourceLocation(pRecipeId.getNamespace(), "recipes/" + getResult().getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath());
  }
  
}
