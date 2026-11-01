package com.barnacle.obsolete_prehistory.client.render.entity;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.client.model.PaleobalistumModel;
import com.barnacle.obsolete_prehistory.entity.Paleobalistum;
import com.barnacle.obsolete_prehistory.registry.OPModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PaleobalistumRenderer extends MobRenderer<Paleobalistum, PaleobalistumModel> {

    private static final ResourceLocation TEXTURE_LOCATION = ObsoletePrehistory.location("textures/entity/paleobalistum/paleobalistum.png");
    private static final ResourceLocation BABY_TEXTURE_LOCATION = ObsoletePrehistory.location("textures/entity/paleobalistum/baby_paleobalistum.png");

    private final PaleobalistumModel adultModel;
    private final PaleobalistumModel babyModel;

    public PaleobalistumRenderer(EntityRendererProvider.Context context) {
        super(context, new PaleobalistumModel(context.bakeLayer(OPModelLayers.PALEOBALISTUM)), 0.5F);
        this.adultModel = new PaleobalistumModel(context.bakeLayer(OPModelLayers.PALEOBALISTUM));
        this.babyModel = new PaleobalistumModel(context.bakeLayer(OPModelLayers.BABY_PALEOBALISTUM));
    }

    @Override
    public void render(Paleobalistum entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        this.model = entity.isBaby() ? this.babyModel : this.adultModel;
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    protected void setupRotations(Paleobalistum entity, PoseStack poseStack, float bob, float yBodyRot, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot + 0.0F, partialTicks, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Paleobalistum entity) {
        return entity.isBaby() ? BABY_TEXTURE_LOCATION : TEXTURE_LOCATION;
    }
}
