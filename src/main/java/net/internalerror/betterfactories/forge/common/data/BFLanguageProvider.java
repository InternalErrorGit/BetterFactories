package net.internalerror.betterfactories.forge.common.data;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.betterfactories.forge.registry.BFBlocks;
import net.internalerror.betterfactories.forge.registry.BFItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static net.internalerror.betterfactories.BetterFactories.MOD_ID;

@Slf4j
public class BFLanguageProvider extends LanguageProvider {
    public static final String UNTRANSLATED = "Untranslated: %s";
    private static final File file = new File("../src/main/resources/en_us.json");
    private static JsonObject jsonObject;

    static {
        try {
            jsonObject = GsonHelper.parse(new FileReader(file));
        } catch (FileNotFoundException e) {
            logger.error("Cannot find original file {}", file.getAbsolutePath());
            jsonObject = null;
        }
    }

    public BFLanguageProvider(DataGenerator gen) {
        super(gen, MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (RegistryObject<Item> itemRegistryObject : BFItems.list) {
            addItem(itemRegistryObject);
        }

        for (RegistryObject<Block> blockRegistryObject : BFBlocks.list) {
            addBlock(blockRegistryObject);
        }
    }

    private void addBlock(RegistryObject<Block> pKey) {
        add(pKey.get(), getValue(pKey.get().getDescriptionId()));
    }

    private String getValue(String descId) {
        if (jsonObject == null) {
            return String.format(UNTRANSLATED, descId);
        }
        return GsonHelper.getAsString(jsonObject, descId, String.format(UNTRANSLATED, descId));
    }

    public void addItem(RegistryObject<Item> pKey) {
        if (pKey.get() instanceof BlockItem) {
            return;
        }
        add(pKey.get(), getValue(pKey.get().getDescriptionId()));
    }
}
