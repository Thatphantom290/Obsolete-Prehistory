package com.barnacle.obsolete_prehistory.client.render.entity;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.client.model.BanffiaModel;
import com.barnacle.obsolete_prehistory.entity.Banffia;
import com.barnacle.obsolete_prehistory.registry.OPModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BanffiaRenderer extends MobRenderer<Banffia, BanffiaModel> {

    private static final ResourceLocation TEXTURE_LOCATION = ObsoletePrehistory.location("textures/entity/banffia/banffia.png");
    private static final ResourceLocation BABY_TEXTURE_LOCATION = ObsoletePrehistory.location("textures/entity/banffia/baby_banffia.png");

    private final BanffiaModel adultModel;
    private final BanffiaModel babyModel;

    public BanffiaRenderer(EntityRendererProvider.Context context) {
        super(context, new BanffiaModel(context.bakeLayer(OPModelLayers.BANFFIA)), 0.5F);
        this.adultModel = new BanffiaModel(context.bakeLayer(OPModelLayers.BANFFIA));
        this.babyModel = new BanffiaModel(context.bakeLayer(OPModelLayers.BABY_BANFFIA));
    }

    @Override
    public void render(Banffia entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        this.model = entity.isBaby() ? this.babyModel : this.adultModel;
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    protected void setupRotations(Banffia entity, PoseStack poseStack, float bob, float yBodyRot, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot + 0.0F, partialTicks, scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Banffia entity) {
        return entity.isBaby() ? BABY_TEXTURE_LOCATION : TEXTURE_LOCATION;
    }
}
