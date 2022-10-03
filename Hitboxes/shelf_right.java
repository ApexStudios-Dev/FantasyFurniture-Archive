Stream.of(
Block.box(1, 4, 13, 4, 6, 16),
Block.box(1.5, 6, 14, 3.5, 11, 16),
Block.box(1, 11, 13, 4, 14, 16),
Block.box(1.5, 12, 8, 3.5, 14, 13),
Block.box(0, 14, 0, 16, 16, 16)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();