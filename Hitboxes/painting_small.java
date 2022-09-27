Stream.of(
Block.box(0, 0, 13, 3, 3, 16),
Block.box(0, 13, 13, 3, 16, 16),
Block.box(13, 13, 13, 16, 16, 16),
Block.box(13, 0, 13, 16, 3, 16),
Block.box(1, 1, 14, 15, 15, 16)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();