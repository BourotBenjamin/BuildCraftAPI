/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.statements;

import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface ITriggerExternal extends ITrigger {

    boolean isTriggerActive(BlockEntity target, FaceInfo side, IStatementContainer source, IStatementParameter[] parameters);

}
