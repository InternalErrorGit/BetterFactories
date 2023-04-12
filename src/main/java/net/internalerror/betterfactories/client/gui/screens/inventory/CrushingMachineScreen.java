package net.internalerror.betterfactories.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.internalerror.betterfactories.client.gui.EnergyInfoArea;
import net.internalerror.betterfactories.util.Util;
import net.internalerror.betterfactories.world.inventory.CrushingMachineMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

public final class CrushingMachineScreen extends AbstractContainerScreen<CrushingMachineMenu> {
  
  private static final ResourceLocation GUI = new ResourceLocation(MOD_ID, "textures/gui/crushing_machine_gui.png");
  private static final ResourceLocation PROGRESS_ARROW = new ResourceLocation(MOD_ID, "textures/gui/progress_arrow_indicator.png");
  
  private EnergyInfoArea energyInfoArea;
  
  public CrushingMachineScreen(CrushingMachineMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
    super(pMenu, pPlayerInventory, pTitle);
    imageHeight = 170;
  }
  
  @Override
  protected void init() {
    super.init();
    energyInfoArea = new EnergyInfoArea(getX() + 152, getY() + 19, menu.getBlockEntity().getEnergyStorage());
  }
  
  @Override
  protected void renderLabels(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY) {
    
    if (Util.isMouseOver(152, 192, pMouseX, pMouseY, energyInfoArea.getWidth(), energyInfoArea.getHeight())) {
      energyInfoArea.renderLabels(pPoseStack, pMouseX, pMouseY, this, getX(), getY());
    }
    
    super.renderLabels(pPoseStack, pMouseX, pMouseY);
  }
  
  private int getX() {
    return (width - imageWidth) / 2;
  }
  
  private int getY() {
    return (height - imageHeight) / 2;
  }
  
  @Override
  protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.setShaderTexture(0, GUI);
    
    blit(pPoseStack, getX(), getY(), 0, 0, imageWidth, imageHeight);
    
    energyInfoArea.render(pPoseStack);
    renderProressArrow(pPoseStack);
  }
  
  @Override
  public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
    renderBackground(pPoseStack);
    super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    renderTooltip(pPoseStack, pMouseX, pMouseY);
  }
  
  private void renderProressArrow(PoseStack pPoseStack) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.setShaderTexture(0, PROGRESS_ARROW);
    
    blit(pPoseStack, getX(), getY(), 30, 20, menu.getScaledProgress(), 14);
  }
  
  
}
