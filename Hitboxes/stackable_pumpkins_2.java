Stream.of(
Block.box(4, 0, 4, 12, 7, 12),
Block.box(3.25, 7, 3.25, 12.75, 12, 12.75),
Block.box(5.5, 12, 5.5, 10.5, 16, 10.5)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();