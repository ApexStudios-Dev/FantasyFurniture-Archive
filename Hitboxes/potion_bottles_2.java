Stream.of(
Block.box(10, 0, 10, 14, 10, 14),
Block.box(1.25, 0, 6.25, 6.75, 10, 11.75),
Block.box(6.25, 0, 1.25, 11.75, 10, 6.75)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();