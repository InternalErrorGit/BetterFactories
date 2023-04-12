package net.internalerror.betterfactories.world.level.block;

import net.internalerror.betterfactories.forge.registry.BFBlockEntities;
import net.internalerror.betterfactories.world.level.block.entity.CrushingMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.extensions.IForgeBlockState;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
public final class CrushingMachineBlock extends BaseEntityBlock implements IForgeBlockState {
  
  private static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  
  public CrushingMachineBlock(Properties pProperties) {
    super(pProperties);
  }
  
  @Override
  public @NotNull BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
    return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public @NotNull BlockState rotate(@NotNull BlockState pBlockState, @NotNull Rotation pRotation) {
    return pBlockState.setValue(FACING, pRotation.rotate(pBlockState.getValue(FACING)));
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public @NotNull BlockState mirror(@NotNull BlockState pBlockState, @NotNull Mirror pMirror) {
    return pBlockState.rotate(pMirror.getRotation(pBlockState.getValue(FACING)));
  }
  
  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBlockBlockStateBuilder) {
    pBlockBlockStateBuilder.add(FACING);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public @NotNull RenderShape getRenderShape(@NotNull BlockState pBlockState) {
    return RenderShape.MODEL;
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public void onRemove(BlockState pBlockState, @NotNull Level pLevel, @NotNull BlockPos pBlockPos, BlockState pNewBlockState, boolean pIsMoving) {
    if (pBlockState.getBlock() != pNewBlockState.getBlock()) {
      BlockEntity blockEntity = pLevel.getBlockEntity(pBlockPos);
      if (blockEntity instanceof CrushingMachineBlockEntity crushingMachineBlockEntity) {
        crushingMachineBlockEntity.dropContents();
      }
    }
    super.onRemove(pBlockState, pLevel, pBlockPos, pNewBlockState, pIsMoving);
  }
  
  @Override
  @SuppressWarnings("deprecation")
  public @NotNull InteractionResult use(@NotNull BlockState pBlockState, @NotNull Level pLevel, @NotNull BlockPos pBlockPos, @NotNull Player pPlayer, @NotNull InteractionHand pInteractionHand, BlockHitResult pBlockHitResult) {
    if (! pLevel.isClientSide()) {
      BlockEntity entity = pLevel.getBlockEntity(pBlockPos);
      if (entity instanceof MenuProvider menuProvider) {
        NetworkHooks.openScreen(((ServerPlayer) pPlayer), menuProvider, pBlockPos);
      } else {
        throw new IllegalStateException("Our Container provider is missing!");
      }
    }
    
    return InteractionResult.sidedSuccess(pLevel.isClientSide());
  }
  
  @Override
  public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pBlockPos, @NotNull BlockState pBlockState) {
    return new CrushingMachineBlockEntity(pBlockPos, pBlockState);
  }
  
  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockEntityType<T> pBlockEntityType) {
    return createTickerHelper(pBlockEntityType, BFBlockEntities.CRUSHING_MACHINE_BLOCK_ENTITY.get(), ((level, pos, state, blockEntity) -> blockEntity.tick(level)));
  }
}
