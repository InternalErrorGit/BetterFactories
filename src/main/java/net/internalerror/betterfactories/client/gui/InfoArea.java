package net.internalerror.betterfactories.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import lombok.AllArgsConstructor;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.Rect2i;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@AllArgsConstructor
public abstract class InfoArea extends GuiComponent {
  
  protected final Rect2i area;
  
  public abstract void render(PoseStack pTransform);
  
  public abstract void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY, AbstractContainerScreen<?> pScreen, int x, int y);
}
