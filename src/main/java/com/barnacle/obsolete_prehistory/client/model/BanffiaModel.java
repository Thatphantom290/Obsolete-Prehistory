package com.barnacle.obsolete_prehistory.client.model;

import com.barnacle.obsolete_prehistory.client.model.animation.BanffiaAnimations;
import com.barnacle.obsolete_prehistory.entity.Banffia;
import com.barnacle.obsolete_prehistory.entity.Paleobalistum;
import com.platypushasnohat.sinew.client.model.entity.SinewEntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class BanffiaModel extends SinewEntityModel<Banffia> {

    private final ModelPart root;
    private final ModelPart swim_control;
    private final ModelPart body;

    public BanffiaModel(ModelPart root) {
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
    protected void setupAnimations(Banffia entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        this.animateWalkSmooth(entity.swimAnimationState, BanffiaAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
        this.animateIdleSmooth(entity.idleAnimationState, BanffiaAnimations.SWIM, ageInTicks, partialTicks, limbSwingAmount);
        this.animateSmooth(entity.flopAnimationState, BanffiaAnimations.FLOP, ageInTicks, partialTicks);
        this.swim_control.xRot += entity.swimPitch.getPitch(partialTicks) * Mth.DEG_TO_RAD;
        this.swim_control.zRot += -entity.swimPitch.getRoll(partialTicks) * Mth.DEG_TO_RAD;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -13.0F, 8.0F, 4.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(-2, 17).addBox(4.0F, 0.0F, -9.0F, 4.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(-10, 17).addBox(-8.0F, 0.0F, -9.0F, 4.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 7.0F));

        PartDefinition tail = swim_control.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 7).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 7.0F));

        return LayerDefinition.create(meshdefinition, 48, 33);
    }

    public static LayerDefinition createBabyBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -6.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

        PartDefinition tail = swim_control.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

        return LayerDefinition.create(meshdefinition, 21, 18);
    }
}
