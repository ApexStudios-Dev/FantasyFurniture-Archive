Stream.of(
Block.box(10, 0, 5, 12, 1, 7),
Block.box(9, 1, 4, 13, 3, 8),
Block.box(3, 2, 9, 7, 4, 13),
Block.box(4, 0, 10, 6, 2, 12)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();