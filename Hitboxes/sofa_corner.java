Stream.of(
Block.box(12, 0, 12, 15, 2, 15),
Block.box(12, 2, 12, 14, 3, 14),
Block.box(0, 3, 12, 15, 16, 15),
Block.box(12, 3, 0, 15, 16, 12),
Block.box(1, 3, 0, 15, 6, 12),
Block.box(0, 3, 1, 14, 6, 12)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();