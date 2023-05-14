package buildcraft.api.statements;

import net.minecraft.client.renderer.FaceInfo;

/** This interface can be used by tiles to override external trigger behaviour.
 *
 * Please use wisely. */
public interface ITriggerExternalOverride {
    enum Result {
        TRUE,
        FALSE,
        IGNORE
    }

    Result override(FaceInfo side, IStatementContainer source, ITriggerExternal trigger, IStatementParameter[] parameters);
}
