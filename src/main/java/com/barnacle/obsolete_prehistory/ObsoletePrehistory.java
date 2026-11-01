package com.barnacle.obsolete_prehistory;

import com.barl_inc.unusual_prehistory.registry.UP2CreativeTabs;
import com.barnacle.obsolete_prehistory.registry.OPBlocks;
import com.barnacle.obsolete_prehistory.registry.OPEntities;
import com.barnacle.obsolete_prehistory.registry.OPItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

import java.util.Locale;

@Mod(ObsoletePrehistory.MOD_ID)
public class ObsoletePrehistory {
    public static final String MOD_ID = "obsolete_prehistory";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path.toLowerCase(Locale.ROOT));
    }

    public ObsoletePrehistory(IEventBus modEventBus, ModContainer modContainer) {
        OPItems.ITEMS.register(modEventBus);
        OPBlocks.BLOCKS.register(modEventBus);
        OPEntities.ENTITY_TYPES.register(modEventBus);


        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == UP2CreativeTabs.UNUSUAL_PREHISTORY_TAB.getKey()) {
            event.accept(OPItems.TANGERINE_FOSSIL);
            event.accept(OPItems.PALEOBALISTUM_BUCKET);
            event.accept(OPItems.PALEOBALISTUM_SPAWN_EGG);
            event.accept(OPBlocks.PALEOBALISTUM_EGGS);
            event.accept(OPItems.MUSACEAE_FOSSIL);
            event.accept(OPItems.BANFFIA_BUCKET);
            event.accept(OPItems.BANFFIA_SPAWN_EGG);
            event.accept(OPBlocks.BANFFIA_EGGS);
        }
    }
}
