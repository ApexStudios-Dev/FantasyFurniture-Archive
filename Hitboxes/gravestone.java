Stream.of(
Block.box(1.5, 0, 6, 14.5, 2, 10),
Block.box(2, 2, 6.5, 14, 15, 9.5),
Block.box(4, 15, 6.5, 12, 16, 9.5)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();