package com.barnacle.obsolete_prehistory.registry;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class OPModelLayers {

    public static final ModelLayerLocation PALEOBALISTUM = register("paleobalistum");
    public static final ModelLayerLocation BABY_PALEOBALISTUM = register("baby_paleobalistum");

    public static final ModelLayerLocation BANFFIA = register("banffia");
    public static final ModelLayerLocation BABY_BANFFIA = register("baby_banffia");

    public static final ModelLayerLocation ANTARCTILAMNA = register("antarctilamna");
    public static final ModelLayerLocation BABY_ANTARCTILAMNA = register("baby_antarctilamna");

    private static ModelLayerLocation register(String id) {
        return new ModelLayerLocation(ObsoletePrehistory.location(id), "main");
    }
}
