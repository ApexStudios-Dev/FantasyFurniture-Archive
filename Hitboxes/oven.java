Stream.of(
Block.box(1, 0, 1, 4, 2, 4),
Block.box(1, 0, 12, 4, 2, 15),
Block.box(12, 0, 12, 15, 2, 15),
Block.box(12, 0, 1, 15, 2, 4),
Block.box(0, 2, 0, 16, 16, 16)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();