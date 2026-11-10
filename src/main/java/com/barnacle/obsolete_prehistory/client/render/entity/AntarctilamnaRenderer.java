package com.barnacle.obsolete_prehistory.client.render.entity;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.client.model.AntarctilamnaModel;
import com.barnacle.obsolete_prehistory.client.render.entity.layer.AntarctilamnaGlowLayer;
import com.barnacle.obsolete_prehistory.entity.Antarctilamna;
import com.barnacle.obsolete_prehistory.registry.OPModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AntarctilamnaRenderer extends MobRenderer<Antarctilamna, AntarctilamnaModel> {

    private static final ResourceLocation TEXTURE_LOCATION = ObsoletePrehistory.location("textures/entity/antarctilamna/antarctilamna.png");
    private static final ResourceLocation BABY_TEXTURE_LOCATION = ObsoletePrehistory.location("textures/entity/antarctilamna/baby_antarctilamna.png");

    private final AntarctilamnaModel adultModel;
    private final AntarctilamnaModel babyModel;

    public AntarctilamnaRenderer(EntityRendererProvider.Context context) {
        super(context, new AntarctilamnaModel(context.bakeLayer(OPModelLayers.ANTARCTILAMNA)), 0.8F);
        this.adultModel = new AntarctilamnaModel(context.bakeLayer(OPModelLayers.ANTARCTILAMNA));
        this.babyModel = new AntarctilamnaModel(context.bakeLayer(OPModelLayers.BABY_ANTARCTILAMNA));

        this.addLayer(new AntarctilamnaGlowLayer(this));
    }

    @Override
    public void render(Antarctilamna entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        this.model = entity.isBaby() ? this.babyModel : this.adultModel;
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    protected void setupRotations(Antarctilamna entity, PoseStack poseStack, float bob, float yBodyRot, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot + 0.0F, partialTicks, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Antarctilamna entity) {
        return entity.isBaby() ? BABY_TEXTURE_LOCATION : TEXTURE_LOCATION;
    }
}