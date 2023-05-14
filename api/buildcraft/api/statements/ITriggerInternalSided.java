package buildcraft.api.statements;

import net.minecraft.client.renderer.FaceInfo;

public interface ITriggerInternalSided extends ITrigger {
    boolean isTriggerActive(FaceInfo side, IStatementContainer source, IStatementParameter[] parameters);
}
