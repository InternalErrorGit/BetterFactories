package net.internalerror.betterfactories.forge.client.model.generators;

import net.internalerror.betterfactories.util.Util;
import net.internalerror.betterfactories.util.Materials;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import static net.internalerror.betterfactories.BetterFactories.BETTER_FACTORIES;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
public final class BFItemModelProvider extends ItemModelProvider {
    public BFItemModelProvider(DataGenerator pDataGenerator, ExistingFileHelper pExistingFileHelper) {
        super(pDataGenerator, BETTER_FACTORIES, pExistingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (Materials material : Materials.values()) {
            material(material);
        }
    }

    private void material(Materials pMaterial) {
        Materials.Items items = pMaterial.getItems();
        item(items.getClump());
        item(items.getCrystal());
        item(items.getDirtyDissolvedBucket());
        item(items.getDissolvedBucket());
        item(items.getDust());
        item(items.getIngot());
        item(items.getNugget());
        item(items.getPlate());
        item(items.getRaw());
        item(items.getRod());
        item(items.getShard());
        item(items.getWire());
        blockItem(items.getDeepslateOre());
        blockItem(items.getOre());
        blockItem(items.getRawStorageBlock());
        blockItem(items.getStorageBlock());
    }


    private void blockItem(Item pItem) {
        blockItem(Util.getItemPath(pItem));
    }

    private void blockItem(String pPath) {
        withExistingParent(pPath, modLoc(String.format("block/%s", pPath)));
    }

    private void item(Item pItem) {
        item(Util.getItemPath(pItem));
    }

    private final ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

    private void item(String pPath) {
        getBuilder(pPath).parent(itemGenerated).texture("layer0", String.format("item/%s", pPath));
    }

}
