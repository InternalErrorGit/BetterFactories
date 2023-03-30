package net.internalerror.betterfactories.forge.client.model.generators;

import net.internalerror.betterfactories.util.Materials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static net.internalerror.betterfactories.BetterFactories.BETTER_FACTORIES;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
public final class BFBlockStateProvider extends BlockStateProvider {

    public BFBlockStateProvider(DataGenerator pDataGenerator, ExistingFileHelper pExistingFileHelper) {
        super(pDataGenerator, BETTER_FACTORIES, pExistingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (Materials material : Materials.values()) {
            material(material);
        }
    }


    private void material(Materials material) {
        Materials.Blocks blocks = material.getBlocks();
        simpleBlock(blocks.getDeepslateOre());
        simpleBlock(blocks.getOre());
        simpleBlock(blocks.getRawStorageBlock());
        simpleBlock(blocks.getStorageBlock());
    }
}
