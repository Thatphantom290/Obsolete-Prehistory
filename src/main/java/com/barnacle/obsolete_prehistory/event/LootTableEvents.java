package com.barnacle.obsolete_prehistory.event;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.registry.OPItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

@EventBusSubscriber(modid = ObsoletePrehistory.MOD_ID)
public class LootTableEvents {

    private static final ResourceLocation CLIFF_UNCOMMON_LOOT = ResourceLocation.fromNamespaceAndPath(
            "unusual_prehistory", "archaeology/fossil/cliff_fossil/uncommon"
    );

    private static final ResourceLocation CLIFF_RARE_LOOT = ResourceLocation.fromNamespaceAndPath(
            "unusual_prehistory", "archaeology/fossil/cliff_fossil/rare"
    );

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(CLIFF_UNCOMMON_LOOT)) {
            LootPool addonPool = LootPool.lootPool()
                    .add(LootItem.lootTableItem(OPItems.TANGERINE_FOSSIL.get()).setWeight(1))
                    .build();

            event.getTable().addPool(addonPool);
        } else if (event.getName().equals(CLIFF_RARE_LOOT)) {
            LootPool addonPool = LootPool.lootPool()
                    .add(LootItem.lootTableItem(OPItems.MUSACEAE_FOSSIL.get()).setWeight(1))
                    .build();

            event.getTable().addPool(addonPool);
        }
    }
}