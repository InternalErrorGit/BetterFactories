package net.internalerror.betterfactories.data.recipes;

import net.internalerror.betterfactories.util.Materials;
import net.internalerror.betterfactories.util.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BFMaterialRecipeProvider extends RecipeProvider {
  
  public BFMaterialRecipeProvider(DataGenerator pDataGenerator) {
    super(pDataGenerator);
  }
  
  @Override
  protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    
    for (Materials material : Materials.values()) {
      material(material, pFinishedRecipeConsumer);
    }
    
  }
  
  private static final String SMELTING = "smelting";
  private static final String COMBINING = "combining";
  private static final String CRUSHING = "crushing";
  private static final String SPLITTING = "splitting";
  
  private void material(Materials pMaterial, Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
    Materials.Items items = pMaterial.getItems();
    Materials.Items.Tags tags = items.getTags();
    
    Util.smeltingRecipe(pFinishedRecipeConsumer, tags.getDust(), items.getIngot(), 0.7f, 200, SMELTING);
    Util.smeltingRecipe(pFinishedRecipeConsumer, tags.getOres(), items.getIngot(), 0.7f, 200, SMELTING);
    
    Util.combiningRecipe(pFinishedRecipeConsumer, tags.getIngot(), items.getStorageBlock(), COMBINING);
    Util.combiningRecipe(pFinishedRecipeConsumer, tags.getNugget(), items.getIngot(), COMBINING);
    Util.combiningRecipe(pFinishedRecipeConsumer, tags.getRaw(), items.getRawStorageBlock(), COMBINING);
    
    Util.splittingRecipe(pFinishedRecipeConsumer, tags.getStorageBlock(), items.getIngot(), SPLITTING);
    Util.splittingRecipe(pFinishedRecipeConsumer, tags.getIngot(), items.getNugget(), SPLITTING);
    Util.splittingRecipe(pFinishedRecipeConsumer, tags.getRawStorageBlock(), items.getRaw(), SPLITTING);
    
    Util.crushingRecipe(pFinishedRecipeConsumer, tags.getRaw(), items.getClump(), 1, 1.0f, items.getClump(), 1, 0.5f, 200, CRUSHING);
    Util.crushingRecipe(pFinishedRecipeConsumer, tags.getOres(), items.getClump(), 1, 1.0f, items.getClump(), 1, 0.5f, 200, CRUSHING);
    
  }
  
}
