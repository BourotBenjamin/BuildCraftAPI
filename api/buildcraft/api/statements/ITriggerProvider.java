/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.statements;

import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface ITriggerProvider {
    void addInternalTriggers(Collection<ITriggerInternal> triggers, IStatementContainer container);

    void addInternalSidedTriggers(Collection<ITriggerInternalSided> triggers, IStatementContainer container, @Nonnull FaceInfo side);

    /** Returns the list of triggers available to a gate next to the given block. */
    void addExternalTriggers(Collection<ITriggerExternal> triggers, @Nonnull FaceInfo side, BlockEntity tile);
}
