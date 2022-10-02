Stream.of(
Block.box(0, 0, 0, 3, 2, 3),
Block.box(0, 14, 0, 3, 16, 3),
Block.box(0, 14, 13, 3, 16, 16),
Block.box(0, 0, 13, 3, 2, 16),
Block.box(13, 0, 13, 16, 2, 16),
Block.box(13, 14, 13, 16, 16, 16),
Block.box(13, 14, 0, 16, 16, 3),
Block.box(13, 0, 0, 16, 2, 3),
Block.box(1, 0, 1, 15, 16, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();