/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import net.minecraft.util.math.Vec3d;
import com.google.common.base.Predicates;
import net.minecraft.util.EntitySelectors;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.entity.Entity;

public class RayCastUtils
{
    public static Entity rayCast(final double n, final float n2, final float n3) {
        final Vec3d positionEyes = Wrapper.INSTANCE.player().getPositionEyes(1.0f);
        if (n > 3.0) {}
        final Vec3d vectorForRotation = getVectorForRotation(n3, n2);
        final Vec3d addVector = positionEyes.addVector(vectorForRotation.x * n, vectorForRotation.y * n, vectorForRotation.z * n);
        Entity entity = null;
        final float n4 = 1.0f;
        final List entitiesInAABBexcluding = Wrapper.INSTANCE.world().getEntitiesInAABBexcluding(Wrapper.INSTANCE.mc().getRenderViewEntity(), Wrapper.INSTANCE.mc().getRenderViewEntity().getEntityBoundingBox().offset(vectorForRotation.x * n, vectorForRotation.y * n, vectorForRotation.z * n).expand((double)n4, (double)n4, (double)n4), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::func_70067_L));
        double n5 = n;
        for (int i = 0; i < entitiesInAABBexcluding.size(); ++i) {
            final Entity entity2 = entitiesInAABBexcluding.get(i);
            final float collisionBorderSize = entity2.getCollisionBorderSize();
            final AxisAlignedBB expand = entity2.getEntityBoundingBox().expand((double)collisionBorderSize, (double)collisionBorderSize, (double)collisionBorderSize);
            final RayTraceResult calculateIntercept = expand.calculateIntercept(positionEyes, addVector);
            if (expand.contains(positionEyes)) {
                if (n5 >= 0.0) {
                    entity = entity2;
                    final Vec3d vec3d = (calculateIntercept == null) ? positionEyes : calculateIntercept.hitVec;
                    n5 = 0.0;
                }
            }
            else if (calculateIntercept != null) {
                final double distanceTo = positionEyes.distanceTo(calculateIntercept.hitVec);
                if (distanceTo >= n5) {
                    if (n5 != 0.0) {
                        continue;
                    }
                }
                final boolean b = false;
                if (entity2 == Wrapper.INSTANCE.mc().getRenderViewEntity().getRidingEntity() && !b) {
                    if (n5 == 0.0) {
                        entity = entity2;
                        final Vec3d hitVec = calculateIntercept.hitVec;
                    }
                }
                else {
                    entity = entity2;
                    final Vec3d hitVec2 = calculateIntercept.hitVec;
                    n5 = distanceTo;
                }
            }
        }
        return entity;
    }
    
    public static Vec3d getVectorForRotation(final float n, final float n2) {
        final float cos = MathHelper.cos(-n2 * 0.017453292f - 3.1415927f);
        final float sin = MathHelper.sin(-n2 * 0.017453292f - 3.1415927f);
        final float n3 = -MathHelper.cos(-n * 0.017453292f);
        return new Vec3d((double)(sin * n3), (double)MathHelper.sin(-n * 0.017453292f), (double)(cos * n3));
    }
}
