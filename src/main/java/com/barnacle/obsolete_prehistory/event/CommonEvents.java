package com.barnacle.obsolete_prehistory.event;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.entity.Banffia;
import com.barnacle.obsolete_prehistory.entity.Paleobalistum;
import com.barnacle.obsolete_prehistory.registry.OPEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = ObsoletePrehistory.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(OPEntities.PALEOBALISTUM.get(), Paleobalistum.registerAttributes().build());
        event.put(OPEntities.BANFFIA.get(), Banffia.registerAttributes().build());
    }
}