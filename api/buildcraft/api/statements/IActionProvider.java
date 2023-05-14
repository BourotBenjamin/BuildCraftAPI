/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.statements;

import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface IActionProvider {
    void addInternalActions(Collection<IActionInternal> actions, IStatementContainer container);

    void addInternalSidedActions(Collection<IActionInternalSided> actions, IStatementContainer container, @Nonnull FaceInfo side);

    void addExternalActions(Collection<IActionExternal> actions, @Nonnull FaceInfo side, BlockEntity tile);
}
