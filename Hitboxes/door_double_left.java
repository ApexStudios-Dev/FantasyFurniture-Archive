Stream.of(
Block.box(0, 0, 0.5, 13, 29, 2.5),
Block.box(13, 0, 0, 16, 32, 3),
Block.box(12, 20, 0, 13, 32, 3),
Block.box(11, 24, 0, 12, 32, 3),
Block.box(9, 27, 0, 11, 32, 3),
Block.box(4, 28, 0, 9, 32, 3),
Block.box(0, 29, 0, 4, 32, 3)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();