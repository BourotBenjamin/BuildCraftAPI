package buildcraft.api.statements;

import net.minecraft.client.renderer.FaceInfo;

public interface IActionInternalSided extends IAction {
    void actionActivate(FaceInfo side, IStatementContainer source, IStatementParameter[] parameters);
}
