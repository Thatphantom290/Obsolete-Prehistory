package com.barnacle.obsolete_prehistory.event;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.client.model.BanffiaModel;
import com.barnacle.obsolete_prehistory.client.model.PaleobalistumModel;
import com.barnacle.obsolete_prehistory.client.render.entity.BanffiaRenderer;
import com.barnacle.obsolete_prehistory.client.render.entity.PaleobalistumRenderer;
import com.barnacle.obsolete_prehistory.registry.OPEntities;
import com.barnacle.obsolete_prehistory.registry.OPModelLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = ObsoletePrehistory.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OPEntities.PALEOBALISTUM.get(), PaleobalistumRenderer::new);
        event.registerEntityRenderer(OPEntities.BANFFIA.get(), BanffiaRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OPModelLayers.PALEOBALISTUM, PaleobalistumModel::createBodyLayer);
        event.registerLayerDefinition(OPModelLayers.BABY_PALEOBALISTUM, PaleobalistumModel::createBabyBodyLayer);
        event.registerLayerDefinition(OPModelLayers.BANFFIA, BanffiaModel::createBodyLayer);
        event.registerLayerDefinition(OPModelLayers.BABY_BANFFIA, BanffiaModel::createBabyBodyLayer);
    }
}