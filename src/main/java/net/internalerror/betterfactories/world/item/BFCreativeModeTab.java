package net.internalerror.betterfactories.world.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.internalerror.betterfactories.BetterFactories.BETTER_FACTORIES;
import static net.minecraft.world.item.Items.HOPPER;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
public final class BFCreativeModeTab extends CreativeModeTab {

    public static final BFCreativeModeTab TAB = new BFCreativeModeTab(BETTER_FACTORIES);

    public BFCreativeModeTab(String pName) {
        super(CreativeModeTab.TABS.length, pName);
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(HOPPER.asItem());
    }


}
