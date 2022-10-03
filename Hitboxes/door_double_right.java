Stream.of(
Block.box(3, 0, 0.5, 16, 29, 2.5),
Block.box(0, 0, 0, 3, 32, 3),
Block.box(3, 20, 0, 4, 32, 3),
Block.box(4, 24, 0, 5, 32, 3),
Block.box(5, 27, 0, 7, 32, 3),
Block.box(7, 28, 0, 12, 32, 3),
Block.box(12, 29, 0, 16, 32, 3)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();