package com.barnacle.obsolete_prehistory.client.render.entity.layer;

import com.barnacle.obsolete_prehistory.ObsoletePrehistory;
import com.barnacle.obsolete_prehistory.client.model.AntarctilamnaModel;
import com.barnacle.obsolete_prehistory.entity.Antarctilamna;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.platypushasnohat.sinew.client.render.SinewRenderTypes;
import com.platypushasnohat.sinew.utils.SinewColorUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class AntarctilamnaGlowLayer extends RenderLayer<Antarctilamna, AntarctilamnaModel> {
    private static final RenderType ADULT_GLOW_LOCATION = SinewRenderTypes.getEyesAlphaEnabled(ObsoletePrehistory.location("textures/entity/antarctilamna/antarctilamna_glow.png"));
    private static final RenderType BABY_GLOW_LOCATION = SinewRenderTypes.getEyesAlphaEnabled(ObsoletePrehistory.location("textures/entity/antarctilamna/baby_antarctilamna_glow.png"));

    public AntarctilamnaGlowLayer(RenderLayerParent<Antarctilamna, AntarctilamnaModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Antarctilamna antarctilamna, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (antarctilamna.isInvisible()) {
            return;
        }

        RenderType renderType = antarctilamna.isBaby() ? BABY_GLOW_LOCATION : ADULT_GLOW_LOCATION;

        VertexConsumer consumer = buffer.getBuffer(renderType);
        this.getParentModel().renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(antarctilamna, 0.0F), SinewColorUtils.packColor(1.0F, 1.0F, 1.0F, 0.7F));
    }
}