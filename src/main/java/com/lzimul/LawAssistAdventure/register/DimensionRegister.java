package com.lzimul.LawAssistAdventure.register;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;

import static com.lzimul.LawAssistAdventure.Config.MODID;

public class DimensionRegister {
    public static final ResourceKey<Level> Overworld = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("overworld"));
    public static final ResourceKey<Level> TheNether = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("the_nether"));
    public static final ResourceKey<Level> TheEnd = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("the_end"));
    public static final ResourceKey<Level> FinalWing = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(MODID, "final_wing"));
    public static final ResourceKey<Level> RemnantDawn = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(MODID, "remnant_dawn"));
    public static final ResourceKey<Level> FallIntoTheVoid = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(MODID, "fall_into_the_void"));
    public static final ResourceKey<Level> Dust = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(MODID, "dust"));
    public static final ResourceKey<Level> Staring = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(MODID, "staring"));


    private static boolean isAirOrStone(LevelChunk chunk, BlockPos pos) {
        BlockState state = chunk.getBlockState(pos);
        return state.getBlock().equals(Blocks.STONE) || state.isAir();
    }

    private static boolean isReplaceable(Level world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.getBlock().equals(Blocks.STONE) ||
                state.getBlock().equals(Blocks.GRANITE) ||
                state.getBlock().equals(Blocks.ANDESITE) ||
                state.getBlock().equals(Blocks.DIORITE) ||
                state.getBlock().equals(Blocks.DIRT) ||
                state.getBlock().equals(Blocks.GRAVEL) ||
                state.getBlock().equals(Blocks.LAVA) ||
                state.isAir();
    }

    public static Vec3 getASafeArea(ServerLevel world, LevelChunk chunk) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int min = world.getMinBuildHeight();
        int max = world.getMaxBuildHeight() - 10;
        for (int y = max - 1; y >= min; y = y - 1) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    pos.set(x, y, z);
                    if (chunk.getBlockState(pos).isAir() && chunk.getBlockState(pos.above(1)).isAir() && chunk.getBlockState(pos.above(2)).isAir()) {
                        BlockPos absolutePos = chunk.getPos().getWorldPosition().offset(pos.getX(), pos.getY(), pos.getZ());
                        return new Vec3(absolutePos.getX() + 0.5, absolutePos.getY() + 1, absolutePos.getZ() + 0.5);
                    }
                }
            }
        }

        for (int y = max - 1; y >= min; y = y - 1) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    pos.set(x, y, z);
                    if (isAirOrStone(chunk, pos) && isAirOrStone(chunk, pos.above(1)) && isAirOrStone(chunk, pos.above(2))) {
                        BlockPos absolutePos = chunk.getPos().getWorldPosition().offset(pos.getX(), pos.getY(), pos.getZ());
                        if (isReplaceable(world, absolutePos.above(3)) &&
                                isReplaceable(world, absolutePos.above(1).relative(Direction.NORTH)) &&
                                isReplaceable(world, absolutePos.above(1).relative(Direction.NORTH)) &&
                                isReplaceable(world, absolutePos.above(1).relative(Direction.SOUTH)) &&
                                isReplaceable(world, absolutePos.above(1).relative(Direction.EAST)) &&
                                isReplaceable(world, absolutePos.above(1).relative(Direction.WEST)) &&
                                isReplaceable(world, absolutePos.above(2).relative(Direction.NORTH)) &&
                                isReplaceable(world, absolutePos.above(2).relative(Direction.SOUTH)) &&
                                isReplaceable(world, absolutePos.above(2).relative(Direction.EAST)) &&
                                isReplaceable(world, absolutePos.above(2).relative(Direction.WEST))
                        ) {
                            world.setBlockAndUpdate(absolutePos.above(1), Blocks.AIR.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(2), Blocks.AIR.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(3), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(1).relative(Direction.NORTH), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(1).relative(Direction.SOUTH), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(1).relative(Direction.EAST), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(1).relative(Direction.WEST), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(2).relative(Direction.NORTH), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(2).relative(Direction.SOUTH), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(2).relative(Direction.EAST), Blocks.STONE.defaultBlockState());
                            world.setBlockAndUpdate(absolutePos.above(2).relative(Direction.WEST), Blocks.STONE.defaultBlockState());
                            return new Vec3(absolutePos.getX() + 0.5, absolutePos.getY() + 1, absolutePos.getZ() + 0.5);
                        }
                    }
                }
            }
        }
        return null;
    }
}
