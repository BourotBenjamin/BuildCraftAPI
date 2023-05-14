package buildcraft.api.mj;

import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.world.level.Level;
import org.joml.Vector3d;

/** Various effects for showing power loss visibly, and for large amounts of power, causes some damage to nearby
 * entities. */
public interface IMjEffectManager {
    void createPowerLossEffect(Level world, Vector3d center, long microJoulesLost);

    void createPowerLossEffect(Level world, Vector3d center, FaceInfo direction, long microJoulesLost);

    void createPowerLossEffect(Level world, Vector3d center, Vector3d direction, long microJoulesLost);
}
