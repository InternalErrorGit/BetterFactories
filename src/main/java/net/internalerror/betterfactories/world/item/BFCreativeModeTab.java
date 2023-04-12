package net.internalerror.betterfactories.world.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;
import static net.minecraft.world.item.Items.HOPPER;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
public final class BFCreativeModeTab extends CreativeModeTab {

    public static final BFCreativeModeTab TAB = new BFCreativeModeTab(MOD_ID);

    public BFCreativeModeTab(String pName) {
        super(CreativeModeTab.TABS.length, pName);
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(HOPPER.asItem());
    }


}
