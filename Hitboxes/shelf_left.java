Stream.of(
Block.box(12, 4, 13, 15, 6, 16),
Block.box(12.5, 6, 14, 14.5, 11, 16),
Block.box(12, 11, 13, 15, 14, 16),
Block.box(12.5, 12, 8, 14.5, 14, 13),
Block.box(0, 14, 0, 16, 16, 16)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();