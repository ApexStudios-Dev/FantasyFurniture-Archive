Stream.of(
Block.box(0, 0, 3, 16, 13, 16),
Block.box(1, 1, 2, 15, 12, 3),
Block.box(0, 13, 0, 16, 16, 16)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();