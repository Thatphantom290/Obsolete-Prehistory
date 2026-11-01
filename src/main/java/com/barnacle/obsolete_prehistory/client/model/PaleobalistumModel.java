package com.barnacle.obsolete_prehistory.client.model;

import com.barnacle.obsolete_prehistory.client.model.animation.PaleobalistumAnimations;
import com.barnacle.obsolete_prehistory.entity.Paleobalistum;
import com.platypushasnohat.sinew.client.model.entity.SinewEntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class PaleobalistumModel extends SinewEntityModel<Paleobalistum> {

    private final ModelPart root;
    private final ModelPart swim_control;
    private final ModelPart body;

    public PaleobalistumModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root.getChild("root");
        this.swim_control = this.root.getChild("swim_control");
        this.body = this.swim_control.getChild("body");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    protected void setupAnimations(Paleobalistum entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        this.animateWalkSmooth(entity.swimAnimationState, PaleobalistumAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
        this.animateIdleSmooth(entity.idleAnimationState, PaleobalistumAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
        this.animateSmooth(entity.flopAnimationState, PaleobalistumAnimations.FLOP, ageInTicks, partialTicks);
        this.swim_control.xRot += entity.swimPitch.getPitch(partialTicks) * Mth.DEG_TO_RAD;
        this.swim_control.zRot += -entity.swimPitch.getRoll(partialTicks) * Mth.DEG_TO_RAD;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -11.0F, -9.0F, 6.0F, 20.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(24, 37).addBox(-3.0F, -8.0F, -12.0F, 6.0F, 15.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(46, 29).addBox(-1.0F, 4.0F, -14.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition dorsal_fin = body.addOrReplaceChild("dorsal_fin", CubeListBuilder.create().texOffs(46, 0).addBox(0.0F, -7.0F, -3.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 5.0F));

        PartDefinition upper_dorsal_fin = body.addOrReplaceChild("upper_dorsal_fin", CubeListBuilder.create().texOffs(42, 37).addBox(0.0F, 0.0F, -3.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 5.0F));

        PartDefinition left_down_fin = body.addOrReplaceChild("left_down_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, -5.0F));

        PartDefinition rightdownfin_r1 = left_down_fin.addOrReplaceChild("rightdownfin_r1", CubeListBuilder.create().texOffs(46, 50).addBox(-2.0F, -6.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition leftdownfin_r1 = left_down_fin.addOrReplaceChild("leftdownfin_r1", CubeListBuilder.create().texOffs(42, 50).addBox(2.0F, -6.0F, 0.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offset(3.0F, -1.0F, -4.0F));

        PartDefinition cube_r1 = left_fin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 13).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

        PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(-3.0F, -1.0F, -4.0F));

        PartDefinition cube_r2 = right_fin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 21).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(8, 45).addBox(0.0F, -3.0F, 0.0F, 0.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 8.0F));

        PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(0, -1).addBox(0.0F, -5.0F, 0.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 4.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createBabyBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(18, 19).addBox(0.0F, 3.0F, -2.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 16).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition dorsal_fin = body.addOrReplaceChild("dorsal_fin", CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, 0.0F, -2.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 2.0F));

        PartDefinition upper_dorsal_fin = body.addOrReplaceChild("upper_dorsal_fin", CubeListBuilder.create().texOffs(10, 14).addBox(0.0F, -3.0F, -2.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 2.0F));

        PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(18, 6).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -1.0F, 0.0F, -0.48F, 0.0F));

        PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(18, 11).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -1.0F, 0.0F, 0.48F, 0.0F));

        PartDefinition tail = swim_control.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
}
