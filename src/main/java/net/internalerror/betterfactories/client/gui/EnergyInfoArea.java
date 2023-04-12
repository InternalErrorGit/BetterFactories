package net.internalerror.betterfactories.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.internalerror.betterfactories.forge.energy.BFEnergyStorage;
import net.internalerror.betterfactories.util.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

public class EnergyInfoArea extends InfoArea {
  
  private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/energy_info_area.png");
  
  public static final int DEFAULT_WIDTH = 16;
  public static final int DEFAULT_HEIGHT = 52;
  
  private final BFEnergyStorage energyStorage;
  
  public EnergyInfoArea(BFEnergyStorage pEnergyStorage) {
    this(0, 0, pEnergyStorage);
  }
  
  public EnergyInfoArea(int pXMin, int pYMin, BFEnergyStorage pEnergyStorage) {
    this(pXMin, pYMin, pEnergyStorage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }
  
  public EnergyInfoArea(int pXMin, int pYMin, BFEnergyStorage pEnergyStorage, int pWidth, int pHeight) {
    super(new Rect2i(pXMin, pYMin, pWidth, pHeight));
    energyStorage = pEnergyStorage;
  }
  
  public int getWidth(){
    return area.getWidth();
  }
  
  public int getHeight(){
    return area.getHeight();
  }
  
  public List<Component> getTooltips() {
    return List.of(Component.literal(String.format("%s/%s FE", getEnergyStored(), getMaxEnergyStored())));
  }
  
  private int getMaxEnergyStored() {
    return energyStorage.getMaxEnergyStored();
  }
  
  private int getEnergyStored() {
    return energyStorage.getEnergyStored();
  }
  
  @Override
  public void render(PoseStack pTransform) {
    final int height = area.getHeight();
    
    int stored = height * (getEnergyStored() / getMaxEnergyStored());
    
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    RenderSystem.setShaderTexture(0, TEXTURE);
    
    blit(pTransform, area.getX(), area.getY() + height - stored, 176, 14, area.getWidth(), stored);
  }
  
  @Override
  public void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY, AbstractContainerScreen<?> pScreen, int x, int y) {
    pScreen.renderTooltip(pPoseStack, getTooltips(), Optional.empty(), pMouseX - x, pMouseY - y);
  }
}
